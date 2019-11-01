package com.example.bulbbeats;

public class BulbColor {
    private String name;
    private String one_hue;
    private String one_on;
    private String one_bri;
    private String one_sat;
    private String two_hue;
    private String two_on;
    private String two_bri;
    private String two_sat;
    private String three_hue;
    private String three_on;
    private String three_bri;
    private String three_sat;


    public BulbColor(String name, String one_hue, String one_on, String one_bri, String one_sat, String two_hue, String two_on, String two_bri, String two_sat, String three_hue, String three_on, String three_bri, String three_sat) {
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

    public String getOne_sat() {
        return one_sat;
    }

    public void setOne_sat(String one_sat) {
        this.one_sat = one_sat;
    }

    public String getTwo_sat() {
        return two_sat;
    }

    public void setTwo_sat(String two_sat) {
        this.two_sat = two_sat;
    }

    public String getThree_sat() {
        return three_sat;
    }

    public void setThree_sat(String three_sat) {
        this.three_sat = three_sat;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOne_hue() {
        return one_hue;
    }

    public void setOne_hue(String one_hue) {
        this.one_hue = one_hue;
    }

    public String getOne_on() {
        return one_on;
    }

    public void setOne_on(String one_on) {
        this.one_on = one_on;
    }

    public String getOne_bri() {
        return one_bri;
    }

    public void setOne_bri(String one_bri) {
        this.one_bri = one_bri;
    }

    public String getTwo_hue() {
        return two_hue;
    }

    public void setTwo_hue(String two_hue) {
        this.two_hue = two_hue;
    }

    public String getTwo_on() {
        return two_on;
    }

    public void setTwo_on(String two_on) {
        this.two_on = two_on;
    }

    public String getTwo_bri() {
        return two_bri;
    }

    public void setTwo_bri(String two_bri) {
        this.two_bri = two_bri;
    }

    public String getThree_hue() {
        return three_hue;
    }

    public void setThree_hue(String three_hue) {
        this.three_hue = three_hue;
    }

    public String getThree_on() {
        return three_on;
    }

    public void setThree_on(String three_on) {
        this.three_on = three_on;
    }

    public String getThree_bri() {
        return three_bri;
    }

    public void setThree_bri(String three_bri) {
        this.three_bri = three_bri;
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
