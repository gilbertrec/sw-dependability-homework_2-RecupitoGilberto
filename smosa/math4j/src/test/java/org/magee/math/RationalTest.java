package org.magee.math;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.evosuite.runtime.EvoAssertions.*;
import org.junit.runner.RunWith;
import org.magee.math.Rational;

public class RationalTest {
    //This is the test class of Rational class
    //that exercise methods that do the arithmetic operations of a rational number

    //This test shows the bug in the add method that declares
    //an integer number as a rational with 0 as denominator
    //so it shouldn't launch the exception and pass the test here
    @Test
    public void test01()  throws Throwable  {
        Rational rational0 = new Rational(762L, 762L);
        // Undeclared exception!
        try {
            rational0.add(762L);
            fail("Expecting exception: NumberFormatException");

        } catch(NumberFormatException e) {
            //
            // Cannot create a Rational object with zero as the denominator
            //
            verifyException("org.magee.math.Rational", e);
        }
    }
    //This test instead checks correctly the launch of the exception "NumberFormatException
    @Test
    public void NumberFormatExceptionTest()  throws Throwable  {
        Rational rational0 = new Rational(762L, 762L);
        // Undeclared exception!
        try {
            rational0.add(new Rational(1L,0L));
            fail("Expecting exception: NumberFormatException");

        } catch(NumberFormatException e) {
            //
            // Cannot create a Rational object with zero as the denominator
            //
            verifyException("org.magee.math.Rational", e);
        }
    }

    // Test for the add method with a Rational Object and it shows the following bug:
    // the math operational order is not respected, uses pars.
    @Test
    public void addTestWithRationalObject() throws Throwable{
        Rational rational0 = new Rational(1L,1L);
        rational0.add(new Rational(1L,2L));
        assertEquals(1.5,rational0.doubleValue(),0.01);
    }

    // this test shows the bug in the add method with an integer parameter that an integer is declared as
    // a rational with 0 as denominator, instead is 1
    @Test
    public void addTestWithInteger() throws Throwable{
        Rational rational0 = new Rational(1L,1L);
        rational0.add(1L);
        assertEquals(2,rational0.longValue());
    }

    // Test that exercises the methods longValue() and doubleValue()
    @Test
    public void test02()  throws Throwable  {
        Rational rational0 = new Rational((-1L), (-661L));
        long long0 = rational0.longValue();
        assertEquals("Long Value is not correct",0L, long0);
        assertEquals("Double Value is not correct",0.0015128593040847202, rational0.doubleValue(), 0.01);
    }

    @Test
    public void LongValueAndDoubleValueNegativeTest(){
        Rational rational0 = new Rational((-322L), (160L));
        long long0 = rational0.longValue();
        assertEquals("Long Value is not correct",-2L, long0);
        assertEquals("Double Value is not correct",-2.0125, rational0.doubleValue(), 0.01);
    }


    //This test exercises the method multiply() and the floatValue() method
    //multiplying a rational number for its self
    //This test was incorrect because of the bug in multiply method
    //changed the second assertion with the real expected value
    @Test
    public void test03()  throws Throwable  {
        Rational rational0 = new Rational((-1431L), (-1431L));
        Rational rational1 = rational0.multiply(rational0);
        assertEquals("Long value expected different",1L, rational1.longValue());
        assertEquals("Float value expected different",1F, rational1.floatValue(), 0.01F);
    }


    //This test exercises the method multiply() and the byteValue() method
    //multiplying a rational number for its self
    @Test
    public void test04()  throws Throwable  {
        Rational rational0 = new Rational(1L, 1L);
        Rational rational1 = rational0.multiply(rational0);
        assertEquals("Integer value expected different",1, rational1.intValue());
        assertEquals("Byte value expected different",(byte)1, rational1.byteValue());
    }

    //This test exercises the multiply method between
    //two rational numbers
    @Test
    public void multiplyDifferentTest() throws Throwable {
        Rational rational0 = new Rational(3L, 2L);
        Rational rational1 = rational0.multiply(new Rational(2L,4L));
        assertEquals("Long value expected different",0L, rational1.longValue());
        assertEquals("Double value expected different",0.75, rational1.doubleValue(),0.001);
    }

    //This test exercises the multiply method between
    //two rational numbers
    @Test
    public void multiplyDifferentNegativeTest() throws Throwable {
        Rational rational0 = new Rational(-3L, 2L);
        Rational rational1 = rational0.multiply(new Rational(2L,4L));
        assertEquals("Long value expected different",0L, rational1.longValue());
        assertEquals("Double value expected different",-0.75, rational1.doubleValue(),0.001);
    }
    //This test exercises the multiply method between
    //two rational numbers, one negative
    @Test
    public void multiplyDifferentBothNegativeTest() throws Throwable {
        Rational rational0 = new Rational(-3L, 2L);
        Rational rational1 = rational0.multiply(new Rational(-2L,4L));
        assertEquals("Long value expected different",0L, rational1.longValue());
        assertEquals("Double value expected different",0.75, rational1.doubleValue(),0.001);
    }

    //This test exercise the method pow()
    //this test checked initially only the numerator
    //byte value can't be checked here because the result is out of range
    @Test
    public void test05()  throws Throwable  {
        Rational rational0 = new Rational(6L, (-2L));
        Rational rational1 = rational0.pow(5);
        assertEquals("Numerator expected different",7776L, rational1.numerator);
        assertEquals("Denominator expected different", -32L,rational1.denominator);
    }

    // This test exercises the method reduce()
    // This test shows a bug. I think that the problem is
    // the function control "if the numerator is less than 1",
    @Test
    public void reduceTest() throws Throwable {
        Rational rational = new Rational (8L,8L);
        Rational result = rational.reduce();
        assertEquals("Numerator expected different",1L,result.numerator);
        assertEquals("Denominator expected different",1L,result.denominator);
    }

    @Test
    public void reduceTest2() throws Throwable {
        Rational rational = new Rational (2L,8L);
        Rational result = rational.reduce();
        assertEquals("Numerator expected different",1L,result.numerator);
        assertEquals("Denominator expected different",4L,result.denominator);
    }

    @Test
    public void reduceTestNegative() throws Throwable {
        Rational rational = new Rational (-2L,8L);
        Rational result = rational.reduce();
        assertEquals("Numerator expected different",-1L,result.numerator);
        assertEquals("Denominator expected different",4L,result.denominator);
    }

    //This test exercise the method subtract()
    //It has a bug because if I subtract a positive number x
    //it should do y-x but it perform y+x
    //this bug is in method subtract() because it first create a new Rational with -1 as denominator
    //then use the method negate so x -> -x and a Rational with (-x,-1) is positive.
    //calling then the method add, it performs an addition.
    @Test
    public void test06()  throws Throwable  {
        Rational rational0 = new Rational((-5L), (-1L));
        Rational rational1 = rational0.subtract((2L));
        assertEquals("Double value expected different",3.0, rational1.doubleValue(), 0.01);
        assertEquals("Resulting Denominator expected different",1L, rational1.denominator);
    }
    //Test that exercise the subtract() method between a positive rational and negative long number
    @Test
    public void subtractTestNegative() throws Throwable {
        Rational rational0 = new Rational((-5L), (-1L));
        Rational rational1 = rational0.subtract(-5L);
        assertEquals("Double value expected different",10.0, rational0.doubleValue(),0.01);
        assertEquals("Resulting Denominator expected different",1L, rational1.denominator);

    }

    //This test exercise the method abs()
    //this method has a bug in the denominator condition.
    @Test
    public void test07()  throws Throwable  {
        Rational rational0 = new Rational(-1L, (-1802L));
        Rational rational1 = rational0.abs();
        assertEquals("Resulting Numerator expected different",1L, rational1.numerator);
        assertEquals("Resulting Denominator expected different",(1802L), rational1.denominator);
    }

    //This test exercise the method abs()
    //this method has a bug in the denominator condition.
    @Test
    public void absTestNegative()  throws Throwable  {
        Rational rational0 = new Rational(-1L, (1802L));
        Rational rational1 = rational0.abs();
        assertEquals("Resulting Numerator expected different",1L, rational1.numerator);
        assertEquals("Resulting Denominator expected different",(1802L), rational1.denominator);
    }

    //This test exercise the method abs()
    //this method has a bug in the denominator condition.
    @Test
    public void absTestPositive()  throws Throwable  {
        Rational rational0 = new Rational(1L, (1802L));
        Rational rational1 = rational0.abs();
        assertEquals("Resulting Numerator expected different",1L, rational1.numerator);
        assertEquals("Resulting Denominator expected different",(1802L), rational1.denominator);
    }
    // This method exercise the method negate()
    // 
    @Test
    public void test08()  throws Throwable  {
        Rational rational0 = new Rational(887L, 887L);
        Rational rational1 = rational0.negate();
        assertEquals((byte) (-1), rational1.byteValue());
        assertEquals(1, rational0.intValue());
    }

    @Test
    public void test09()  throws Throwable  {
        Rational rational0 = new Rational(0L, 1090L);
        double double0 = rational0.doubleValue();
        assertEquals(1090L, rational0.denominator);
        assertEquals(0.0, double0, 0.01);
    }

    @Test
    public void test10()  throws Throwable  {
        Rational rational0 = new Rational(1184L, 73L);
        Rational rational1 = rational0.abs();
        assertEquals(1184L, rational0.numerator);
        assertEquals(16.21917808219178, rational1.doubleValue(), 0.01);
    }
}


//Other observations:
/*
1)
 I think it's better to use the same method in the several tests for getting the value
 for the assertion and then test singularly each get method,
 but the tool exercises the code in this way in order to
 maximize the statement coverage and minimize the number of tests,
 and in this way it exercises each single method without additional tests
 */