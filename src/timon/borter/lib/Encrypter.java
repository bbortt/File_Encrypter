package timon.borter.lib;

/**
 * Imports.
 */
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;

/**
 * Contains the logic required to encrypt and decrypt files. Also contains
 * functions to transfer bytes into bits and back.
 * 
 * @author Timon Borter
 * 
 */
public class Encrypter {
	/**
	 * Encrypts a file by creating a random key and using XOR algorithm.
	 * 
	 * @param file
	 *            The file to encrypt.
	 * @param keyLocation
	 *            The location to store the generated key.
	 * @return True if successfully encrypted.
	 */
	public boolean encrypt(Path file, Path keyLocation) {
		// Log start
		Log.writeInformation("Started encrypting " + file);

		// The bytes of the uncrypted file
		byte[] byteArray;

		// Try to open the uncrypted file
		try {
			byteArray = Files.readAllBytes(file);
		} catch (IOException e) {
			Log.writeError("Could not open file to encrypt. " + e.getMessage());
			return false;
		}

		// Parse bytes into bits
		int[] bitArray = generateBits(byteArray);

		// Start generating a key
		List<Integer> keyList = new ArrayList<Integer>();

		// For each bit of the file
		for (int i = 0; i < bitArray.length; i++) {
			// Generate 1 or 0 randomly
			keyList.add((Math.random() > 0.5) ? 1 : 0);

			// XOR encryption
			bitArray[i] = ((bitArray[i] != 0) ^ (keyList
					.get(keyList.size() - 1)) != 0) ? 1 : 0;
		}

		// Cast the file and the key back into bytes
		byte[] encryptedByteArray = generateBytes(bitArray);
		byte[] keyArray = generateBytes(ArrayUtils.toPrimitive(keyList
				.toArray(new Integer[keyList.size()])));

		// Try to write encrypted file
		try {
			Path encryptedFile = Paths.get(file + ".crpt");
			Files.write(encryptedFile, encryptedByteArray,
					StandardOpenOption.CREATE,
					StandardOpenOption.TRUNCATE_EXISTING);
		} catch (IOException e) {
			Log.writeError("Failed to write encrypted file. " + e.getMessage());
			return false;
		}

		// Try to write key file
		try {
			Path keyFile = Paths.get(keyLocation + "/" + file.getFileName()
					+ ".key");
			Files.write(keyFile, keyArray, StandardOpenOption.CREATE,
					StandardOpenOption.TRUNCATE_EXISTING);
		} catch (IOException e) {
			Log.writeError("Failed to write key file. " + e.getMessage());
			File toDelete = new File(file + ".crpt");
			toDelete.delete();
			return false;
		}

		// Try to finalize class
		try {
			this.finalize();
		} catch (Throwable e) {
			Log.writeError("Could not unset variables. " + e.getMessage());
		}

		// Return true if completed
		return true;
	}

	/**
	 * Decrypts a file with a specified key.
	 * 
	 * @param file
	 *            The file to decrypt.
	 * @param key
	 *            The key to use.
	 * @return True if succesfully decrypted.
	 */
	public boolean decrypt(Path file, Path key) {
		// Log start
		Log.writeInformation("Started decrypting " + file);

		// The bytes of the encrypted file and key
		byte[] byteArray;
		byte[] byteKey;

		// Try to open the encrypted file
		try {
			byteArray = Files.readAllBytes(file);
		} catch (IOException e) {
			Log.writeError("Could not open file to decrypt. " + e.getMessage());
			return false;
		}

		// Try to open the key
		try {
			byteKey = Files.readAllBytes(key);
		} catch (IOException e) {
			Log.writeError("Could not open key file. " + e.getMessage());
			return false;
		}

		// Parse bytes into bits
		int[] bitArray = generateBits(byteArray);
		int[] bitKey = generateBits(byteKey);

		// For each bit of the file
		for (int i = 0; i < bitArray.length; i++) {
			// XOR decryption
			bitArray[i] = ((bitArray[i] != 0) ^ (bitKey[i] != 0)) ? 1 : 0;
		}

		// Cast the decrypted file back into bytes
		byte[] decryptedByteArray = generateBytes(bitArray);

		// Try to write encrypted file
		try {
			Path decryptedFile = Paths.get(String.valueOf(file).replaceAll(
					".crpt", ""));
			Files.write(decryptedFile, decryptedByteArray,
					StandardOpenOption.CREATE,
					StandardOpenOption.TRUNCATE_EXISTING);
		} catch (IOException e) {
			Log.writeError("Failed to write decrypted file. " + e.getMessage());
			return false;
		}

		// Try to finalize class
		try {
			this.finalize();
		} catch (Throwable e) {
			Log.writeError("Could not finalize class. " + e.getMessage());
		}

		// Return true if completed
		return true;

	}

	/**
	 * Generates an int[] array with bits.
	 * 
	 * @param byteArray
	 *            The bytes to convert.
	 * @return The converted bytes as int[].
	 */
	private int[] generateBits(byte[] byteArray) {
		// Due to the twos complement we have to save byte as int
		int toCalculate;

		// List with bits out of bytes
		List<Integer> bitList = new ArrayList<Integer>();

		// For each byte
		for (int i = 0; i < byteArray.length; i++) {
			// If twos complement: Add 256
			if (byteArray[i] < 0) {
				toCalculate = (256 + (int) byteArray[i]);
			}
			// If not: Do not
			else {
				toCalculate = (int) byteArray[i];
			}

			// Special case for "0000 0000"
			if (toCalculate == 0) {
				bitList.add(0);
			}
			// For each other byte
			else {
				// While you can divide it
				while (toCalculate > 0) {
					// If divisible by 2: Add 0 and recalculate byte
					if (toCalculate % 2 == 0) {
						bitList.add(0);
						toCalculate = (byte) (toCalculate / 2);
					}
					// If not: Add 1 and recalculate byte
					else {
						bitList.add(1);
						toCalculate = (byte) ((toCalculate - 1) / 2);
					}
				}
			}

			// Complete a byte (to 8 bits)
			while (bitList.size() < 8 || bitList.size() % 8 != 0) {
				bitList.add(0);
			}

		}

		// Cast List<Integer> into int[]
		int[] bitArray = ArrayUtils.toPrimitive(bitList
				.toArray(new Integer[bitList.size()]));

		// We have to reverse each byte
		for (int i = 0; i < bitArray.length; i = i + 8) {
			int tmpInt;
			tmpInt = bitArray[i + 7];
			bitArray[i + 7] = bitArray[i];
			bitArray[i] = tmpInt;
			tmpInt = bitArray[i + 6];
			bitArray[i + 6] = bitArray[i + 1];
			bitArray[i + 1] = tmpInt;
			tmpInt = bitArray[i + 5];
			bitArray[i + 5] = bitArray[i + 2];
			bitArray[i + 2] = tmpInt;
			tmpInt = bitArray[i + 4];
			bitArray[i + 4] = bitArray[i + 3];
			bitArray[i + 3] = tmpInt;
		}

		// Return the final bit array
		return bitArray;
	}

	/**
	 * Generates a byte[] array with bytes.
	 * 
	 * @param bitArray
	 *            The bits to convert.
	 * @return The converted bits as byte[].
	 */
	private byte[] generateBytes(int[] bitArray) {
		// List with bytes out of bits
		List<Byte> byteList = new ArrayList<Byte>();

		// For each byte as 8 bits
		for (int i = 0; i < bitArray.length; i = i + 8) {
			int tmpInt = 0;
			tmpInt = (int) (tmpInt + bitArray[i + 7] * Math.pow(2, 0));
			tmpInt = (int) (tmpInt + bitArray[i + 6] * Math.pow(2, 1));
			tmpInt = (int) (tmpInt + bitArray[i + 5] * Math.pow(2, 2));
			tmpInt = (int) (tmpInt + bitArray[i + 4] * Math.pow(2, 3));
			tmpInt = (int) (tmpInt + bitArray[i + 3] * Math.pow(2, 4));
			tmpInt = (int) (tmpInt + bitArray[i + 2] * Math.pow(2, 5));
			tmpInt = (int) (tmpInt + bitArray[i + 1] * Math.pow(2, 6));
			tmpInt = (int) (tmpInt - bitArray[i] * Math.pow(2, 7));
			byteList.add((byte) tmpInt);
		}

		// Cast List<Byte> into byte[]
		byte[] byteArray = ArrayUtils.toPrimitive(byteList
				.toArray(new Byte[byteList.size()]));

		// Return the final byte array
		return byteArray;
	}
}