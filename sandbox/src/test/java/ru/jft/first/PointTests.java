package ru.jft.first;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTests {

  @Test
  public void testDistance(){
    Point p1 = new Point(10, -30);
    Point p2 = new Point(25, -45);
    Assert.assertEquals(p2.getDistance(p1), 21.0);
  }

}
