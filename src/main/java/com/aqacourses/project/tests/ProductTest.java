package com.aqacourses.project.tests;

import com.aqacourses.project.base.BaseTest;
import com.aqacourses.project.pages.*;
import com.aqacourses.project.utils.Categories;
import org.junit.Test;

public class ProductTest extends BaseTest {

    /**
     * Open site and click on the "Sign in" link, fill in email and password to login form and click
     * on the "Sign in" button. Hover over the Women category and open Evening dresses sub category.
     * Open the product in new tab and choose color and size. Add product to shopping cart and
     * continue shopping. Hover over the cart and verify that color and size of the product in the
     * cart are the same as on the page. Remove product from cart and verify that cart is empty.
     * Close tab and switch to first tab, print names of all cookies.
     */
    @Test
    public void testProductTest() {

        // Open site
        HomePage homePage = openSite();
        log("Opened site");

        // Click on the "Sign in" link
        LoginPage loginPage = homePage.clickSignInLink();
        log("Clicked on the \"Sign in\" link");

        // Fill in email and password to login form and click on the "Sign in" button
        MyAccountPage myAccountPage = loginPage.login();
        log("Filled in email and password to login form and clicked on the \"Sign in\" button");

        // Hover over the Women category
        myAccountPage.hoverOverTheCategory(Categories.WOMEN.toString());
        log("Hovered over the Women category");

        // Open Evening dresses sub category
        DressesPage dressesPage =
                myAccountPage.openSubCategory(Categories.EVENING_DRESSES.toString());
        log("Opened Evening dresses sub category");

        // Open the product in new tab
        ProductPage productPage = dressesPage.openProductByTitleInNewTab("Printed Dress");
        log("Opened product in new tab");

        // Choose color of the product
        productPage.chooseColorOfTheProduct("Pink");
        log("Chose color of the product");

        // Choose size of the product
        productPage.chooseSizeOfTheProduct("L");
        log("Chose size of the product");

        // Add product to shopping cart and continue shopping
        productPage.addProductToShoppingCartAndContinueShopping();
        log("Added product to shopping cart and continued shopping");

        // Hover over the cart
        productPage.hoverOverTheCart();
        log("Hovered over the cart");

        // Verify that color and size of the product in the cart are the same as on the page
        productPage.verifyColorAndSize();
        log("Verified that color and size of the product in the cart are the same as on the page");

        // Remove product from cart
        productPage.removeProductFromCart();
        log("Removed product from cart");

        // Verify that cart is empty
        productPage.verifyEmptyCart();
        log("Verified that cart is empty");

        // Close tab and switch to first tab
        productPage.closeTabAndSwitchToFirstTab();
        log("Closed tab and switched to first tab");

        // Print names of all cookies
        productPage.printNamesOfAllCookies();
        log("Printed names of all cookies");

        log("Closed site");
    }
}
