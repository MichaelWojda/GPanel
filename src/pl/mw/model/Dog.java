package pl.mw.model;

import java.util.Objects;

public class Dog {

    private int id_dog;
    private String dogname;
    private String race;
    private int age;
    private User dogowner;
    private String health_notes;
    private String groomer_notes;

    public int getId_dog() {
        return id_dog;
    }

    public void setId_dog(int id_dog) {
        this.id_dog = id_dog;
    }

    public String getDogname() {
        return dogname;
    }

    public void setDogname(String dogname) {
        this.dogname = dogname;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public User getDogowner() {
        return dogowner;
    }

    public void setDogowner(User dogowner) {
        this.dogowner = dogowner;
    }

    public String getHealth_notes() {
        return health_notes;
    }

    public void setHealth_notes(String health_notes) {
        this.health_notes = health_notes;
    }

    public String getGroomer_notes() {
        return groomer_notes;
    }

    public void setGroomer_notes(String groomer_notes) {
        this.groomer_notes = groomer_notes;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Dog)) return false;
        Dog dog = (Dog) o;
        return getId_dog() == dog.getId_dog() &&
                getAge() == dog.getAge() &&
                getDogname().equals(dog.getDogname()) &&
                getRace().equals(dog.getRace()) &&
                getDogowner().equals(dog.getDogowner()) &&
                getHealth_notes().equals(dog.getHealth_notes()) &&
                getGroomer_notes().equals(dog.getGroomer_notes());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId_dog(), getDogname(), getRace(), getAge(), getDogowner(), getHealth_notes(), getGroomer_notes());
    }

    @Override
    public String toString() {
        return "Dog{" +
                "id_dog=" + id_dog +
                ", dogname='" + dogname + '\'' +
                ", race='" + race + '\'' +
                ", age=" + age +
                ", dogowner=" + dogowner +
                ", health_notes='" + health_notes + '\'' +
                ", groomer_notes='" + groomer_notes + '\'' +
                '}';
    }
}
