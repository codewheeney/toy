package org.example;


import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/**
 * Unit test for simple App.
 */
public class AppTest {

    @BeforeClass
    public void beforeClass() {
        System.out.println("BeforeClass");
    }

    @BeforeSuite(alwaysRun = true)
    public void beforeSuite() {
        System.out.println("BeforeSuite");
    }

    @BeforeTest
    public void beforeTest() {
        System.out.println("BeforeTest");
    }

    @BeforeMethod
    public void beforeMethod() {
        System.out.println("BeforeMethod");
    }

    @Test
    public void shouldAnswerWithTrue() {
        System.out.print("Test");
    }
}
