package com.getbase.onp;

import junit.framework.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertEquals;

@RunWith(JUnit4.class)
public class ONPTest {

    @Test
    public void test_onp() {
        //given
        String onpString = "2 3 + 5 *";

        //when
        int onpInt = ONP.calculate(onpString);

        //then
        assertEquals(25, onpInt);
    }

    @Test
    public void test_onp2() {
        //given
        String onpString = "12 2 3 4 * 10 5 / + * +";

        //when
        int onpInt = ONP.calculate(onpString);

        //then
        assertEquals(40, onpInt);
    }
}
