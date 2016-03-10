package timon.borter;

/**
 * Imports.
 */
import timon.borter.controller.MainController;
import timon.borter.lib.Log;

/**
 * Copyright (C) 2016 Timon Borter
 * 
 * This program is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU General Public License along with
 * this program. If not, see http://www.gnu.org/licenses/.
 * 
 * ------------------------------------------------------------------------
 * 
 * Starts a new session of FileEncrypter.
 * 
 * @author Timon Borter
 * @version 1.0
 */
public class app {
	/**
	 * Static variables.
	 */
	private static MainController mainController;

	/**
	 * @param args
	 *            The default arguments of a java application.
	 */
	public static void main(String[] args) {
		// Log start
		Log.writeInformation("Application started.");

		// Create new main controller
		mainController = new MainController();

		// Start the application
		mainController.initializeApplication();
	}
}
