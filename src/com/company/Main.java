package com.company;

/*
* r - read
* w - write
*
* e.g. r1x transaction 1 perform read on item x
*      w2y transaction 2 perform write on item y
* */

public class Main {

    public static void main(String[] args) {
        String t1 = "r1x w1x r1y w1y";
        String t2 = "r2z r2y w2y r2x w2x";
        String t3 = "r3y r3z w3y w3z";

        System.out.println("t1 t2 t3");
        Schedule s1 = new Schedule(t1, t2, t3);
    }
}
