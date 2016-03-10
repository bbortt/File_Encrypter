package timon.borter.view;

/**
 * Imports.
 */
import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import timon.borter.controller.CryptoController;

/**
 * Contains utilities to encrypt a page.
 * 
 * @author Timon Borter
 * 
 */
public class EncryptPage extends Page {
	/**
	 * Static variables
	 */
	private static CryptoController cryptoController;

	/**
	 * Set up encryption page.
	 * 
	 * @param givenFrame
	 *            The frame which handles the pages.
	 */
	public static void show(JFrame givenFrame) {
		// Bind local variables
		mainFrame = givenFrame;

		// Create the controller
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
			JLabel lblBinaryEncrypter = new JLabel("Select file to encrypt:");
			lblBinaryEncrypter
					.setFont(new Font("URW Chancery L", Font.BOLD, 30));
			lblBinaryEncrypter.setHorizontalAlignment(SwingConstants.CENTER);
			titelPanel.add(lblBinaryEncrypter);

			// Copyright
			JLabel lblOwnerCopyright = new JLabel(
					"File Encrypter (c) 2016 by Timon Borter");
			lblOwnerCopyright
					.setFont(new Font("URW Chancery L", Font.BOLD, 10));
			lblOwnerCopyright.setHorizontalAlignment(SwingConstants.CENTER);
			titelPanel.add(lblOwnerCopyright);
		}

		// Body region
		JPanel bodyPanel = new JPanel();
		bodyPanel.setBorder(new EmptyBorder(0, 100, 50, 100));
		bodyPanel.setLayout(new GridLayout(4, 1, 0, 5));
		mainFrame.getContentPane().add(bodyPanel, BorderLayout.CENTER);
		{
			// JTextField to select a file
			final JTextField txtSelectedPath = new JTextField();
			txtSelectedPath.setFont(new Font("URW Chancery L", Font.BOLD, 15));
			txtSelectedPath.setText("Select file to encrypt..");
			txtSelectedPath.setEditable(false);
			txtSelectedPath.setCursor(new Cursor(Cursor.HAND_CURSOR));
			txtSelectedPath.addMouseListener(new MouseListener() {
				public void mouseReleased(MouseEvent e) {
				}

				public void mousePressed(MouseEvent e) {
				}

				public void mouseExited(MouseEvent e) {
				}

				public void mouseEntered(MouseEvent e) {
				}

				public void mouseClicked(MouseEvent e) {
					JFileChooser fileChooser = new JFileChooser();
					fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
					int answer = fileChooser.showOpenDialog(null);
					if (answer == JFileChooser.APPROVE_OPTION) {
						txtSelectedPath.setText(fileChooser.getSelectedFile()
								.getAbsolutePath());
					}
				}
			});
			bodyPanel.add(txtSelectedPath);

			// JTextField to select a file
			final JTextField txtKeyLocation = new JTextField();
			txtKeyLocation.setFont(new Font("URW Chancery L", Font.BOLD, 15));
			txtKeyLocation.setText("Select key location..");
			txtKeyLocation.setEditable(false);
			txtKeyLocation.setCursor(new Cursor(Cursor.HAND_CURSOR));
			txtKeyLocation.addMouseListener(new MouseListener() {
				public void mouseReleased(MouseEvent e) {
				}

				public void mousePressed(MouseEvent e) {
				}

				public void mouseExited(MouseEvent e) {
				}

				public void mouseEntered(MouseEvent e) {
				}

				public void mouseClicked(MouseEvent e) {
					JFileChooser fileChooser = new JFileChooser();
					fileChooser
							.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
					int answer = fileChooser.showOpenDialog(null);
					if (answer == JFileChooser.APPROVE_OPTION) {
						txtKeyLocation.setText(fileChooser.getSelectedFile()
								.getAbsolutePath());
					}
				}
			});
			bodyPanel.add(txtKeyLocation);

			// JButton to start file encryption
			JButton btnOK = new JButton("Start encryption");
			btnOK.setFont(new Font("URW Chancery L", Font.BOLD, 20));
			btnOK.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					cryptoController.encrypt(txtSelectedPath.getText(),
							txtKeyLocation.getText());
				}
			});
			bodyPanel.add(btnOK);

			// JButton to change back into the main menu
			JButton btnCancel = new JButton("Cancel");
			btnCancel.setFont(new Font("URW Chancery L", Font.BOLD, 20));
			btnCancel.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					cryptoController.backToMenu();
				}
			});
			bodyPanel.add(btnCancel);
		}

		// Redraw the frame
		mainFrame.revalidate();
	}
}
