package com.delicious.model;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.List;
import org.springframework.security.core.userdetails.User;

@Entity
public class RichUser {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String lastName;

    @NotNull
    @Column(unique = true)
    private User user;

    @NotNull
    @Email
    @Column(unique = true)
    private String email;

    @OneToMany
    private List<Recipe> recipes;

    public void addRecipe(Recipe recipe){
        if (!this.getRecipes().contains(recipe)) {
            this.getRecipes().add(recipe);
        }
    }

    public void removeRecipe(Recipe recipe){
        if (this.getRecipes().contains(recipe)) {
           this.getRecipes().remove(recipe);
        }
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Recipe> getRecipes() {
        return recipes;
    }

    public void setRecipes(List<Recipe> recipes) {
        this.recipes = recipes;
    }
}
