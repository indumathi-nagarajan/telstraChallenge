package com.amazonmshop.screens;

import com.amazonmshop.framework.ReadProperties;
import com.experitest.client.Client;

public class HomeScreen {
	public static String prodDescription, prodPrice;
	
	/*
	 * Unable to add 65-inch tv to cart, because of Covid-19, there are some
	 * delivery restrictions. So searching for health drinks
	 */
	public void searchProduct(Client client, ReadProperties homeScreenLocators, String productName) throws Exception {

		client.waitForElement("NATIVE", homeScreenLocators.getElement("Search_textField"), 0, 10000);

		// Search from text field or from action bar, based on which option is available
		if (client.isElementFound("NATIVE", homeScreenLocators.getElement("Search_textField"))) {
			client.click("NATIVE", homeScreenLocators.getElement("Search_textField"), 0, 1);
		} else {
			client.click("NATIVE", homeScreenLocators.getElement("Search_actionBar"), 0, 1);
		}

		// Clear previous search if any
		if (client.isElementFound("NATIVE", homeScreenLocators.getElement("Clear_button")))
			client.click("NATIVE", homeScreenLocators.getElement("Clear_button"), 0, 1);

		client.sendText(productName);
		client.sendText("{ENTER}");
		System.out.println("Searching for " + productName);
		client.waitForElement("NATIVE", homeScreenLocators.getElement("Search_list"), 0, 10000);

		// Store details of 2nd item from the search list, and select the same
		client.swipeWhileNotFound("Down", 300, 1000, "NATIVE", homeScreenLocators.getElement("SearchItem_price"), 1, 500, 3,
				false);
		prodDescription = client.elementGetText("NATIVE", homeScreenLocators.getElement("SearchItem_title"), 1);
		prodPrice = client.elementGetText("NATIVE", homeScreenLocators.getElement("SearchItem_price"), 1);
		client.click("NATIVE", homeScreenLocators.getElement("Search_list"), 1, 1);
		System.out.println("Selected 2nd search item from search list");
		System.out.println("***********" + prodDescription + " " + prodPrice);
	}

}
