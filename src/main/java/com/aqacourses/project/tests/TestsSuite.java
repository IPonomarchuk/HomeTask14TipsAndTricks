package com.aqacourses.project.tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/** This class works as suite. You can run as many tests as you want */
@RunWith(Suite.class)
@Suite.SuiteClasses({
    LoginAndLogoutTest.class,
    CompareQuantityOfProductsTest.class,
    ShoppingCartTest.class,
    ProductTest.class
})
public class TestsSuite {}
