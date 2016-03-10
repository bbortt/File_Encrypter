package timon.borter.view;

/**
 * Imports.
 */
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import timon.borter.controller.CryptoController;
import timon.borter.controller.MainController;

/**
 * Displays the functions of the tool. Everything starts here.
 * 
 * @author Timon Borter
 * 
 */
public class MainMenu extends Page {
	/**
	 * Static variables.
	 */
	private static MainController mainController;
	private static CryptoController cryptoController;

	/**
	 * Set up the menu page.
	 * 
	 * @param givenFrame
	 *            The frame which handles the pages.
	 */
	public static void show(JFrame givenFrame) {
		// Bind local variables
		mainFrame = givenFrame;

		// Create the controllers
		mainController = new MainController();
		cryptoController = new CryptoController(mainFrame);

		// Clear the frame
		mainFrame.getContentPane().removeAll();

		// Header region
		JPanel titelPanel = new JPanel();
		titelPanel.setBorder(BorderFactory.createEmptyBorder(20, 100, 0, 100));
		titelPanel.setLayout(new GridLayout(3, 1));
		mainFrame.getContentPane().add(titelPanel, BorderLayout.NORTH);
		{
			// Title
			JLabel lblBinaryEncrypter = new JLabel("File Encrypter");
			lblBinaryEncrypter
					.setFont(new Font("URW Chancery L", Font.BOLD, 30));
			lblBinaryEncrypter.setHorizontalAlignment(SwingConstants.CENTER);
			titelPanel.add(lblBinaryEncrypter);

			// Copyright
			JLabel lblOwnerCopyright = new JLabel("(c) 2016 by Timon Borter");
			lblOwnerCopyright
					.setFont(new Font("URW Chancery L", Font.BOLD, 10));
			lblOwnerCopyright.setHorizontalAlignment(SwingConstants.CENTER);
			titelPanel.add(lblOwnerCopyright);
		}

		// Body region
		JPanel menuPanel = new JPanel();
		menuPanel.setBorder(new EmptyBorder(0, 100, 50, 100));
		menuPanel.setLayout(new GridLayout(3, 1, 0, 5));
		givenFrame.getContentPane().add(menuPanel, BorderLayout.CENTER);
		{
			// Button to encrypt a file
			JButton btnEncryptFile = new JButton("Encrypt/Decrypt");
			btnEncryptFile.setFont(new Font("URW Chancery L", Font.BOLD, 20));
			btnEncryptFile.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					cryptoController.encryptDecrypt();
				}
			});
			menuPanel.add(btnEncryptFile);

			// Button to gain information about the encryption
			JButton btnDecryptFile = new JButton("About the tool");
			btnDecryptFile.setFont(new Font("URW Chancery L", Font.BOLD, 20));
			btnDecryptFile.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					mainController.showAbout();
				}
			});
			menuPanel.add(btnDecryptFile);

			// Button to gain information about the license
			JButton btnAboutTheProject = new JButton("Exit tool");
			btnAboutTheProject
					.setFont(new Font("URW Chancery L", Font.BOLD, 20));
			btnAboutTheProject.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					System.exit(0);
				}
			});
			menuPanel.add(btnAboutTheProject);
		}

		givenFrame.revalidate();
	}
}