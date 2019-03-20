package com.aqacourses.project.pages;

import com.aqacourses.project.base.BaseTest;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public abstract class AbstractPage {

    // Web Elements
    @FindBy(xpath = "//div/a[@class='login']")
    private WebElement signInLink;

    @FindBy(xpath = "//div/a[@class='logout']")
    private WebElement singOutButton;

    @FindBy(xpath = "//div[@id='block_top_menu']/ul/li/a[@title='Dresses']")
    private WebElement dressesButton;

    @FindBy(xpath = "//div[@id='block_top_menu']/ul/li/a[@title='T-shirts']")
    private WebElement tShirtsButton;

    @FindBy(xpath = "//div[@class='product-container']")
    List<WebElement> listOfProducts;

    @FindBy(xpath = "//span[@class='heading-counter']")
    WebElement counterOfProducts;

    @FindBy(xpath = "//div[@id='page']")
    WebElement divPage;

    @FindBy(
            xpath =
                    "//li[@class='sfHover']//ul[@class='submenu-container clearfix first-in-line-xs']")
    private WebElement subMenu;

    @FindBy(xpath = "//a[@title='View my shopping cart']")
    private WebElement cart;

    @FindBy(xpath = "//span[@class='remove_link']/a")
    private WebElement removeIcon;

    private String EMPTY_CART_XPATH = "//span[@class='ajax_cart_no_product']";

    private String PRODUCT_DETAIL_LINK_XPATH = "//h5/a[@title='%s']";

    private String CATEGORY_LINK_XPATH = "//div[@id='block_top_menu']/ul/li/a[@title='%s']";

    private String SUB_CATEGORY_LINK_XPATH = "//li[@class='sfHover']//a[@title='%s']";

    // Instances of BaseTest
    protected BaseTest testClass;

    /**
     * Constructor
     *
     * @param testClass
     */
    public AbstractPage(BaseTest testClass) {
        this.testClass = testClass;
        PageFactory.initElements(testClass.getDriver(), this);
        testClass.waitTillElementIsVisible(divPage);
    }

    /**
     * Click on the "Sign in" link
     *
     * @return new instance of LoginPage
     */
    public LoginPage clickSignInLink() {
        testClass.waitTillElementIsVisible(signInLink);
        signInLink.click();
        return new LoginPage(testClass);
    }

    /**
     * Click on the "Sign out" button
     *
     * @return new instance of LoginPage
     */
    public LoginPage logout() {
        testClass.waitTillElementIsVisible(singOutButton);
        singOutButton.click();
        return new LoginPage(testClass);
    }

    /**
     * Click on the "Dresses" button
     *
     * @return new instance of DressesPage
     */
    public DressesPage openDressesPage() {
        testClass.waitTillElementIsVisible(dressesButton);
        dressesButton.click();
        return new DressesPage(testClass);
    }

    /**
     * Click on the "T-Shirts" button
     *
     * @return new instance of TShirtsPage
     */
    public TShirtsPage openTShirtsPage() {
        testClass.waitTillElementIsVisible(tShirtsButton);
        tShirtsButton.click();
        return new TShirtsPage(testClass);
    }

    /** Hover over the category */
    public void hoverOverTheCategory(String category) {
        Actions actions = new Actions(testClass.getDriver());
        WebDriver driver = testClass.getDriver();
        WebElement categoryLink =
                driver.findElement(By.xpath(String.format(CATEGORY_LINK_XPATH, category)));
        actions.moveToElement(categoryLink).build().perform();
        testClass.waitTillElementIsVisible(subMenu);
    }

    /**
     * Open the subCategory
     *
     * @return new instance of ProductPage
     */
    public DressesPage openSubCategory(String subCategory) {
        WebDriver driver = testClass.getDriver();
        WebElement subCategoryLink =
                driver.findElement(By.xpath(String.format(SUB_CATEGORY_LINK_XPATH, subCategory)));
        subCategoryLink.click();
        return new DressesPage(testClass);
    }

    /**
     * Open the product
     *
     * @return new instance of ProductPage
     */
    public ProductPage openProductByTitle(String title) {
        testClass.waitTillListOfElementsAreVisible(listOfProducts);
        WebDriver driver = testClass.getDriver();
        driver.findElement(By.xpath(String.format(PRODUCT_DETAIL_LINK_XPATH, title))).click();
        return new ProductPage(testClass);
    }

    /**
     * Open the product in new tab
     *
     * @return new instance of ProductPage
     */
    public ProductPage openProductByTitleInNewTab(String title) {
        testClass.waitTillListOfElementsAreVisible(listOfProducts);
        WebDriver driver = testClass.getDriver();
        String actualWindow = driver.getWindowHandle();
        driver.findElement(By.xpath(String.format(PRODUCT_DETAIL_LINK_XPATH, title)))
                .sendKeys(Keys.chord(Keys.CONTROL, Keys.RETURN));

        Set<String> windows = driver.getWindowHandles();
        String newWindow = null;
        for (String window : windows) {
            if (!window.equals(actualWindow)) {
                newWindow = window;
            }
        }
        driver.switchTo().window(newWindow);

        return new ProductPage(testClass);
    }

    /** Close tab and switch to first tab */
    public void closeTabAndSwitchToFirstTab() {
        WebDriver driver = testClass.getDriver();
        ArrayList tabs = new ArrayList(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1).toString());
        driver.close();
        driver.switchTo().window(tabs.get(0).toString());
    }

    /** Hover over the cart */
    public void hoverOverTheCart() {
        Actions actions = new Actions(testClass.getDriver());
        actions.moveToElement(cart).build().perform();
    }

    /** Remove product from cart */
    public void removeProductFromCart() {
        testClass.waitTillElementIsVisible(removeIcon);
        removeIcon.click();
    }

    /** Verify that cart is empty */
    public void verifyEmptyCart() {
        WebDriver driver = testClass.getDriver();
        WebElement emptyCart = driver.findElement(By.xpath(EMPTY_CART_XPATH));
        Assert.assertEquals("Cart isn't empty", "(empty)", emptyCart.getAttribute("innerText"));
    }

    /** Print names of all cookies */
    public void printNamesOfAllCookies() {
        Set<Cookie> cookies = testClass.getDriver().manage().getCookies();
        Iterator iterator = cookies.iterator();
        while (iterator.hasNext()) {
            Object cookie = iterator.next();
            Cookie newCookie = (Cookie) cookie;
            String nameOfCookie = newCookie.getName();
            System.out.println(nameOfCookie);
        }
    }
}
