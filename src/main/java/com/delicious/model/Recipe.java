package com.delicious.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.net.URL;

@Entity
public class Recipe {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    @Column(unique = true)
    private String name;

    @NotNull
    private String description;

    @Lob
    @NotNull
    private byte[] image;

//    @ElementCollection
    private URL link;

    private Long numberOfVotes;

    private Long startsSum;

    public Recipe(){}

    public Recipe(String name, String description, byte[] image, URL link) {
        this.name = name;
        this.description = description;
        this.image = image;
        this.link = link;
        this.setNumberOfVotes(0L);
        this.setStartsSum(0L);
    }

    public Double getAvgStarts(){
        return this.startsSum.doubleValue() / this.numberOfVotes;
    }

    public Double addStarts(Long numberOfStarts){
        this.numberOfVotes++;
        this.startsSum+=numberOfStarts;
        return this.startsSum.doubleValue() / this.numberOfVotes;
    }

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public URL getLink() {
        return link;
    }

    public void setLink(URL link) {
        this.link = link;
    }

    public Long getNumberOfVotes() {
        return numberOfVotes;
    }

    public void setNumberOfVotes(Long numberOfVotes) {
        this.numberOfVotes = numberOfVotes;
    }

    public Long getStartsSum() {
        return startsSum;
    }

    public void setStartsSum(Long startsSum) {
        this.startsSum = startsSum;
    }
}
