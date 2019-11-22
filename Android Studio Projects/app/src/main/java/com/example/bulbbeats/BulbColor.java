package com.example.bulbbeats;

public class BulbColor {
    private String name;
    private int one_hue;
    private String one_on;
    private int one_bri;
    private int one_sat;
    private int two_hue;
    private String two_on;
    private int two_bri;
    private int two_sat;
    private int three_hue;
    private String three_on;
    private int three_bri;
    private int three_sat;


    public BulbColor(String name, int one_hue, String one_on, int one_bri, int  one_sat, int  two_hue, String two_on, int  two_bri, int  two_sat, int  three_hue, String three_on, int  three_bri, int three_sat) {
        this.name = name;
        this.one_hue = one_hue;
        this.one_on = one_on;
        this.one_bri = one_bri;
        this.one_sat = one_sat;
        this.two_hue = two_hue;
        this.two_on = two_on;
        this.two_bri = two_bri;
        this.two_sat = two_sat;
        this.three_hue = three_hue;
        this.three_on = three_on;
        this.three_bri = three_bri;
        this.three_sat = three_sat;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getOne_hue() {
        return one_hue;
    }

    public void setOne_hue(int one_hue) {
        this.one_hue = one_hue;
    }

    public String getOne_on() {
        return one_on;
    }

    public void setOne_on(String one_on) {
        this.one_on = one_on;
    }

    public int getOne_bri() {
        return one_bri;
    }

    public void setOne_bri(int one_bri) {
        this.one_bri = one_bri;
    }

    public int getOne_sat() {
        return one_sat;
    }

    public void setOne_sat(int one_sat) {
        this.one_sat = one_sat;
    }

    public int getTwo_hue() {
        return two_hue;
    }

    public void setTwo_hue(int two_hue) {
        this.two_hue = two_hue;
    }

    public String getTwo_on() {
        return two_on;
    }

    public void setTwo_on(String two_on) {
        this.two_on = two_on;
    }

    public int getTwo_bri() {
        return two_bri;
    }

    public void setTwo_bri(int two_bri) {
        this.two_bri = two_bri;
    }

    public int getTwo_sat() {
        return two_sat;
    }

    public void setTwo_sat(int two_sat) {
        this.two_sat = two_sat;
    }

    public int getThree_hue() {
        return three_hue;
    }

    public void setThree_hue(int three_hue) {
        this.three_hue = three_hue;
    }

    public String getThree_on() {
        return three_on;
    }

    public void setThree_on(String three_on) {
        this.three_on = three_on;
    }

    public int getThree_bri() {
        return three_bri;
    }

    public void setThree_bri(int three_bri) {
        this.three_bri = three_bri;
    }

    public int getThree_sat() {
        return three_sat;
    }

    public void setThree_sat(int three_sat) {
        this.three_sat = three_sat;
    }

    @Override
    public String toString() {
        return "BulbColor{" +
                "name='" + name + '\'' +
                ", one_hue='" + one_hue + '\'' +
                ", one_on='" + one_on + '\'' +
                ", one_bri='" + one_bri + '\'' +
                ", one_sat='" + one_sat + '\'' +
                ", two_hue='" + two_hue + '\'' +
                ", two_on='" + two_on + '\'' +
                ", two_bri='" + two_bri + '\'' +
                ", two_sat='" + two_sat + '\'' +
                ", three_hue='" + three_hue + '\'' +
                ", three_on='" + three_on + '\'' +
                ", three_bri='" + three_bri + '\'' +
                ", three_sat='" + three_sat + '\'' +
                '}';
    }
}
