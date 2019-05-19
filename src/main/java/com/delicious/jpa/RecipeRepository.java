package com.delicious.jpa;

import com.delicious.model.Recipe;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;

import java.util.Optional;

public interface RecipeRepository extends Repository<Recipe, Long> {

    Long count();

    Recipe save(Recipe recipe);

    Page<Recipe> findAll(Pageable pageable);

    Optional<Recipe> getById(Long id);
}
