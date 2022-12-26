package com.softwaretesting.Module;

import com.softwaretesting.components.Driver;
import java.util.regex.Pattern;
import java.util.ArrayList;

public class AddRecord extends Driver {
    private final Pattern EMAIL_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
            Pattern.CASE_INSENSITIVE);
    private final Pattern PHONE_REGEX = Pattern.compile("\\d{10}|(?:\\d{3}-){2}\\d{4}|\\(\\d{3}\\)\\d{3}-?\\d{4}",
            Pattern.CASE_INSENSITIVE);
    private ArrayList<String> formList = new ArrayList<String>();

    public AddRecord() {
        super();
    }

    public void fillForm(String firstName, String lastName, String gender, String birthDate, String emailOrPhone,
            String password) {
        super.setInputForm("FirstName", firstName);
        super.setInputForm("LastName", lastName);
        super.setSelectForm("Gender", gender);
        super.setInputForm("birthdate", birthDate);
        super.setInputForm("EmailOrPhone", emailOrPhone);
        super.setInputForm("CreatePassword", password);

        formList.add(firstName);
        formList.add(lastName);
        formList.add(gender);
        formList.add(birthDate);
        formList.add(emailOrPhone);
        formList.add(password);
    }

    public boolean requiredField(String formValue) {
        if (formValue == "") {
            return false;
        }
        return true;
    }

    private boolean matchRegex(Pattern regex, String formValue) {
        return regex.matcher(formValue).find();
    }

    public boolean checkEmail(String id) {
        boolean email = matchRegex(EMAIL_REGEX, id);
        boolean phone = matchRegex(PHONE_REGEX, id);

        if (email == false && phone == false)
            return false;

        return true;
    }

    public void testResult() {
        for (int i = 0; i < formList.size(); i++) {
            if (i == 4) {
                System.out
                        .println("Value 5: " + (checkEmail(formList.get(i)) && requiredField(formList.get(i))));
            } else {
                System.out.println("Value " + (i + 1) + ": " + requiredField(null));
            }
        }
    }
}
