package vn.lequan.warrior.util;

/**
 * Created by Le Quan on 25/05/2017.
 */

public class HightScore {
   private String name;
   private int score;

    public HightScore() {
    }

    public HightScore(String name, int score) {
        this.name = name;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
