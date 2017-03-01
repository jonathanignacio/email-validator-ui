package com.ignacio.validator.emailvalidator;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for Validate.
 */
public class ValidateTest 
    extends TestCase
{
	
	static String[] domains = {"hotmail.com", "mail.com", "gmail.com"};
	
    /**
     * Create the test case.
     *
     * @param testName name of the test case.
     */
    public ValidateTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested.
     */
    public static Test suite()
    {
        return new TestSuite( ValidateTest.class );
    }

    /**
     * Verify that {@link validate} correctly displays the number of passing tests.
     */
    public void testValidate(){
    	assertEquals(0, Validate.validate("cooooooooooooooooooooooooooooooooooooompletefailure")); 
    	assertEquals(2, Validate.validate("partial@failure")); //has @
    	assertEquals(2, Validate.validate("partial.failure")); //has .
    	assertEquals(3, Validate.validate("garbage.@"));
    	assertEquals(3, Validate.validate("short@sweet.com")); //correct length, wrong domain
    	assertEquals(3, Validate.validate("gettingtherebutnoooooooooooooooooootquite@gmail.com")); //correct domain, too long
    	assertEquals(4, Validate.validate("theperfectemail@gmail.com")); 
    }
    
    /**
     * Verify that the check for '@' characters functions correctly.
     */
    public void testValidateAt(){
    	assertTrue(Validate.validateAt("this@"));
    	assertFalse(Validate.validateAt("fail"));
    }
    
    
    /**
     * Verify that the check for '.' characters functions correctly.
     */
    public void testValidateDot(){
    	assertTrue(Validate.validateDot("...pass"));
    	assertTrue(Validate.validateDot(".pass"));
    	assertFalse(Validate.validateDot("fail"));
    }
    
    /**
     * Verify that the check for valid domains functions correctly.
     */
    public void testValidateEmailDomain(){
    	assertTrue(Validate.validateEmailDomain("test@gmail.com", domains)); //gmail.com is a valid domain
    	assertFalse(Validate.validateEmailDomain("bad@domain", domains));
    }
    
    /**
     * Verify that the check for valid email address length functions correctly.
     */
    public void testValidateEmailLength(){
    	assertTrue(Validate.validateEmailLength("shouldbe@good.com"));
    	assertFalse(Validate.validateEmailLength("loooooooooooooooooooooooooooooooooooooooong@lol.com")); //very long email (over 50 characters)
    }
}
