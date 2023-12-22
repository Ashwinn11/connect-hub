package com.connect.authentication.validator;

import com.connect.authentication.model.Signup;

import java.util.regex.*;
class InvalidSignupDataException extends Exception {
    public InvalidSignupDataException(String errorMessage) {
        super(errorMessage);
    }
}
public class SignupValidator {

    public boolean validateSignup(Signup signup) throws InvalidSignupDataException {
        if (!signup.getFirstName().matches("^[a-zA-Z]+$") || !signup.getLastName().matches("^[a-zA-Z]+$")) {
            throw new InvalidSignupDataException("First name and last name should contain only letters.");
        }

        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(signup.getEmailId());
        if (!matcher.matches()) {
            throw new InvalidSignupDataException("Invalid email format.");
        }

        // Check password length and character types (letters, numbers, special characters)
        if (signup.getPassword().length() < 8 || !signup.getPassword().matches("^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[@#$%^&+=]).*$")) {
            throw new InvalidSignupDataException("Password should be at least 8 characters long and include uppercase, lowercase, digit, and special character.");
        }

        // Validate mobile number length (strictly 10 digits)
        if (signup.getMobileNo().length() != 10 || !signup.getMobileNo().matches("[0-9]+")) {
            throw new InvalidSignupDataException("Mobile number should be exactly 10 digits long.");
        }

        return true;
    }


}
