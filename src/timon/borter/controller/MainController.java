package timon.borter.controller;

/**
 * Imports.
 */
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;

import timon.borter.lib.Log;
import timon.borter.view.AboutPage;
import timon.borter.view.MainMenu;

/**
 * Controls the main menu. U can start to encrypt and decrypt files from here.
 * You are also able to gain some information about the project.
 * 
 * @author Timon Borter
 * 
 */
public class MainController {
	/**
	 * Static variables.
	 */
	private static JFrame mainFrame;

	/**
	 * Start the graphical user interface.
	 */
	public void initializeApplication() {
		// Log build start
		Log.writeInformation("Started creating JFrame mainFrame");

		// Create the main frame
		mainFrame = new JFrame();
		mainFrame.setSize(new Dimension(500, 350));
		mainFrame.setLocationRelativeTo(null);
		mainFrame.setResizable(false);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.getContentPane().setLayout(new BorderLayout());

		// Set up the main menu
		this.showMenu();

		// Set mainFrame visible
		mainFrame.setVisible(true);

		// Log success
		Log.writeInformation("Successfully built JFrame mainFrame");
	}

	/**
	 * Show the menu page.
	 */
	public void showMenu() {
		MainMenu.show(mainFrame);
		Log.writeInformation("Changed to menu page.");
	}

	/**
	 * Show the about page.
	 */
	public void showAbout() {
		AboutPage.show(mainFrame);
		Log.writeInformation("Changed to about page.");
	}
}