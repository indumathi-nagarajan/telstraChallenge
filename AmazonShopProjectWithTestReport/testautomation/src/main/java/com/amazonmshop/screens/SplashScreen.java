package com.amazonmshop.screens;

import com.amazonmshop.framework.ReadProperties;
import com.experitest.client.Client;

public class SplashScreen {

	public void launchApp(Client client, ReadProperties splashScreenLocators, ReadProperties configData)
			throws Exception {
		// Launching app manually
		client.sendText("{HOME}");
		client.click("NATIVE", splashScreenLocators.getElement("App_icon"), 0, 1);
		client.waitForElement("NATIVE", splashScreenLocators.getElement("Amazon_SplashLogo"), 0, 10000);
	}

	public void selectLoginFromSplashScreen(Client client, ReadProperties splashScreenLocators) throws Exception {
		client.waitForElement("NATIVE", splashScreenLocators.getElement("SignIn_button"), 0, 10000);
		client.verifyElementFound("NATIVE", splashScreenLocators.getElement("Amazon_SplashLogo"), 0);
		client.swipeWhileNotFound("Down", 300, 1000, "NATIVE", splashScreenLocators.getElement("SignIn_button"), 0, 500,
				2, false);
		client.click("NATIVE", splashScreenLocators.getElement("SignIn_button"), 0, 1);
		System.out.println("Select Sign In option");
	}

}
