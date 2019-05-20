package com.delicious.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.net.URL;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity(name = "rich_user")
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

    public RichUser(){}

    public RichUser(String name, String lastName, URL image, String email, Collection<? extends GrantedAuthority> authorities){
        this.name = name;
        this.lastName = lastName;
        this.image = image;
        this.email = email;
    }

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

    public Set<Recipe> getRecipes() {
        return recipes;
    }

    public void setRecipes(Set<Recipe> recipes) {
        this.recipes = recipes;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public URL getImage() {
        return image;
    }

    public void setImage(URL image) {
        this.image = image;
    }

    @Override
    public int hashCode(){
        HashCodeBuilder builder = new HashCodeBuilder();
        builder.append(Arrays.asList(getName(), getLastName(), getImage().toString(), getEmail()));
        return builder.toHashCode();
    }

    @Override
    public boolean equals(Object o){
        if (!(o instanceof RichUser)) {
            return false;
        }
        RichUser richUser = (RichUser) o;
        EqualsBuilder builder = new EqualsBuilder();
        builder.append(getName(), richUser.getName());
        builder.append(getLastName(), richUser.getLastName());
        builder.append(getImage(), richUser.getImage());
        builder.append(getEmail(), richUser.getEmail());
        return builder.isEquals();
    }

}
