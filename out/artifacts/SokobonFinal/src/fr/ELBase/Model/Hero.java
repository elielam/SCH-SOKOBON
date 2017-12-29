package fr.ELBase.Model;

public class Hero {

    private String name;
    private int[] pos;

    public Hero (String name, int []pos) {

        // Attribution des variables
        this.name = name;
        this.pos = pos;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int[] getPos() {
        return pos;
    }

    public void setPos(int[] pos) {
        this.pos = pos;
    }
}
