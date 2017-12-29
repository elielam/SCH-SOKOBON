package fr.ELBase.Model;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Map {

    private char [][]map  = null;
    private int row;
    private int col;
    private int []pos = null;

    public Map (int row , int col) {

        this.row = row;
        this.col = col;
        this.map = new char[this.row][this.col];

        // Initialisation du tableau à zéro
        for(int i = 0; i< this.row; i++){
            for(int j = 0; j < this.col; j++){
                this.map[i][j] = '0';
            }
        }

    }

    public int[] initialiseMap(String content) {

        int posI = 0;
        int posJ = 0;
        int challenges = 0;

        // Explode retour a la ligne
        String[] lines = content.split("\r\n|\r|\n");


        for(int i = 1; i< lines.length; i++){
            String[] entry = lines[i].split(" ");
            for(int j = 0; j < entry.length; j++){
                this.map[i-1][j] = (entry[j].charAt(0));
                if (entry[j].charAt(0) == 'O') {
                    challenges += 1;
                }
                else if (entry[j].charAt(0) == 'X') {
                    posI = i;
                    posJ = j;
                }
            }
        }

        int[] res = new int [3];
        res[0] = challenges;
        res[1] = posI-1;
        res[2] = posJ;

        return res;

    }

    public char[][] getMap() {
        return map;
    }

    public void setMap(char[][] map) {
        this.map = map;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public int[] getPos() {
        return pos;
    }

    public void setPos(int[] pos) {
        this.pos = pos;
    }
}
