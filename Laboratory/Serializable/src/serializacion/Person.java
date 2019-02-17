/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serializacion;

import java.io.Serializable;

/**
 *
 * @author VicDCruz
 */
public class Person implements Serializable {
    private String name;
    private int age;
    private String place;

    public Person(String name, int age, String place) {
        this.name = name;
        this.age = age;
        this.place = place;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getPlace() {
        return place;
    }

}
