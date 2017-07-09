package ru.ticketeen.util;


public class FieldCheckerUtil {
    public static boolean isPhoneValid(String phone) {
        // plus and 10 digits
        return phone.matches("\\+[0-9]{11}");
    }

    public static boolean isPasswordValid(String password) {
        // 6 digits
        return password.matches("[0-9]{6}");
    }
}
