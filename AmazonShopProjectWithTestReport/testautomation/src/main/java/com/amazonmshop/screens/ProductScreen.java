package com.amazonmshop.screens;

import static org.testng.Assert.assertTrue;

import com.amazonmshop.framework.ReadProperties;
import com.experitest.client.Client;

public class ProductScreen extends HomeScreen {

	public void addToCart(Client client, ReadProperties productScreenLocators) throws Exception {
		client.waitForElement("NATIVE", productScreenLocators.getElement("Product_title"), 0, 10000);
		
		// validating product description and price
		String description = client.elementGetText("NATIVE", productScreenLocators.getElement("Product_title"), 0);
		client.swipeWhileNotFound("Down", 300, 1000, "NATIVE", productScreenLocators.getElement("Product_price"), 0, 500, 2, false);
		String price = client.elementGetText("NATIVE", productScreenLocators.getElement("Product_price"), 0).replace("rupees ", "");
		assertTrue(HomeScreen.prodDescription.contains(description), "Product description validated");
		assertTrue(HomeScreen.prodPrice.contains(price), "Product price validated");

		// Add to cart
		client.swipeWhileNotFound("Down", 300, 1000, "NATIVE", productScreenLocators.getElement("AddToCart_button"), 0, 500, 5,
				false);
		client.click("NATIVE", productScreenLocators.getElement("AddToCart_button"), 0, 1);
		System.out.println("Click on Add to Cart button");

		// Goto checkout screen
		client.click("NATIVE", productScreenLocators.getElement("Cart_icon"), 0, 1);
	}

}
