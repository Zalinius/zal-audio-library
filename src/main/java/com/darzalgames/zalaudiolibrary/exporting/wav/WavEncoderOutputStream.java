package com.darzalgames.zalaudiolibrary.exporting.wav;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import com.darzalgames.zalaudiolibrary.AudioConstants;
import com.darzalgames.zalaudiolibrary.effects.sampling.SampleClipper;
import com.darzalgames.zalaudiolibrary.pipeline.zamples.AudioConsumer;

/**
 * A class that can encode a .wav file from mono float PCM data
 */
public class WavEncoderOutputStream implements AudioConsumer {

	private final ByteArrayOutputStream sampleOutputBuffer;
	private final OutputStream wavOutput;
	private final Map<String, String> metadata;

	private final int currentOutputSize;
	private final SampleClipper sampleClipper;


	public WavEncoderOutputStream(OutputStream outputStream, Map<String, String> metadata) {
		sampleOutputBuffer = new ByteArrayOutputStream();
		wavOutput = outputStream;
		this.metadata = metadata;
		currentOutputSize = 0;
		sampleClipper = new SampleClipper(1f);
	}

	@Override
	public void close() throws Exception {
		sampleOutputBuffer.close();
		wavOutput.close();
	}

	@Override
	public void writeSamples(float[] samples) {
		int numSamples = samples.length;
		byte[] bytes = new byte[numSamples * 2];
		int end = Math.min(currentOutputSize + numSamples, samples.length);
		for (int i = currentOutputSize, ii = 0; i < end; i++) {
			float floatSample = samples[i];
			floatSample = sampleClipper.apply(floatSample);
			int intSample = (int)(floatSample * 32767);
			bytes[ii++] = (byte)(intSample & 0xFF);
			bytes[ii++] = (byte)((intSample >> 8) & 0xFF);
		}
		try {
			sampleOutputBuffer.write(bytes);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void writeWavToOutputStream() throws IOException {
		byte[] sampleBytes = sampleOutputBuffer.toByteArray(); //Every 2 bytes is a sample

		int formatChunkSize = 24;
		int listInfoChunkSize = 12 + computeMetaDataSizeInBytes(metadata);
		int dataChunkSize = 8 + sampleBytes.length;

		int sizeOfFileMinusType = 4 + formatChunkSize + listInfoChunkSize + dataChunkSize;
		short channels = (short) 1; //Mono
		short bitsPerSample = Short.BYTES * Byte.SIZE;
		int byteRate = AudioConstants.SAMPLING_RATE * channels * Short.BYTES;
		short blockAlign = (short) (channels * Short.BYTES);

		//RIFF chunk descriptor
		wavOutput.write(intToByteBuffer(0x52494646, ByteOrder.BIG_ENDIAN)); //The word RIFF in ASCII
		wavOutput.write(intToByteBuffer(sizeOfFileMinusType, ByteOrder.LITTLE_ENDIAN)); //The size of everything after this in bytes, or 36+data size
		wavOutput.write(intToByteBuffer(0x57415645, ByteOrder.BIG_ENDIAN)); //The word WAVE in ASCII

		//FMT sound formatting sub-chunk
		int subchunkSize = 16; //The size of the fmt  sub-chunck minus the initial 8 bytes
		wavOutput.write(intToByteBuffer(0x666d7420, ByteOrder.BIG_ENDIAN)); //The word fmt  in ASCII
		wavOutput.write(intToByteBuffer(subchunkSize, ByteOrder.LITTLE_ENDIAN)); //The size of everything in the sub-chunk after this in bytes, 16 for PCM
		wavOutput.write(shortToByteBuffer((short)1, ByteOrder.LITTLE_ENDIAN)); //The PCM quantization. 1 because uncompressed
		wavOutput.write(shortToByteBuffer(channels, ByteOrder.LITTLE_ENDIAN)); //number of audio channels
		wavOutput.write(intToByteBuffer(AudioConstants.SAMPLING_RATE, ByteOrder.LITTLE_ENDIAN)); //Sampling Rate
		wavOutput.write(intToByteBuffer(byteRate, ByteOrder.LITTLE_ENDIAN)); //Byte Rate
		wavOutput.write(shortToByteBuffer(blockAlign, ByteOrder.LITTLE_ENDIAN));
		wavOutput.write(shortToByteBuffer(bitsPerSample, ByteOrder.LITTLE_ENDIAN));

		//LIST INFO chunk (which is an optional sub-chunk of the riff chunk)
		wavOutput.write(intToByteBuffer(0x4C495354, ByteOrder.BIG_ENDIAN)); //The word LIST in ASCII
		wavOutput.write(intToByteBuffer(computeMetaDataSizeInBytes(metadata) + 4, ByteOrder.LITTLE_ENDIAN)); //The size of everything in this chunk after this, in bytes
		wavOutput.write(intToByteBuffer(0x494E464F, ByteOrder.BIG_ENDIAN)); //The word INFO in ASCII


		for (Iterator<Map.Entry<String, String>> it = metadata.entrySet().iterator(); it.hasNext();) {
			Entry<String, String> metadatum = it.next();

			String infoSubChunkId = metadatum.getKey();
			String infoSubChunkValue = metadatum.getValue();
			wavOutput.write(infoSubChunkId.getBytes(StandardCharsets.US_ASCII)); //The word LIST in ASCII
			wavOutput.write(intToByteBuffer(infoSubChunkValue.length(), ByteOrder.LITTLE_ENDIAN)); //The length of the null-terminated string to be written
			wavOutput.write(infoSubChunkValue.getBytes(StandardCharsets.US_ASCII)); //The word LIST in ASCII
			if(infoSubChunkValue.length() % 2 == 1) {
				//Pad with a 0 byte if odd size, data in RIFF files must be word aligned (2 bytes)
				wavOutput.write(0);
			}
		}


		//DATA sub-chunk
		int dataByteSize = sampleBytes.length * channels;
		wavOutput.write(intToByteBuffer(0x64617461, ByteOrder.BIG_ENDIAN)); //The word DATA in ASCII
		wavOutput.write(intToByteBuffer(dataByteSize, ByteOrder.LITTLE_ENDIAN)); //The size of everything in the sub-chunk after this in bytes, 16 for PCM
		wavOutput.write(sampleBytes);
	}

	private static byte[] intToByteBuffer(int integer, ByteOrder byteOrder) {
		ByteBuffer b = ByteBuffer.allocate(Integer.BYTES);
		b.order(byteOrder);
		b.putInt(integer);
		return b.array();
	}

	private static byte[] shortToByteBuffer(short s, ByteOrder byteOrder) {
		ByteBuffer b = ByteBuffer.allocate(Short.BYTES);
		b.order(byteOrder);
		b.putShort(s);
		return b.array();
	}

	public static int computeMetaDataSizeInBytes(Map<String, String> metadata) {
		int size = 0;

		for (Iterator<Entry<String, String>> it = metadata.entrySet().iterator(); it.hasNext();) {
			Entry<String, String> metadatum = it.next();
			size += 4; //The chunk ID
			size += 4; //The chunk size
			size += metadatum.getValue().length();
			if(size % 2 == 1) {
				size += 1;
			}
		}

		return size;
	}

}
