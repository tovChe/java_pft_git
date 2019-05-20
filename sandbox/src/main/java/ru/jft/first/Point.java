package ru.jft.first;

public class Point {

  double x;
  double y;

  public Point(double x, double y) {
    this.x = x;
    this.y = y;
  }

  public double getDistance(Point p1) {
    return Math.sqrt(Math.pow(x-p1.x, 2) + Math.pow(y - p1.y, 2));
  }
}