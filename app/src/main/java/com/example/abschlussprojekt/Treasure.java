package com.example.abschlussprojekt;

public class Treasure {
    static final char TREASURE = 'o';
    int xcoord=3;
    int ycoord=3;

    Treasure(int x, int y){
            xcoord = x;
            ycoord = y;

    }
    String[] addSchatz(String[] mazevisu){
        StringBuilder schatzinmaze = new StringBuilder(mazevisu[ycoord]);
        schatzinmaze.setCharAt(xcoord, TREASURE);
        mazevisu[ycoord] = schatzinmaze.toString();
        return mazevisu;
    }

}
