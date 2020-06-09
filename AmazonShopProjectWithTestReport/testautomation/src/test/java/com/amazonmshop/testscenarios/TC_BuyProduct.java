package com.amazonmshop.testscenarios;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.amazonmshop.framework.ReadProperties;
import com.amazonmshop.screens.CheckoutScreen;
import com.amazonmshop.screens.HomeScreen;
import com.amazonmshop.screens.LoginScreen;
import com.amazonmshop.screens.MenuBar;
import com.amazonmshop.screens.ProductScreen;
import com.amazonmshop.screens.SplashScreen;
import com.experitest.client.Client;

public class TC_BuyProduct {
	public int port;
	public String projectBaseDirectory, filePath;
	protected Client client = null;
	public ReadProperties splashScreenLocators, loginScreenLocators, homeScreenLocators, productScreenLocators,
			checkoutScreenLocators, menuBarLocators, configData;
	SplashScreen splashScreen = new SplashScreen();
	HomeScreen homeScreen = new HomeScreen();
	LoginScreen loginScreen = new LoginScreen();
	ProductScreen productScreen = new ProductScreen();
	CheckoutScreen checkoutScreen = new CheckoutScreen();
	MenuBar menuBar = new MenuBar();

	@BeforeTest
	public void setUp() throws NumberFormatException, Exception {
		// Get current working directory and load property files
		splashScreenLocators = new ReadProperties("splashScreenLocators.properties");
		loginScreenLocators = new ReadProperties("loginScreenLocators.properties");
		homeScreenLocators = new ReadProperties("homeScreenLocators.properties");
		productScreenLocators = new ReadProperties("productScreenLocators.properties");
		checkoutScreenLocators = new ReadProperties("checkoutScreenLocators.properties");
		menuBarLocators = new ReadProperties("menuBarLocators.properties");
		configData = new ReadProperties("configDataFile.properties");
		filePath = configData.getElement("FilePath");

		// Initializing Seetest properties
		client = new Client(configData.getElement("Host"), Integer.parseInt(configData.getElement("Port")), true);
		client.setProjectBaseDirectory(configData.getElement("ProjectBaseDirectory"));

		/*
		 * Commented these lines, since application is not launching in instrumented
		 * mode. client.uninstall("com.amazon.mShop.android.shopping.test");
		 * client.install("com.amazon.mShop.android.shopping.test", true, false);
		 * client.launch("com.amazon.mShop.android.shopping.test", true, true);
		 * 
		 */
	}

	@BeforeMethod
	public void initializeTest() throws Exception {
		client.setReporter("html", filePath, "Login and buy a product");
		try {
			client.setDevice(configData.getElement("Device"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		client.deviceAction(configData.getElement("Orientation"));
	}

	/*
	 * Test steps in this testcase Login to application Search for a product Verify
	 * product detail in product screen Add product to cart Verify product detail in
	 * checkout screens
	 */

	@Parameters("productName")
	@Test(groups = { "seetest" })
	public void test(@Optional("soap") String productName) throws Exception {
		splashScreen.launchApp(client, splashScreenLocators, configData);
		menuBar.logoutOnLaunchApp(client, menuBarLocators);
		splashScreen.selectLoginFromSplashScreen(client, splashScreenLocators);
		loginScreen.login(client, loginScreenLocators, configData);
		menuBar.verifyAfterLogin(client, menuBarLocators, configData);
		homeScreen.searchProduct(client, homeScreenLocators, productName);
		productScreen.addToCart(client, productScreenLocators);
		checkoutScreen.verifyProductDetails(client, checkoutScreenLocators);
	}

	@AfterMethod
	public void endTestCase() throws Exception {
		menuBar.logout(client, menuBarLocators);
		client.closeAllApplications();
		client.sendText("{HOME}");
		client.generateReport(false);
	}

	@AfterTest
	public void tearDown() {
		// Releases the client so that other clients can approach the agent in the near
		// future.
		client.releaseClient();
	}

}
