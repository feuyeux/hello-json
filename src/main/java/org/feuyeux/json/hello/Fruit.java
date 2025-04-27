package org.feuyeux.json.hello;

import java.util.Objects;

public class Fruit {
    private String english;
    private String french;
    private String russian;

    // Default constructor needed for JSON deserialization
    public Fruit() {
    }

    public Fruit(String english, String french, String russian) {
        this.english = english;
        this.french = french;
        this.russian = russian;
    }

    public String getEnglish() {
        return english;
    }

    public void setEnglish(String english) {
        this.english = english;
    }

    public String getFrench() {
        return french;
    }

    public void setFrench(String french) {
        this.french = french;
    }

    public String getRussian() {
        return russian;
    }

    public void setRussian(String russian) {
        this.russian = russian;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Fruit fruit = (Fruit) o;
        return Objects.equals(english, fruit.english) &&
                Objects.equals(french, fruit.french) &&
                Objects.equals(russian, fruit.russian);
    }

    @Override
    public int hashCode() {
        return Objects.hash(english, french, russian);
    }

    @Override
    public String toString() {
        return "Fruit{" +
                "english='" + english + '\'' +
                ", french='" + french + '\'' +
                ", russian='" + russian + '\'' +
                '}';
    }
}