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
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import timon.borter.controller.MainController;

/**
 * Displays information about the application. License included here.
 * 
 * @author Timon Borter
 * 
 */
public class AboutPage extends Page {
	/**
	 * Static variables
	 */
	private static MainController mainController;

	/**
	 * Set up about page.
	 * 
	 * @param givenFrame
	 *            The frame which handles the pages.
	 */
	public static void show(JFrame givenFrame) {
		// Bind local variables
		mainFrame = givenFrame;

		// Create the controller
		mainController = new MainController();

		// Clear the frame
		mainFrame.getContentPane().removeAll();

		// Header region
		JPanel titelPanel = new JPanel();
		titelPanel.setBorder(BorderFactory.createEmptyBorder(0, 100, 0, 100));
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
		JPanel bodyPanel = new JPanel();
		bodyPanel.setBorder(new EmptyBorder(0, 100, 50, 100));
		bodyPanel.setLayout(new GridLayout(3, 1, 0, 5));
		mainFrame.getContentPane().add(bodyPanel, BorderLayout.CENTER);
		{
			// JLabel to show version info
			JLabel lblVersionInfo = new JLabel();
			lblVersionInfo.setText("Version 1.0, released on 03/10/3016");
			lblVersionInfo.setFont(new Font("URW Chancery L", Font.BOLD, 20));
			lblVersionInfo.setHorizontalAlignment(SwingConstants.CENTER);
			bodyPanel.add(lblVersionInfo);

			// JTextArea to show license information
			JTextArea areaLicenseInfo = new JTextArea();
			areaLicenseInfo.setBackground(bodyPanel.getBackground());
			areaLicenseInfo
					.setText("Copyright (C) 2016 Timon Borter"
							+ "\n\nThis program is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version."
							+ "\n\nThis program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details."
							+ "\n\nYou should have received a copy of the GNU General Public License along with this program. If not, see http://www.gnu.org/licenses/.");
			areaLicenseInfo.setFont(new Font("Arial", Font.BOLD, 10));
			areaLicenseInfo.setEditable(false);
			areaLicenseInfo.setWrapStyleWord(true);
			areaLicenseInfo.setLineWrap(true);

			// Make JTextArea scrollable
			JScrollPane paneScrollable = new JScrollPane(areaLicenseInfo);
			areaLicenseInfo.setCaretPosition(0);
			bodyPanel.add(paneScrollable);

			// JButton to change back into the main menu
			JButton btnAboutTheProject = new JButton("Back");
			btnAboutTheProject
					.setFont(new Font("URW Chancery L", Font.BOLD, 20));
			btnAboutTheProject.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					mainController.showMenu();
				}
			});
			bodyPanel.add(btnAboutTheProject);
		}

		// Redraw the frame
		mainFrame.revalidate();
	}
}
