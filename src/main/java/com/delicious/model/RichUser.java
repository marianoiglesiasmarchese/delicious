package com.delicious.model;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.net.URL;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity(name = "rich_user")
@Getter @Setter @EqualsAndHashCode @Builder
public class RichUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private String name;

    @NotNull
    @Column(name = "last_name")
    private String lastName;

    @NotNull
    private URL image;

    @NotNull
    @Email
    @Column(unique = true)
    private String email;

    @OneToMany(cascade = CascadeType.ALL)
    private Set<Recipe> recipes = new LinkedHashSet<>();

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

}
