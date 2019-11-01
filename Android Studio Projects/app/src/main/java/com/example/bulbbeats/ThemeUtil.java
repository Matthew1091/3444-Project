package com.example.bulbbeats;

import java.util.ArrayList;

public class ThemeUtil {

    private static ArrayList<BulbColor> allColors;
    private static ArrayList<BulbColor> selectedColors;

    public ThemeUtil(){
        if(allColors == null){
            allColors = new ArrayList<>();
            initAllColors();
        }
        if(selectedColors == null){
            selectedColors = new ArrayList<>();
        }
    }
    public static ArrayList<BulbColor> getAllColors(){return allColors;}
    public static ArrayList<BulbColor> getSelectedColors(){return selectedColors;}

    public boolean addSelectedColors(BulbColor colors){
        return selectedColors.add(colors);
    }




    private static void initAllColors(){

        String name = "";
        String one_hue = "";
        String one_on = "";
        String one_bri= "";
        String one_sat="";
        String two_hue= "";
        String two_on= "";
        String two_bri= "";
        String two_sat="";
        String three_hue= "";
        String three_on= "";
        String three_bri= "";
        String three_sat="";

        name = "UNT";
        one_hue = "25500"; //green
        one_on = "true";
        one_bri= "254";
        one_sat = "41";
        two_hue= "25500"; //another green
        two_on= "false";
        two_bri= "254";
        two_sat="100";
        three_hue= "25844"; //another green
        three_on= "false";
        three_bri= "254";
        three_sat="41";
        allColors.add(new BulbColor(name, one_hue, one_on, one_bri, one_sat, two_hue, two_on, two_bri, two_sat, three_hue, three_on, three_bri, three_sat));

        name = "Halloween";
        one_hue = "6006"; //orange
        one_on = "true";
        one_bri= "254";
        one_sat ="100";
        two_hue= "8008"; //yellow
        two_on= "false";
        two_bri= "254";
        two_sat="100";
        three_hue= "4444";//another orange
        three_on= "false";
        three_bri= "254";
        three_sat="100";
        allColors.add(new BulbColor(name, one_hue, one_on, one_bri, one_sat, two_hue, two_on, two_bri, two_sat, three_hue, three_on, three_bri, three_sat));


        name = "Christmas";
        one_hue = "0"; //red
        one_sat = "100";
        one_on = "true";
        one_bri= "254";
        two_hue= "25500"; //green
        two_on= "false";
        two_bri= "254";
        two_sat="46";
        three_hue= "64974"; //another red
        three_on= "false";
        three_bri= "254";
        three_sat="46";
        allColors.add(new BulbColor(name, one_hue, one_on, one_bri, one_sat, two_hue, two_on, two_bri, two_sat, three_hue, three_on, three_bri, three_sat));

    }

}
