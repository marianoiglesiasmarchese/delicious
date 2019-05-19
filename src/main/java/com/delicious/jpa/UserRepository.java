package com.delicious.jpa;

import com.delicious.model.RichUser;
import org.springframework.data.repository.Repository;

import java.util.Optional;

public interface UserRepository extends Repository<RichUser, Long> {

    RichUser save(RichUser user);

    Optional<RichUser> findByEmail(String email);

}
