package org.melusky.bookbash.utility.validator;

import org.passay.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by mmelusky on 2/9/2016.
 */

/*
    Custom password validator class which is derived off of the Passay Library

    http://www.passay.org/reference/
 */
public class PasswordValidator {

    public static List<String> validate(String password) {
        org.passay.PasswordValidator pv = new org.passay.PasswordValidator(Arrays.asList(
                // length between 8 and 16 characters
                new LengthRule(8, 16),

                // at least one upper-case character
                // new CharacterRule(EnglishCharacterData.UpperCase, 1),

                // at least one lower-case character
                // new CharacterRule(EnglishCharacterData.LowerCase, 1),

                // at least one digit character
                new CharacterRule(EnglishCharacterData.Digit, 1),

                // at least one symbol (special character)
                // new CharacterRule(EnglishCharacterData.Special, 1),

                // no whitespace
                new WhitespaceRule()));
        RuleResult result = pv.validate(new PasswordData(password));
        if (result.isValid()) {
            return new ArrayList<>();
        } else {
            return pv.getMessages(result);
        }
    }

}
