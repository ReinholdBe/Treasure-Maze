package com.example.abschlussprojekt;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Maze laby = new Maze(12,24);



    public void printlaby(String inputlabytext){
        TextView currentview = (TextView) findViewById(R.id.lab);
        currentview.setText(inputlabytext);

    }
    public void updateScore(Player a){
        TextView currentScore = (TextView) findViewById(R.id.playerpoints);
        currentScore.setText("Score:   " + Integer.toString(a.points));
    }
    public String[] spawnTreasures(String[] lab, int anzahl){
        Random rando = new Random();
        for (int i = 0; i < anzahl; i++) {
            int x = rando.nextInt(lab[0].length()-3)+1; //Rand ignorieren
            int y = rando.nextInt(lab.length-3)+1;
            //Wallspawn verhindern
            if(lab[y].charAt(x)== Maze.FREE){
                new Treasure(x,y).addSchatz(lab);
            }else {
                spawnTreasures(lab, 1);
            }
        }
        return lab;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Random rand = new Random();
        laby.connectPerfectly(rand);
        laby.braidSomeDeadEnds(rand);

        String[] labyasarray = laby.asTileText();
        Player us = new Player(labyasarray);

        String labytext = String.join("\n", us.addPlayer(spawnTreasures(labyasarray,45)));
        printlaby(labytext);
        updateScore(us);


        Button nbu = (Button) findViewById(R.id.nbutton);
        Button sbu = (Button) findViewById(R.id.sbutton);
        Button ebu = (Button) findViewById(R.id.ebutton);
        Button wbu = (Button) findViewById(R.id.wbutton);

        nbu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String labytext = String.join("\n",us.move(labyasarray, Maze.Direction.NORTH));
                printlaby(labytext);
                updateScore(us);
            }
        });
        sbu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String labytext = String.join("\n",us.move(labyasarray, Maze.Direction.SOUTH));
                printlaby(labytext);
                updateScore(us);
            }
        });
        ebu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String labytext = String.join("\n",us.move(labyasarray, Maze.Direction.EAST));
                printlaby(labytext);
                updateScore(us);
            }
        });
        wbu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String labytext = String.join("\n",us.move(labyasarray, Maze.Direction.WEST));
                printlaby(labytext);
                updateScore(us);
            }
        });

    }
}