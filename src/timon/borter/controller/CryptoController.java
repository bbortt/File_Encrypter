package timon.borter.controller;

/**
 * Imports.
 */
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import timon.borter.lib.Encrypter;
import timon.borter.lib.Log;
import timon.borter.view.DecryptPage;
import timon.borter.view.EncryptPage;
import timon.borter.view.MainMenu;

/**
 * Controls the sub pages where encryption and decryption are done. Only handles
 * the GUI. The logic behind is handled by Encrypter.java.
 * 
 * @author Timon Borter
 * 
 */
public class CryptoController {
	/**
	 * Static variables.
	 */
	private static Encrypter encrypter;
	private static JFrame mainFrame;

	/**
	 * Configure the controller.
	 * 
	 * @param givenFrame
	 *            The frame which handles the pages.
	 */
	public CryptoController(JFrame givenFrame) {
		// Bind local variables
		mainFrame = givenFrame;
	}

	/**
	 * Show the encryption page.
	 */
	public void encryptDecrypt() {
		// Ask user if he wants to encrypt or decrypt a file
		Object[] options = { "Encrypt File", "Decrypt File", "Cancel" };
		int answer = JOptionPane.showOptionDialog(mainFrame,
				"What do you want to do?", "Select option",
				JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,
				null, options, options[2]);

		// Encrypt file
		if (answer == 0) {
			this.startEncryption();
		}
		// Decrypt file
		else if (answer == 1) {
			this.startDecryption();
		}
		// Cancel
		else {
			this.backToMenu();
		}
	}

	/**
	 * Show encryption page.
	 */
	private void startEncryption() {
		EncryptPage.show(mainFrame);
	}

	/**
	 * Encrypt a file.
	 * 
	 * @param fileString
	 *            The file to encrypt.
	 * @param keyString
	 *            The location to store the key.
	 */
	public void encrypt(String fileString, String keyString) {
		// Convert String into File
		File fileToEncrypt = new File(fileString);
		File keyLocation = new File(keyString);

		// If the file does not exist: Show encrypt page
		if (!fileToEncrypt.exists() || !fileToEncrypt.isFile()) {
			JOptionPane.showMessageDialog(mainFrame,
					"Please select a valid file.", "Error",
					JOptionPane.ERROR_MESSAGE, null);
		}
		// If the key location does not exist: Show encrypt page
		else if (!keyLocation.exists() || !keyLocation.isDirectory()) {
			JOptionPane.showMessageDialog(mainFrame,
					"Please select a valid directory.", "Error",
					JOptionPane.ERROR_MESSAGE, null);
		}
		// If it does not: Show the main menu
		else {
			Path filePath = Paths.get(fileString);
			Path keyPath = Paths.get(keyString);

			encrypter = new Encrypter();
			if (encrypter.encrypt(filePath, keyPath)) {
				Log.writeInformation("Successfully encrypted " + fileString);

				// Ask user if he wants to encrypt another file
				Object[] options = { "Again", "Decrypt File", "Menu" };
				int answer = JOptionPane
						.showOptionDialog(mainFrame, "Successfully encrypted\""
								+ fileString + ".\nWhat do you want to do?",
								"Succeed", JOptionPane.YES_NO_CANCEL_OPTION,
								JOptionPane.QUESTION_MESSAGE, null, options,
								options[2]);

				// Encrypt file
				if (answer == 0) {
					this.startEncryption();
				}
				// Decrypt file
				else if (answer == 1) {
					this.startDecryption();
				}
				// Cancel
				else {
					this.backToMenu();
				}
			} else {
				JOptionPane.showMessageDialog(mainFrame, "Failed to encrypt \""
						+ fileString + "\".\nPlease check the log.", "Error",
						JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	/**
	 * Show decryption page.
	 */
	private void startDecryption() {
		DecryptPage.show(mainFrame);
	}

	/**
	 * Decrypt a file.
	 * 
	 * @param fileString
	 *            The file to encrypt.
	 * @param keyString
	 *            The key to use.
	 */
	public void decrypt(String fileString, String keyString) {
		// Convert String into File
		File fileToDecrypt = new File(fileString);
		File keyToUse = new File(keyString);

		// If the file does not exist: Show decrypt page
		if (!fileToDecrypt.exists() || !fileToDecrypt.isFile()
				|| !fileToDecrypt.getName().contains(".crpt")) {
			JOptionPane.showMessageDialog(mainFrame,
					"Please select a valid file.", "Error",
					JOptionPane.ERROR_MESSAGE, null);
		}
		// If the key does not exist: Show decrypt page.
		else if (!keyToUse.exists() || !keyToUse.isFile()
				|| !keyToUse.getName().contains(".key")) {
			JOptionPane.showMessageDialog(mainFrame,
					"Please select a valid key.", "Error",
					JOptionPane.ERROR_MESSAGE, null);
		}
		// If it does not: Show the main menu
		else {
			Path filePath = Paths.get(fileString);
			Path keyPath = Paths.get(keyString);

			encrypter = new Encrypter();
			if (encrypter.decrypt(filePath, keyPath)) {
				Log.writeInformation("Successfully decrypted " + fileString);

				// Ask user if he wants to encrypt another file
				Object[] options = { "Again", "Decrypt File", "Menu" };
				int answer = JOptionPane
						.showOptionDialog(mainFrame, "Successfully decrypted\""
								+ fileString + "\".\nWhat do you want to do?",
								"Succeed", JOptionPane.YES_NO_CANCEL_OPTION,
								JOptionPane.QUESTION_MESSAGE, null, options,
								options[2]);

				// Encrypt file
				if (answer == 0) {
					this.startEncryption();
				}
				// Decrypt file
				else if (answer == 1) {
					this.startDecryption();
				}
				// Cancel
				else {
					this.backToMenu();
				}
			} else {
				JOptionPane.showMessageDialog(mainFrame, "Failed to decrypt \""
						+ fileString + "\".\nPlease check the log.", "Error",
						JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	/**
	 * Show the main menu page.
	 */
	public void backToMenu() {
		MainMenu.show(mainFrame);
	}
}