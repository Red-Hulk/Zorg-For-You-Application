/*LOGIN SUBSYSTEM*/

package sample.backendklassen;

import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

//This is the class who contains validation methods
public class DataValidationForm {

    //Method to check if username is 8 or more
    public static boolean dataLength(TextField str, Label inputLabel, String validationText) {
        boolean isDataLength = true;
        String validationString = null;

        if (str.getText().length() <= 8) {
            isDataLength = false;
            validationString = validationText;

        }
        inputLabel.setText(validationString);
        return isDataLength;
    }

    //Checks if the name is filled in
    public static boolean checksName(TextField str, Label inputLabel, String validationText){
        boolean isNameFilledIn = true;
        String validationString = null;

        if (str.getText().equals("")){
            isNameFilledIn = false;
            validationString = validationText;

        }
        inputLabel.setText(validationString);
        return isNameFilledIn;

    }



    //Method checks if password has the right formatting
    public static boolean checkPassword(TextField str, Label inputLabel, String validationText) {
        boolean isPasswordCorrect = true;
        String validationString = null;

        if (!str.getText().matches(".*\\d.*") && !str.getText().matches(".*[A-Z].*")){
            isPasswordCorrect = false;
            validationString = validationText;

        }
        inputLabel.setText(validationString);
        return isPasswordCorrect;
    }

    //Method checks is both passwords are matched
    public static boolean checkPasswordMatches(TextField str, TextField str2, Label inputLabel, String validationText) {
        boolean isPasswordSame = true;
        String validationString = null;

        if (!str.getText().equals(str2.getText())){
            isPasswordSame = false;
            validationString = validationText;

        }
        inputLabel.setText(validationString);
        return isPasswordSame;
    }

    //Method to check email is the right format
    public static boolean checkEmail(TextField str, Label inputLabel, String validationText) {
        boolean isEmailCorrect = true;
        String validationString = null;

        if (!str.getText().matches("^(.+)@(.+)$")){
            isEmailCorrect = false;
            validationString = validationText;

        }
        inputLabel.setText(validationString);
        return isEmailCorrect;
    }

    //Method to check email on login page
    public static boolean checkEmailLogin(String str) {
        boolean isEmailCorrect = true;

        if (!str.matches("^(.+)@(.+)$")){
            isEmailCorrect = false;

        }
        return isEmailCorrect;
    }


    //Checks if certification professional is provided
    public static boolean checkCombobox(ComboBox combo, Label inputLabel, String validationText) {
        boolean isComboFilled = true;
        String validationString = null;

        if (combo.getValue().toString() == "Niet ingevuld!"){
            isComboFilled = false;
            validationString = validationText;

        }
        inputLabel.setText(validationString);
        return isComboFilled;
    }

}
