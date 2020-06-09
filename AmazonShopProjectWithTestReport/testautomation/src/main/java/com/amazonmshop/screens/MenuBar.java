package com.amazonmshop.screens;

import static org.testng.Assert.assertTrue;

import com.amazonmshop.framework.ReadProperties;
import com.experitest.client.Client;

public class MenuBar {

	public void logoutOnLaunchApp(Client client, ReadProperties menuBarLocators) throws Exception {
		if (client.isElementFound("NATIVE", menuBarLocators.getElement("Menu_bar"))) {
			logout(client, menuBarLocators);
		}
	}
	
	public void logoutAfterTestcase(Client client, ReadProperties menuBarLocators) throws Exception {
		if (client.isElementFound("NATIVE", menuBarLocators.getElement("Menu_bar"))) {
			logout(client, menuBarLocators);
		}
	}

	public void logout(Client client, ReadProperties menuBarLocators) throws Exception {
		if (!client.isElementFound("NATIVE", menuBarLocators.getElement("Menu_list")))
			client.click("NATIVE", menuBarLocators.getElement("Menu_bar"), 0, 1);
		client.elementSwipeWhileNotFound("NATIVE", menuBarLocators.getElement("Menu_list"), "Down", 300, 1000, "NATIVE",
				menuBarLocators.getElement("Settings_menu"), 0, 1000, 5, false);
		client.click("NATIVE", menuBarLocators.getElement("Settings_menu"), 0, 1);
		client.click("NATIVE", menuBarLocators.getElement("Signout_menu"), 0, 1);
		client.click("NATIVE", menuBarLocators.getElement("Signout_button"), 0, 1);
		client.waitForElementToVanish("NATIVE", menuBarLocators.getElement("Signout_button"), 0, 10000);
		System.out.println("You are Signed out");
	}

	public void verifyAfterLogin(Client client, ReadProperties menuBarLocators, ReadProperties configData)
			throws Exception {
		client.waitForElement("NATIVE", menuBarLocators.getElement("Menu_bar"), 0, 10000);
		if (client.isElementFound("NATIVE", menuBarLocators.getElement("Language_notification"))) {
			client.click("NATIVE", menuBarLocators.getElement("Language_notification"), 0, 1);
		}
		client.click("NATIVE", menuBarLocators.getElement("Menu_bar"), 0, 1);
		assertTrue(client.elementGetText("NATIVE", menuBarLocators.getElement("SignIn_menu"), 0)
				.contains(configData.getElement("Username")));
		System.out.println("Login success");
		client.click("NATIVE", menuBarLocators.getElement("SignIn_menu"), 0, 1);

	}
}
