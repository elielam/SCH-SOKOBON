package fr.ELBase.Vue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Console {

    public Console () {
        System.out.println();
        System.out.print("#################################################################");
        System.out.println();
        System.out.print("                          SOKOBON                                ");
        System.out.println();
        System.out.print("#################################################################");
        System.out.println();
    }

    public String mainMenu () {

        String choice = "-1";

        System.out.println();
        System.out.print("1. Game                               ");
        System.out.println();
        System.out.println();
        System.out.print("2. Map Editor                         ");
        System.out.println();
        System.out.println();
        System.out.print("#################################################################");
        System.out.println();
        System.out.println();
        System.out.println("Your choice : ");
        System.out.println();

        try {
            BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
            choice = bufferRead.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return choice;
    }

    public String[] loadGame () {

        String filename = "map";
        String heroname = "name";

        System.out.println();
        System.out.print("What's your nickname ? ");
        System.out.println();
        System.out.println();
        System.out.println("Your choice : ");
        System.out.println();

        try {
            BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
            heroname = bufferRead.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println();
        System.out.print("What level would you load ? ");
        System.out.println();
        System.out.println();
        System.out.println("Your choice : ");
        System.out.println();

        try {
            BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
            filename = bufferRead.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String[] arr = new String[2];
        arr[0] = heroname;
        arr[1] = filename;

        return arr;

    }

    public String createMap () {

        String filename = "default";

        System.out.println();
        System.out.print("1. File's name ? ");
        System.out.println();
        System.out.println();
        System.out.println("Your choice : ");
        System.out.println();

        try {
            BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
            filename = bufferRead.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return filename;

    }

    public int[] editorProperty () {

        String row = null;
        String col = null;

        System.out.println();
        System.out.print("How many row ? ");
        System.out.println();
        System.out.println();
        System.out.println("Your choice : ");
        System.out.println();

        try {
            BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
            row = bufferRead.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println();
        System.out.print("How many col ? ");
        System.out.println();
        System.out.println();
        System.out.println("Your choice : ");
        System.out.println();

        try {
            BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
            col = bufferRead.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        int []res = new int[2];
        res[0] = Integer.parseInt(row);
        res[1] = Integer.parseInt(col);

        return res;

    }

    public String[] editorMapLine(int row, int col) {

        String[] content = new String[row];

        for(int i = 0; i<row; i++) {
            String line = null;
            String lineFinal = null;
            System.out.println("Ligne "+i+" : ");
            try {
                BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
                line = bufferRead.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (line.length() < col) {
                System.out.println("Not enough column remember you need "+col+" columns !!");
                i--;
            } else if (line.length() > col){
                System.out.println("Too much column remember only "+col+" columns !!");
                i--;
            }
            else {
                content[i] = line;
            }
        }

        return content;

    }

    public void error(String type) {
        System.out.println();
        System.out.print(type+" corrupted !");
        System.out.println();
    }

    public void showMap (char [][] arr, int points, int challenges) {

        // Affichage de la map
        System.out.println();
        System.out.println();
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                System.out.print(arr[i][j] + "  ");
            }
            System.out.println();
        }
        System.out.println();
        System.out.println();

        System.out.println("Points : "+points+" || Challenges : "+challenges);
        System.out.println();
        System.out.println();
        System.out.print("#################################################");

    }

    public String moveMenu () {

        String dir = "0";

        System.out.println();
        System.out.print("                     8.Up");
        System.out.println();
        System.out.print("    4.Left                       6.Right");
        System.out.println();
        System.out.print("                    2.Down");
        System.out.println();
        System.out.print("#################################################");
        System.out.println();
        System.out.println();

        try {
            BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
            dir = bufferRead.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return dir;

    }

    public void wallError(int t) {

        switch(t) {

            case 1:

                System.out.println();
                System.out.println();
                System.out.print("There's a wall here !");
                System.out.println();

                break;

            case 2:

                System.out.println();
                System.out.println();
                System.out.print("There's a wall behind the box !");
                System.out.println();

                break;

        }


    }

    public void boxError() {

                System.out.println();
                System.out.println();
                System.out.print("There's a another one box behind this one !");
                System.out.println();

    }

    public void targetError(int t) {

        switch (t) {

            case 1:

                System.out.println();
                System.out.println();
                System.out.print("There's already a box on this emplacement !");
                System.out.println();

                break;

            case 2 :

                System.out.println();
                System.out.println();
                System.out.print("There's a target emplacement here !");
                System.out.println();

                break;

        }

    }

    public void win (String name) {
        System.out.println();
        System.out.println();
        System.out.print("!! You Win this game "+name+" try another level !!");
        System.out.println();
        System.out.println();
    }

    public void debug(char element) {
        System.out.println("Character : "+element);
    }

}
