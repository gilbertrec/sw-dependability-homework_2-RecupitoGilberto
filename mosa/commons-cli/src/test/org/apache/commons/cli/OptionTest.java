package org.apache.commons.cli;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.evosuite.runtime.EvoAssertions.*;
import org.apache.commons.cli.Option;
import org.junit.runner.RunWith;

public class OptionTest {

    //This test exercise the "getArgName()","setArgName()" and "hasArgName()" methods
    //If it use setArgName to empty string the argName
    //hasArgName should return false
  @Test
  public void test01()  throws Throwable  {
      Option option0 = new Option("", "");
      assertEquals("argName expected different","arg", option0.getArgName());

      option0.setArgName("");
      boolean boolean0 = option0.hasArgName();
      assertFalse("Option object has argName",boolean0);
  }

  // This tests checks the method getArgs()
  @Test
  public void test02()  throws Throwable  {
      Option option0 = new Option("", "", true, "");
      assertEquals("argName expected different","arg", option0.getArgName());
      assertEquals("Number of arguments expected different",1, option0.getArgs());
  }

  /* This tests initially checks the construction of two equals options.
     and the construction of the argName
     I think it's an EagerTest
     ExtractMethod: I have splitted this test in two tests.
  */
  @Test
  public void test03()  throws Throwable  {
      Option option0 = new Option("R", "R", true, "R");
      assertEquals("argName expected different","arg", option0.getArgName());
      assertTrue("Option doesn't have arguments",option0.hasArg());
  }

  /*
    Test extracted from test03, this test checks the equality of two Options
   */
  @Test
  public void EqualsOptionTest(){
      Option option0 = new Option("R", "R", true, "R");
      Option option1 = new Option("R", "R", true, "R");
      boolean boolean0 = option1.equals(option0);
      assertTrue(boolean0);
  }
    /*
       This test checks the equality of two different Options
    */
    @Test
    public void EqualsOptionTestFalse(){
        Option option0 = new Option("R", "R", true, "R");
        Option option1 = new Option("L", "R", true, "R");
        boolean boolean0 = option1.equals(option0);
        assertFalse(boolean0);
    }


//This test
  @Test
  public void test04()  throws Throwable  {
      Option option0 = new Option("VC", "VC");
      int int0 = option0.getId();
      assertFalse(option0.hasLongOpt());
      assertFalse(option0.hasArgs());
      assertEquals("ID expected different",'V', int0);
      assertEquals("argName expected different","arg", option0.getArgName());
  }

  @Test
  public void test05()  throws Throwable  {
      Option option0 = new Option("P", "P", true, "P");
      boolean boolean0 = option0.hasArgs();
      assertEquals("arg", option0.getArgName());
      assertTrue(boolean0);
      assertEquals(1, option0.getArgs());
  }

  @Test
  public void test06()  throws Throwable  {
      Option option0 = new Option("P", true, "P");
      option0.addValueForProcessing("P");
      try { 
        option0.getValue(295);
        fail("Expecting exception: IndexOutOfBoundsException");
      
      } catch(IndexOutOfBoundsException e) {
         //
         // Index: 295, Size: 1
         //
         verifyException("java.util.ArrayList", e);
      }
  }

  @Test
  public void test07()  throws Throwable  {
      Option option0 = new Option("", "", true, "");
      Option option1 = new Option("", "");
      boolean boolean0 = option0.equals(option1);
      assertEquals((-1), option1.getArgs());
      assertFalse(option1.hasLongOpt());
      assertEquals("arg", option1.getArgName());
      assertEquals(1, option0.getArgs());
      assertTrue(boolean0);
  }

  @Test
  public void test08()  throws Throwable  {
      Option option0 = new Option("", "", true, "");
      option0.getValue();
      assertEquals("arg", option0.getArgName());
      assertTrue(option0.hasArg());
  }

  @Test
  public void test09()  throws Throwable  {
      Option option0 = new Option((String) null, (String) null, true, (String) null);
      assertEquals("arg", option0.getArgName());
      
      option0.setArgName((String) null);
      option0.getArgName();
      assertEquals(1, option0.getArgs());
  }

  @Test
  public void test10()  throws Throwable  {
      Option option0 = new Option("SX", "SX");
      Option option1 = new Option("SX", "SX", false, "SX");
      boolean boolean0 = option0.equals(option1);
      assertTrue(boolean0);
      assertEquals((-1), option1.getArgs());
      assertFalse(option0.hasLongOpt());
      assertEquals("arg", option1.getArgName());
  }
}
