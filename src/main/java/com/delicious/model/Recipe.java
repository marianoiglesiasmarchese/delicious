package com.delicious.model;


import javassist.bytecode.ByteArray;
import org.hibernate.validator.constraints.URL;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
public class Recipe {

    @Id
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String description;

    @NotNull
    private ByteArray image;

    @URL
    private List<String> links;

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

    public ByteArray getImage() {
        return image;
    }

    public void setImage(ByteArray image) {
        this.image = image;
    }

    public List<String> getLinks() {
        return links;
    }

    public void setLinks(List<String> links) {
        this.links = links;
    }
}
