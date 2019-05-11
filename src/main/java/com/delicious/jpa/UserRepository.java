package com.delicious.jpa;

import com.delicious.model.RichUser;
import org.springframework.data.repository.Repository;

public interface UserRepository extends Repository<RichUser, Long> {

}
