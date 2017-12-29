package fr.ELBase.Controller;

import fr.ELBase.Model.Hero;
import fr.ELBase.Model.Map;
import fr.ELBase.Vue.Console;

import java.io.*;
import java.util.Timer;
import java.util.concurrent.CompletableFuture;


public class Manager {

    // Variables Globales
    private Console console = null;
    private Hero hero = null;
    private Map map = null;

    private String row;
    private String col;

    private String filename = null;

    private int points = 0;
    private int challenges = 0;

    // Constructeur
    public Manager () {

        this.console = new Console();
        String choice = console.mainMenu();

        switch (choice) {

            case "0":
                // Exit
                break;

            case "1":
                // Game
                String []fRes = this.console.loadGame();
                this.filename = fRes[1];
                String []sRes = this.initialise();
                this.map = new Map(Integer.parseInt(sRes[1]), Integer.parseInt(sRes[2]));
                int []tRes = this.map.initialiseMap(sRes[0]);
                this.challenges = tRes[0];
                int []pos = new int[2];
                pos[0] = tRes[1];
                pos[1] = tRes[2];

                this.hero = new Hero(fRes[0], pos);

                console.showMap(this.map.getMap(), this.points, this.challenges);

                while (this.move()) {
                    console.showMap(this.map.getMap(), this.points, this.challenges);
                }
                break;

            case "2":
                // Map editor
                this.filename = this.console.createMap();
                this.createMap();
                break;

        }

    }

    private void createMap () {

        int []dimensions = this.console.editorProperty();

        File file = new File("maps/"+this.filename+".sok");

        PrintWriter writer = null;
        try {
            writer = new PrintWriter(file.getAbsoluteFile(), "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }

        String[] content = this.console.editorMapLine(dimensions[0], dimensions[1]);

        writer.println(dimensions[0]+" "+dimensions[1]);

        for (String res : content) {
            char []temp = res.toCharArray();
            String tempLine = null;

            for ( char result : temp) {
                tempLine += result+" ";
            }

            tempLine = tempLine.replaceAll("null", "");

            writer.println(tempLine);

        }

        System.out.println();
        System.out.println();
        System.out.print("Relunch the game en play with your new level !!");

        writer.close();
    }

    public String[] initialise () {

        int challenges = 0;

        String content = null;

        File file = new File("maps/"+this.filename+".sok");
        FileReader reader = null;
        try {
            reader = new FileReader(file);
            char[] chars = new char[(int) file.length()];
            reader.read(chars);
            content = new String(chars);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String[] lines = content.split("\r\n|\r|\n");
        for(int i = 0; i< 1; i++) {
            String[] entry = lines[i].split(" ");
            if (entry.length > 2) {
                this.console.error("File");
            } else {
                row = entry[0];
                col = entry[1];
            }
        }

        String res[] = new String[3];
        res[0] = content;
        res[1] = row;
        res[2] = col;

        return res;

    }

    private boolean move() {

        String dir = this.console.moveMenu();

        char[][]map = this.map.getMap();
        int[] pos= this.hero.getPos();

        switch (dir) {

            case "0":
                return false;

            case "8":

                if (map[pos[0]-1][pos[1]] == '=') {

                    this.console.wallError(1);

                }
                else if (map[pos[0]-1][pos[1]] == 'B') {
                    if (map[pos[0]-2][pos[1]] == '=') {

                        this.console.wallError(2);

                    } else if (map[pos[0]-2][pos[1]] == 'B') {

                        this.console.boxError();

                    } else if (map[pos[0]-2][pos[1]] == 'O') {
                        map[pos[0]-2][pos[1]] = '!';
                        map[pos[0]][pos[1]] = '0';
                        map[pos[0] - 1][pos[1]] = 'X';
                        this.points += 1;
                        pos[0] -= 1;

                    }
                    else if(map[pos[0]-2][pos[1]] == '!') {
                        this.console.targetError(1);
                    }
                    else {
                        map[pos[0]-2][pos[1]] = 'B';
                        map[pos[0]][pos[1]] = '0';
                        map[pos[0] - 1][pos[1]] = 'X';
                        pos[0] -= 1;
                    }
                }
                else if(map[pos[0]][pos[1]-1] == 'O' || map[pos[0]][pos[1]-1] == '!') {
                    this.console.targetError(2);
                }
                else {

                    map[pos[0]][pos[1]] = '0';
                    map[pos[0] - 1][pos[1]] = 'X';
                    pos[0] -= 1;

                }

                break;

            case "2":

                if (map[pos[0]+1][pos[1]] == '=') {

                    this.console.wallError(1);

                }
                else if (map[pos[0]+1][pos[1]] == 'B') {
                    if (map[pos[0]+2][pos[1]] == '=') {

                        this.console.wallError(2);

                    } else if (map[pos[0]+2][pos[1]] == 'B') {

                        this.console.boxError();

                    } else if (map[pos[0]+2][pos[1]] == 'O') {
                        map[pos[0]+2][pos[1]] = '!';
                        map[pos[0]][pos[1]] = '0';
                        map[pos[0] + 1][pos[1]] = 'X';
                        this.points += 1;
                        pos[0] += 1;

                    }
                    else if(map[pos[0]+2][pos[1]] == '!') {
                        this.console.targetError(1);
                    }
                    else {
                        map[pos[0]+2][pos[1]] = 'B';
                        map[pos[0]][pos[1]] = '0';
                        map[pos[0] + 1][pos[1]] = 'X';
                        pos[0] += 1;
                    }
                }
                else if(map[pos[0]+1][pos[1]] == 'O' || map[pos[0]+1][pos[1]] == '!') {
                    this.console.targetError(2);
                }
                else {

                    map[pos[0]][pos[1]] = '0';
                    map[pos[0] + 1][pos[1]] = 'X';
                    pos[0] += 1;

                }

                break;

            case "4":

                if (map[pos[0]][pos[1]-1] == '=') {

                    this.console.wallError(1);

                }
                else if (map[pos[0]][pos[1]-1] == 'B') {
                    if (map[pos[0]][pos[1]-2] == '=') {

                        this.console.wallError(2);

                    } else if (map[pos[0]][pos[1]-2] == 'B') {

                        this.console.boxError();

                    } else if (map[pos[0]][pos[1]-2] == 'O') {
                        map[pos[0]][pos[1]-2] = '!';
                        map[pos[0]][pos[1]] = '0';
                        map[pos[0]][pos[1]-1] = 'X';
                        this.points += 1;
                        pos[1] -= 1;

                    }
                    else if(map[pos[0]][pos[1]-2] == '!') {
                        this.console.targetError(1);
                    }
                    else {
                        map[pos[0]][pos[1]-2] = 'B';
                        map[pos[0]][pos[1]] = '0';
                        map[pos[0]][pos[1]-1] = 'X';
                        pos[1] -= 1;
                    }
                }
                else if(map[pos[0]][pos[1]-1] == 'O' || map[pos[0]][pos[1]-1] == '!') {
                    this.console.targetError(2);
                }
                else {

                    map[pos[0]][pos[1]] = '0';
                    map[pos[0]][pos[1]-1] = 'X';
                    pos[1] -= 1;

                }

                break;

            case "6":

                if (map[pos[0]][pos[1]+1] == '=') {

                    this.console.wallError(1);

                }

                else if (map[pos[0]][pos[1]+1] == 'B') {

                    if (map[pos[0]][pos[1] + 2] == '=') {

                        this.console.wallError(2);

                    } else if (map[pos[0]][pos[1] + 2] == 'B') {

                        this.console.boxError();

                    } else if (map[pos[0]][pos[1] + 2] == 'O') {
                        map[pos[0]][pos[1] + 2] = '!';
                        map[pos[0]][pos[1]] = '0';
                        map[pos[0]][pos[1]+1] = 'X';
                        this.points += 1;
                        pos[1] += 1;

                    }
                    else if(map[pos[0]][pos[1]+2] == '!') {
                        this.console.targetError(1);
                    }
                    else {
                        map[pos[0]][pos[1] + 2] = 'B';
                        map[pos[0]][pos[1]] = '0';
                        map[pos[0]][pos[1]+1] = 'X';
                        pos[1] += 1;
                    }
                }
                else if(map[pos[0]][pos[1]+1] == 'O' || map[pos[0]][pos[1]+1] == '!') {
                    this.console.targetError(2);
                }
                else {

                    map[pos[0]][pos[1]] = '0';
                    map[pos[0]][pos[1]+1] = 'X';
                    pos[1] += 1;

                }

                break;
        }

        this.map.setMap(map);
        this.hero.setPos(pos);

        if(this.winCheck()) {
            this.console.win(this.hero.getName());
            this.console.showMap(this.map.getMap(), this.points, this.challenges);
            return false;
        } else {
            return true;
        }



    }

    private boolean winCheck() {
        if (this.points == this.challenges) {
            return true;
        } else {
            return false;
        }
    }

}
