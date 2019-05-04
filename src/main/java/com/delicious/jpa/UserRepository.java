package com.delicious.jpa;

import com.delicious.model.User;
import org.springframework.data.repository.Repository;

public interface UserRepository extends Repository<User, Long> {

}
