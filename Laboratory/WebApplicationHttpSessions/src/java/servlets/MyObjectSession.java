/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

/**
 *
 * @author daniel
 */
public class MyObjectSession {
    private int age;
    private String user;
    
    public MyObjectSession(int age, String user) {
        this.age = age;
        this.user = user;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "MyObjectSession{" + "age=" + age + ", user=" + user + '}';
    }
}
