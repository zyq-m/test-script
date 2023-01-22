package com.softwaretesting.components;

public class TestCase {
    private String name;
    private boolean isPass;

    public TestCase(String name, boolean isPass) {
        this.name = name;
        this.isPass = isPass;
    }

    public String getName() {
        return name;
    }

    private String isPass() {
        if (isPass) {
            return "Success";
        } else {
            return "Fail";
        }
    }

    public String getTestResult() {
        return name + ": " + this.isPass();
    }
}
