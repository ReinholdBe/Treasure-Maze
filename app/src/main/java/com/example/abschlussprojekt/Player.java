package com.example.abschlussprojekt;

import java.util.Random;

public class Player {
    static final char PLAYER = 'X';
    int xcoord=1;
    int ycoord=1;
    int points=0;



    Player(String[] labtext){
        Random rando = new Random();
        xcoord = rando.nextInt(labtext[0].length()-1);
        ycoord = rando.nextInt(labtext.length-1);
        //Wallspawn verhindern
        if(labtext[ycoord].charAt(xcoord) != Maze.FREE){
           new Player(labtext);
        }
        points = 0;
    }

    String[] addPlayer(String[] mazevisu){
        StringBuilder playerinmaze = new StringBuilder(mazevisu[ycoord]);
        playerinmaze.setCharAt(xcoord, PLAYER);
        mazevisu[ycoord] = playerinmaze.toString();
        return mazevisu;
    }

    String[] moveOnView(String[] mazevisu, Maze.Direction dir){

        switch(dir){
            case NORTH: StringBuilder northdelete = new StringBuilder(mazevisu[ycoord+1]);
                        northdelete.setCharAt(xcoord,Maze.FREE);
                        mazevisu[ycoord+1] = northdelete.toString();
                        StringBuilder northnew = new StringBuilder(mazevisu[ycoord]);
                        northnew.setCharAt(xcoord,PLAYER);
                        mazevisu[ycoord] = northnew.toString();
                        break;
            case SOUTH: StringBuilder southdelete = new StringBuilder(mazevisu[ycoord-1]);
                        southdelete.setCharAt(xcoord,Maze.FREE);
                        mazevisu[ycoord-1] = southdelete.toString();
                        StringBuilder southnew = new StringBuilder(mazevisu[ycoord]);
                        southnew.setCharAt(xcoord, PLAYER);
                        mazevisu[ycoord] = southnew.toString();
                        break;
            case EAST: StringBuilder eastnew = new StringBuilder(mazevisu[ycoord]);
                       eastnew.setCharAt(xcoord-1,Maze.FREE);
                       eastnew.setCharAt(xcoord,PLAYER);
                       mazevisu[ycoord] = eastnew.toString();
                       break;
            case WEST: StringBuilder westnew = new StringBuilder(mazevisu[ycoord]);
                       westnew.setCharAt(xcoord+1,Maze.FREE);
                       westnew.setCharAt(xcoord,PLAYER);
                       mazevisu[ycoord] = westnew.toString();
                       break;
        }
        return mazevisu;
    }
     String[] move(String[] mazevisu, Maze.Direction dir){
            switch (dir){
                case NORTH: if(mazevisu[ycoord-1].charAt(xcoord) != Maze.WALL){
                            if(mazevisu[ycoord-1].charAt(xcoord) == Treasure.TREASURE){ points++;}
                            ycoord--;
                            return moveOnView(mazevisu, dir);
                                }
                            break;
                case SOUTH: if(mazevisu[ycoord+1].charAt(xcoord) != Maze.WALL){
                            if(mazevisu[ycoord+1].charAt(xcoord) == Treasure.TREASURE){points++;}
                            ycoord++;
                            return moveOnView(mazevisu, dir);
                                }
                            break;
                case EAST: if(mazevisu[ycoord].charAt(xcoord+1) != Maze.WALL){
                            if(mazevisu[ycoord].charAt(xcoord+1) == Treasure.TREASURE){points++;}
                            xcoord++;
                            return moveOnView(mazevisu, dir);
                                }
                            break;
                case WEST: if(mazevisu[ycoord].charAt(xcoord-1) != Maze.WALL){
                            if(mazevisu[ycoord].charAt(xcoord-1) == Treasure.TREASURE){points++;}
                            xcoord--;
                            return moveOnView(mazevisu, dir);
                                }
                            break;
            }


         return mazevisu;
    }
}
