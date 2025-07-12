package com.apiautomationframework.tests;

import org.testng.TestNG;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

import java.util.ArrayList;
import java.util.List;

/**
 * TestRunner class to execute multiple test classes sequentially using TestNG
 * Each test class will generate its own separate ExtentReport
 */
public class TestRunner {

    public static void main(String[] args) {
        System.out.println("🚀 Starting Sequential Test Execution with TestNG...");
        System.out.println("=====================================");
        
        long startTime = System.currentTimeMillis();
        
        try {
            // Create TestNG instance
            TestNG testNG = new TestNG();
            
            // Create XML Suite
            XmlSuite suite = new XmlSuite();
            suite.setName("Sequential Test Suite");
            suite.setParallel(XmlSuite.ParallelMode.NONE); // Sequential execution
            
            // Create XML Test for LoginTest
            XmlTest loginTest = new XmlTest(suite);
            loginTest.setName("LoginTest");
            List<XmlClass> loginClasses = new ArrayList<>();
            loginClasses.add(new XmlClass("com.apiautomationframework.tests.LoginTest"));
            loginTest.setXmlClasses(loginClasses);
            
            // Create XML Test for AccountTest
            XmlTest accountTest = new XmlTest(suite);
            accountTest.setName("AccountTest");
            List<XmlClass> accountClasses = new ArrayList<>();
            accountClasses.add(new XmlClass("com.apiautomationframework.tests.AccountTest"));
            accountTest.setXmlClasses(accountClasses);
            
            // Create XML Test for AdminTest
            XmlTest adminTest = new XmlTest(suite);
            adminTest.setName("AdminTest");
            List<XmlClass> adminClasses = new ArrayList<>();
            adminClasses.add(new XmlClass("com.apiautomationframework.tests.AdminTest"));
            adminTest.setXmlClasses(adminClasses);
            
            // Add all tests to suite
            List<XmlTest> tests = new ArrayList<>();
            tests.add(loginTest);
            tests.add(accountTest);
            tests.add(adminTest);
            suite.setTests(tests);
            
            // Set the suite to TestNG
            List<XmlSuite> suites = new ArrayList<>();
            suites.add(suite);
            testNG.setXmlSuites(suites);
            
            System.out.println("📋 Executing tests sequentially...");
            testNG.run();
            
            System.out.println("✅ All sequential tests completed successfully");
            
        } catch (Exception e) {
            System.err.println("❌ Error during sequential test execution: " + e.getMessage());
            e.printStackTrace();
        }
        
        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        
        System.out.println("=====================================");
        System.out.println("🎉 Sequential test execution completed!");
        System.out.println("⏱️  Total execution time: " + (totalTime / 1000) + " seconds");
        System.out.println("📊 Reports generated in: extent-test-report/");
        System.out.println("   ├── LoginTest/index.html");
        System.out.println("   ├── AccountTest/index.html");
        System.out.println("   └── AdminTest/index.html");
        System.out.println("=====================================");
    }
} 