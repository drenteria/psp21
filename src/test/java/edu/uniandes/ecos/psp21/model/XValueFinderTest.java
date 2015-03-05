package edu.uniandes.ecos.psp21.model;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for XValueFinder
 */
public class XValueFinderTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public XValueFinderTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( XValueFinderTest.class );
    }

    /**
     * Tests the search of an X value. This test
     * passes if the expected X value is as closest as the given value
     * from the assignment kit and the error is less than the allowed error configured
     * 
     */
    public void testFindXValue01()
    {
        double allowedError = 0.00001;
    	try {
        	XValueFinder finder1 = new XValueFinder(6, 0.20, allowedError);
        	finder1.findXValue();
        	assertEquals(0.55338, finder1.getXValue(), 0.0001);
        	assertTrue(finder1.getCurrentErrorValue() <= allowedError );
        	
        	XValueFinder finder2 = new XValueFinder(15, 0.45, allowedError);
        	finder2.findXValue();
        	assertEquals(1.75305, finder2.getXValue(), 0.0001);
        	assertTrue(finder2.getCurrentErrorValue() <= allowedError );
        	
        	XValueFinder finder3 = new XValueFinder(4, 0.495, allowedError);
        	finder3.findXValue();
        	assertEquals(4.60409, finder3.getXValue(), 0.01);
        	assertTrue(finder3.getCurrentErrorValue() <= allowedError );
        	
		} catch (Exception e) {
			fail(e.getMessage());
		}
        
    }
}
