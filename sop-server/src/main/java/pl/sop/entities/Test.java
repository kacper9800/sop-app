/*
 * System Obsługi Praktyk
 * Kacper Rzymkiewicz #2020
 */

package pl.sop.entities;

public class Test {

    private String name;
    private String author;

    public Test() {
    }

    public Test(String name, String author) {
        this.name = name;
        this.author = author;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
