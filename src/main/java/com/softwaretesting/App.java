package com.softwaretesting;

import com.softwaretesting.Module.AddRecord;

public final class App {
    public static void main(String[] args) {
        AddRecord m2 = new AddRecord();

        m2.launch("https://www.oneplustwo.my/account/login");
        m2.start();
        m2.fillForm("Test Case 1", "Haziq", "Musa", "01234567890", "UniSZA", "tembila", "22200", true);
        m2.fillForm("Test Case 2", true);
        m2.fillForm("Test Case 3", "Haziq", "Musa", "0123456", "UniSZA", "tembila", "22200", false);
        m2.fillForm("Test Case 4", "Haziq", "Musa", "0123456", "UniSZA", "tembila",
                "zip12345678", false);
        m2.viewTest();
        m2.quit();
    }
}
