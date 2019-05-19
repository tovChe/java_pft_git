package ru.jft.first;

public class First {

    public static void main(String[] args) {

        System.out.println("Hello, world");

        System.out.println(1.0 / 2.0);
        System.out.println(Math.sqrt(4));

        Point p1 = new Point(10, 10);
        Point p2 = new Point(50, 50);

        System.out.println("Расстояние между точками = " + p1.getDistance(p2));
    }


}