package org.apache.commons.cli;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.evosuite.runtime.EvoAssertions.*;
import org.apache.commons.cli.Option;
import org.junit.runner.RunWith;

public class OptionTest {

    //This test exercise the "getArgName()" and "hasArgName()" methods
    //If it use setArgName to empty the argName
    //hasArgName should return false
  @Test
  public void test01()  throws Throwable  {
      Option option0 = new Option("", "");
      assertEquals("argName expected different","arg", option0.getArgName());

      option0.setArgName("");
      boolean boolean0 = option0.hasArgName();
      assertFalse("Option object has still argName",boolean0);
  }

  //
  @Test
  public void test02()  throws Throwable  {
      Option option0 = new Option("", "", true, "");
      option0.getKey();
      assertEquals("arg", option0.getArgName());
      assertEquals(1, option0.getArgs());
  }

  @Test
  public void test03()  throws Throwable  {
      Option option0 = new Option("R", "R", true, "R");
      Option option1 = new Option("R", "R", true, "R");
      boolean boolean0 = option1.equals(option0);
      assertEquals("arg", option1.getArgName());
      assertTrue(option1.hasArg());
      assertTrue(boolean0);
  }

  @Test
  public void test04()  throws Throwable  {
      Option option0 = new Option("VC", "VC");
      int int0 = option0.getId();
      assertFalse(option0.hasLongOpt());
      assertFalse(option0.hasArgs());
      assertEquals(67, int0);
      assertEquals("arg", option0.getArgName());
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
