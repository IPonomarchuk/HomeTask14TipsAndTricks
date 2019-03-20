package com.aqacourses.project.pages;

import com.aqacourses.project.base.BaseTest;

public class EveningDressesPage extends DressesPage {

    /**
     * Constructor
     *
     * @param testClass
     */
    public EveningDressesPage(BaseTest testClass) {
        super(testClass);
        testClass.waitTillElementIsVisible(divPage);
    }
}
