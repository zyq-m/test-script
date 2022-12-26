package com.softwaretesting;

import com.softwaretesting.Module.AddRecord;

public final class App {
    /**
     * 
     * @param args The arguments of the program.
     */
    public static void main(String[] args) {
        AddRecord m2 = new AddRecord();
        m2.launch("https://www.oneplustwo.my/account/register");
        m2.fillForm("Haziq", "Musa", "male", "2001", "haziq@mail", "password");
        m2.testResult();
        m2.quit();
    }
}
