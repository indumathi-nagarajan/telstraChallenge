package com.amazonmshop.screens;

import com.amazonmshop.framework.ReadProperties;
import com.experitest.client.Client;

public class LoginScreen {
	
	public void login(Client client, ReadProperties loginScreenLocators, ReadProperties configData) throws Exception {
		client.waitForElement("NATIVE", loginScreenLocators.getElement("Login_option"), 0, 10000);
		client.click("NATIVE", loginScreenLocators.getElement("Login_option"), 0, 1);
		client.elementSendText("NATIVE", loginScreenLocators.getElement("UserId_textfield"), 0, configData.getElement("UserId"));
		client.click("NATIVE", loginScreenLocators.getElement("Continue_button"), 0, 1);
		System.out.println("Click on Continue button in login page");
		client.waitForElement("NATIVE", loginScreenLocators.getElement("Password_textfield"), 0, 10000);
		client.elementSendText("NATIVE", loginScreenLocators.getElement("Password_textfield"), 0,
				configData.getElement("Password"));
		client.click("NATIVE", loginScreenLocators.getElement("Login_button"), 0, 1);
		System.out.println("Click on Login button");

		}


}
