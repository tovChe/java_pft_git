package ru.jft.first;

import org.testng.Assert;
import org.testng.annotations.Test;

public class EquationTests {

  @Test
  public void test0() {

    Equation e = new Equation(0, 1, 1);
    Assert.assertEquals(e.rootNumber(), 0);

  }

  @Test
  public void test1() {

    Equation e = new Equation(1, 0, 1);
    Assert.assertEquals(e.rootNumber(), 1);

  }

  @Test
  public void test2() {

    Equation e = new Equation(1, 5, 0);
    Assert.assertEquals(e.rootNumber(), 2);

  }

}