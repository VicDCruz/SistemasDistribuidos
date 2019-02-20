/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.io.Serializable;

/**
 *
 * @author VicDCruz
 */
public class Credential implements Serializable {
    private String name;
    private String country;
    private int year;
    private String curp;

    public Credential(String name, String country, int year, String curp) {
        this.name = name;
        this.country = country;
        this.year = year;
        this.curp = curp;
    }

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }

    public int getYear() {
        return year;
    }

    public String getCurp() {
        return curp;
    }

}
