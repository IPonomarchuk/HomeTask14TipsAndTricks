package com.aqacourses.project.pages;

import com.aqacourses.project.base.BaseTest;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class ProductPage extends AbstractPage {

    // Web elements
    @FindBy(xpath = "//div[@class='breadcrumb clearfix']")
    private WebElement fullBreadcrumb;

    @FindBy(xpath = "//span[contains(text(),'Add to cart')]")
    private WebElement addToCartButton;

    @FindBy(xpath = "//span[contains(text(),'Proceed to checkout')]")
    private WebElement proceedToCheckoutButton;

    @FindBy(xpath = "//span[@title='Continue shopping']")
    private WebElement continueShoppingButton;

    @FindBy(xpath = "//select[@id='group_1']")
    private WebElement sizeDropdown;

    @FindBy(xpath = "//div[@class='product-atributes']/a")
    private WebElement productAttributes;

    @FindBy(xpath = "//li[@class='selected']/a")
    private WebElement colorSelected;

    private String COLOR_XPATH = "//a[@title='%s']";

    /**
     * Constructor
     *
     * @param testClass
     */
    public ProductPage(BaseTest testClass) {
        super(testClass);
        testClass.waitTillElementIsVisible(divPage);
    }

    /** Verify breadcrumb on the ProductPage */
    public void verifyBreadcrumb(String breadcrumb) {
        String stringBreadcrumb = fullBreadcrumb.getText().replaceFirst("> ", "");
        Assert.assertEquals(
                "Breadcrumb isn't correct",
                breadcrumb,
                stringBreadcrumb.substring(0, stringBreadcrumb.lastIndexOf(">")));
    }

    /**
     * Add product to shopping cart and proceed to checkout
     *
     * @return new instance of ShoppingCartPage
     */
    public ShoppingCartPage addProductToShoppingCartAndProceedToCheckout() {
        testClass.waitTillElementIsVisible(addToCartButton);
        addToCartButton.click();
        testClass.waitTillElementIsVisible(proceedToCheckoutButton);
        proceedToCheckoutButton.click();
        return new ShoppingCartPage(testClass);
    }

    /**
     * Add product to shopping cart and continue shopping
     *
     * @return new instance of ShoppingCartPage
     */
    public ProductPage addProductToShoppingCartAndContinueShopping() {
        testClass.waitTillElementIsVisible(addToCartButton);
        addToCartButton.click();
        testClass.waitTillElementIsVisible(continueShoppingButton);
        continueShoppingButton.click();
        return new ProductPage(testClass);
    }

    /**
     * Choose color of the product
     *
     * @param color
     */
    public void chooseColorOfTheProduct(String color) {
        WebDriver driver = testClass.getDriver();
        driver.findElement(By.xpath(String.format(COLOR_XPATH, color))).click();
    }

    /**
     * Choose size of the product
     *
     * @param size
     */
    public void chooseSizeOfTheProduct(String size) {
        Select dropdown = new Select(sizeDropdown);
        dropdown.selectByVisibleText(size);
    }

    /** Verify that color and size of the product in the cart are the same as on the page */
    public void verifyColorAndSize() {
        Select dropdown = new Select(sizeDropdown);
        String sizeSelected = dropdown.getFirstSelectedOption().getText();
        String actualProductAttributes = colorSelected.getAttribute("title") + ", " + sizeSelected;
        Assert.assertEquals(
                "Color and size of the product in the cart aren't the same as on the page",
                actualProductAttributes,
                productAttributes.getAttribute("innerText"));
    }
}
