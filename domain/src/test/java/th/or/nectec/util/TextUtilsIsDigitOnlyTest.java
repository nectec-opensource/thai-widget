package th.or.nectec.util;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Blaze on 10/15/2015.
 */
public class TextUtilsIsDigitOnlyTest {

    @Test
    public void digitOnlyStringShouldTrue(){
        assertEquals("input only digit should return true", true, TextUtils.isDigitOnly("12346"));
    }

    @Test
    public void notDigitOnlyStringShouldFalse(){
        assertEquals("input not only digit should return false", false, TextUtils.isDigitOnly("123adc"));
    }

    @Test
    public void emptyStringShouldReturnFalse(){
        assertEquals("empty should return false", false, TextUtils.isDigitOnly(""));
    }
}