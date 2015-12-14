package com.getbase.onp;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertEquals;

@RunWith(JUnit4.class)
public class ONPTest {

    @Test
    public void test_onp_simple_example() {
        //given
        String onpString = "2 3 + 5 *";

        //when
        int onpInt = ONP.calculate(onpString);

        //then
        assertEquals(25, onpInt);
    }

    @Test
    public void test_onp_long_example() {
        //given
        String onpString = "12 2 3 4 * 10 5 / + * +";

        //when
        int onpInt = ONP.calculate(onpString);

        //then
        assertEquals(40, onpInt);
    }

    @Test
    public void test_onp_with_function() {
        //given
        String onpString = "2 5 1 quadfun 5 *";

        //when
        int onpInt = ONP.calculate(onpString);

        //then
        assertEquals(40, onpInt);
    }
}
