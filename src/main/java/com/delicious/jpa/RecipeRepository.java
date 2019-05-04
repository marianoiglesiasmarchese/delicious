package com.delicious.jpa;

import com.delicious.model.Recipe;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;

public interface RecipeRepository extends Repository<Recipe, Long> {

    Long count();

    Page<Recipe> findAll(Pageable pageable);

}
