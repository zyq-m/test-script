package com.softwaretesting.Module;

import com.softwaretesting.components.Driver;
import com.softwaretesting.components.TestCase;
import java.util.ArrayList;

public class AddRecord extends Driver {
    private ArrayList<TestCase> testCases = new ArrayList<TestCase>();

    public AddRecord() {
        super();
    }

    public void start() {
        login();
        goToForm();
    }

    private void goToForm() {
        super.navigate(System.getenv("LINK_2"));
        super.clickButtonByXpath("//*[@id=\"PageContainer\"]/main/div/div/div[1]/div/a");
    }

    private void login() {
        super.setInputForm("CustomerEmail", System.getenv("EMAIL"));
        super.setInputForm("CustomerPassword", System.getenv("PASSWORD"));
        super.clickButtonByXpath("//*[@id=\"CustomerLoginForm\"]/form/div/input");
    }

    public void fillForm(String testName, String firstName, String lastName, String phone, String address, String city,
            String zipCode, boolean expected) {
        super.setInputForm("AddressFirstNameNew", firstName);
        super.setInputForm("AddressLastNameNew", lastName);
        super.setInputForm("AddressPhoneNew", phone);
        super.setInputForm("AddressAddress1New", address);
        // country & province
        super.setSelectForm("AddressCountryNew", "MY");
        super.setSelectForm("AddressProvinceNew", "MY-11");
        super.setInputForm("AddressCityNew", city);
        super.setInputForm("AddressZipNew", zipCode);

        try {
            Thread.sleep(800);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        super.clickButtonByXpath("//*[@id=\"address_form_new\"]/div/p[2]/input");

        checkError(testName, expected);
        try {
            Thread.sleep(800);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        goToForm();
    }

    private void checkError(String testName, boolean expected) {
        // check if element exits then asign the result
        try {
            super.findByXpath("//*[@id=\"address_form_new\"]/div/ul").isDisplayed();
            testCases.add(new TestCase(testName, true));
        } catch (Exception e) {
            if (!expected) {
                testCases.add(new TestCase(testName, false));
            } else {
                testCases.add(new TestCase(testName, true));
            }
        }
    }

    public boolean requiredField(String formValue) {
        if (formValue == "") {
            return false;
        }
        return true;
    }

    public void viewTest() {
        for (int i = 0; i < testCases.size(); i++) {
            System.out.println(testCases.get(i).getTestResult());
        }
    }
}
