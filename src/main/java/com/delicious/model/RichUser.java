package com.delicious.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.net.URL;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity(name = "rich_user")
@Builder @NoArgsConstructor @AllArgsConstructor @Getter @Setter @EqualsAndHashCode
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

    /**
     * @Important: do not update this attribute because is used to handle user uniqueness.
     *  changing this attribute could cause strange behavior in other parts of the code.
     */
    @NotNull
    @Email
    @Column(unique = true)
    private String email;

    @OneToMany(cascade = CascadeType.ALL)
    @Builder.Default
    @EqualsAndHashCode.Exclude
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
