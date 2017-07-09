package ru.ticketeen.util;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static ru.ticketeen.util.FieldCheckerUtil.isPasswordValid;
import static ru.ticketeen.util.FieldCheckerUtil.isPhoneValid;

public class FieldCheckerUtilTest {
    @Test
    public void checkIsPhoneValid() throws Exception {
        assertTrue(isPhoneValid("+79523507655"));
        assertFalse(isPhoneValid("79523507655"));
        assertFalse(isPhoneValid("+79sdf507655"));
        assertFalse(isPhoneValid("+795235076559"));
        assertFalse(isPhoneValid("+7952350765"));
    }

    @Test
    public void checkIsPasswordValid() throws Exception {
        assertTrue(isPasswordValid("123456"));
        assertFalse(isPasswordValid("W23456"));
        assertFalse(isPasswordValid("12345678"));
        assertFalse(isPasswordValid("12345"));
    }
}