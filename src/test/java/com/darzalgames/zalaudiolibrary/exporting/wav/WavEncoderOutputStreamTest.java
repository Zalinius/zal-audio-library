package com.darzalgames.zalaudiolibrary.exporting.wav;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

class WavEncoderOutputStreamTest {

	@Test
	void initialize_0AudioSamples_createsProperHeader() throws Exception {
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		WavEncoderOutputStream wavEncoderOutputStream = new WavEncoderOutputStream(outputStream, new HashMap<>());

		wavEncoderOutputStream.writeWavToOutputStream();

		wavEncoderOutputStream.close();

		byte[] wavHeader = outputStream.toByteArray();
		assertEquals("RIFF", bytesToAsciiString(Arrays.copyOfRange(wavHeader, 0, 4)));
		assertEquals(48, bytesToInt(Arrays.copyOfRange(wavHeader, 4, 8), ByteOrder.LITTLE_ENDIAN));
		assertEquals("WAVE", bytesToAsciiString(Arrays.copyOfRange(wavHeader, 8, 12)));

		assertEquals("fmt ", bytesToAsciiString(Arrays.copyOfRange(wavHeader, 12, 16)));
		assertEquals(16, bytesToInt(Arrays.copyOfRange(wavHeader, 16, 20), ByteOrder.LITTLE_ENDIAN));
		assertEquals((short)1, bytesToShort(Arrays.copyOfRange(wavHeader, 20, 22), ByteOrder.LITTLE_ENDIAN));
		assertEquals((short)1, bytesToShort(Arrays.copyOfRange(wavHeader, 22, 24), ByteOrder.LITTLE_ENDIAN));
		assertEquals(44100, bytesToInt(Arrays.copyOfRange(wavHeader, 24, 28), ByteOrder.LITTLE_ENDIAN));
		assertEquals(88200, bytesToInt(Arrays.copyOfRange(wavHeader, 28, 32), ByteOrder.LITTLE_ENDIAN));
		assertEquals((short)2, bytesToShort(Arrays.copyOfRange(wavHeader, 32, 34), ByteOrder.LITTLE_ENDIAN));
		assertEquals((short)16, bytesToShort(Arrays.copyOfRange(wavHeader, 34, 36), ByteOrder.LITTLE_ENDIAN));

		assertEquals("LIST", bytesToAsciiString(Arrays.copyOfRange(wavHeader, 36, 40)));
		assertEquals(4, bytesToInt(Arrays.copyOfRange(wavHeader, 40, 44), ByteOrder.LITTLE_ENDIAN));
		assertEquals("INFO", bytesToAsciiString(Arrays.copyOfRange(wavHeader, 44, 48)));

		assertEquals("data", bytesToAsciiString(Arrays.copyOfRange(wavHeader, 48, 52)));
		assertEquals(0, bytesToInt(Arrays.copyOfRange(wavHeader, 52, 56), ByteOrder.LITTLE_ENDIAN));

		assertEquals(56, wavHeader.length);
	}

	@Test
	void initialize_32AudioSamples_createsProperHeader() throws Exception {
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		WavEncoderOutputStream wavEncoderOutputStream = new WavEncoderOutputStream(outputStream, new HashMap<>());
		float[] floatSamples = new float[32];

		wavEncoderOutputStream.writeSamples(floatSamples);
		wavEncoderOutputStream.writeWavToOutputStream();
		wavEncoderOutputStream.close();

		byte[] wavHeader = outputStream.toByteArray();
		assertEquals("RIFF", bytesToAsciiString(Arrays.copyOfRange(wavHeader, 0, 4)));
		assertEquals(112, bytesToInt(Arrays.copyOfRange(wavHeader, 4, 8), ByteOrder.LITTLE_ENDIAN));
		assertEquals("WAVE", bytesToAsciiString(Arrays.copyOfRange(wavHeader, 8, 12)));

		assertEquals("fmt ", bytesToAsciiString(Arrays.copyOfRange(wavHeader, 12, 16)));
		assertEquals(16, bytesToInt(Arrays.copyOfRange(wavHeader, 16, 20), ByteOrder.LITTLE_ENDIAN));
		assertEquals((short)1, bytesToShort(Arrays.copyOfRange(wavHeader, 20, 22), ByteOrder.LITTLE_ENDIAN));
		assertEquals((short)1, bytesToShort(Arrays.copyOfRange(wavHeader, 22, 24), ByteOrder.LITTLE_ENDIAN));
		assertEquals(44100, bytesToInt(Arrays.copyOfRange(wavHeader, 24, 28), ByteOrder.LITTLE_ENDIAN));
		assertEquals(88200, bytesToInt(Arrays.copyOfRange(wavHeader, 28, 32), ByteOrder.LITTLE_ENDIAN));
		assertEquals((short)2, bytesToShort(Arrays.copyOfRange(wavHeader, 32, 34), ByteOrder.LITTLE_ENDIAN));
		assertEquals((short)16, bytesToShort(Arrays.copyOfRange(wavHeader, 34, 36), ByteOrder.LITTLE_ENDIAN));

		assertEquals("LIST", bytesToAsciiString(Arrays.copyOfRange(wavHeader, 36, 40)));
		assertEquals(4, bytesToInt(Arrays.copyOfRange(wavHeader, 40, 44), ByteOrder.LITTLE_ENDIAN));
		assertEquals("INFO", bytesToAsciiString(Arrays.copyOfRange(wavHeader, 44, 48)));

		assertEquals("data", bytesToAsciiString(Arrays.copyOfRange(wavHeader, 48, 52)));
		assertEquals(64, bytesToInt(Arrays.copyOfRange(wavHeader, 52, 56), ByteOrder.LITTLE_ENDIAN));

		assertEquals(120, wavHeader.length);
	}

	@Test
	void makeMetadata_mapDataHasCorrectKeysAndAreNullTerminatedStrings() throws Exception {
		Map<String, String> metadata = WavUtils.makeWavMetadata("title", "artist name", "album name", 0, 2023, "genre");

		assertEquals(6, metadata.size());

		assertEquals("title\0", metadata.get("INAM"));
		assertEquals("artist name\0", metadata.get("IART"));
		assertEquals("album name\0", metadata.get("IPRD"));
		assertEquals("0\0", metadata.get("IPRT"));
		assertEquals("2023\0", metadata.get("ICRD"));
		assertEquals("genre\0", metadata.get("IGNR"));
	}

	private static String bytesToAsciiString(byte[] bytes) {
		return new String(bytes, Charset.defaultCharset());
	}

	private static int bytesToInt(byte[] bytes, ByteOrder byteOrder) {
		assertEquals(4, bytes.length);
		ByteBuffer b = ByteBuffer.wrap(bytes);
		b.order(byteOrder);
		return b.getInt();
	}

	private static short bytesToShort(byte[] bytes, ByteOrder byteOrder) {
		assertEquals(2, bytes.length);
		ByteBuffer b = ByteBuffer.wrap(bytes);
		b.order(byteOrder);
		return b.getShort();
	}
}
