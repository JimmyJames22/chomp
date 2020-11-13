import java.util.ArrayList;
import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.net.*;
import java.io.*;


public class Board implements java.io.Serializable {

    public int[] col = new int[3];
    //set up configurations - create an array list of boards and add each configuration to it as you go through the for loops
    //booleans for winning/losing
    //what number it should return
//    public boolean winning;
    public boolean winner;
    public boolean loser;

    int move;


    public Board(int j, int k, int l) {

        col[0] = j;
        col[1] = k;
        col[2] = l;
//        col[3] = m;
//        col[4] = n;
    }

    public String toString() {
        return "Board Configuration: " + col[0] + " " + col[1] + " " + col[2] + " ";
    }

    public boolean equals(Board c) {
        boolean t = true;

        for (int i = 0; i < c.col.length; i++) {
            if (col[i] != c.col[i]) {
                t = false;
                return t;
            }
        }
        return t;
    }

}

