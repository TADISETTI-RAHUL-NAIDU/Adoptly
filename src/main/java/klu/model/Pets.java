package klu.model;

import jakarta.persistence.*;
import java.util.Arrays;

@Entity
@Table(name = "pets")
public class Pets {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "breed", nullable = false)
    private String breed;

    @Column(name = "age", nullable = false)
    private int age;

    @Column(name = "location", nullable = false)
    private String location;

    @Column(name = "cost", nullable = false)
    private int cost;

    @Column(name = "pets_condition", nullable = false)
    private String pets_condition;

    @Column(name = "image_filename")
    private String imageFilename; // for filename storage

    @Lob
    @Column(name = "photo")
    private byte[] photo; // for storing the actual image data

    @Column(name = "category", nullable = false)
    private String category;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public String getCondition() {
        return pets_condition;
    }

    public void setCondition(String pets_condition) {
        this.pets_condition = pets_condition;
    }

    public String getImageFilename() {
        return imageFilename;
    }

    public void setImageFilename(String imageFilename) {
        this.imageFilename = imageFilename;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Pets{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", breed='" + breed + '\'' +
                ", age=" + age +
                ", location='" + location + '\'' +
                ", cost=" + cost +
                ", pets_condition='" + pets_condition + '\'' +
                ", imageFilename='" + imageFilename + '\'' +
                ", category='" + category + '\'' +
                ", photo=" + Arrays.toString(photo) +
                '}';
    }
}
