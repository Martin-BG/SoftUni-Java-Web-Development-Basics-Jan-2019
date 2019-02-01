package fdmc.domain.entities;

import java.io.Serializable;

public class Cat implements Serializable {

    private static final long serialVersionUID = 1L;

    private final String name;
    private final String breed;
    private final String color;
    private final int age;

    public Cat(String name, String breed, String color, int age) {
        this.name = name;
        this.breed = breed;
        this.color = color;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public String getBreed() {
        return breed;
    }

    public String getColor() {
        return color;
    }

    public int getAge() {
        return age;
    }
}
