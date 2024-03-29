package com.vladgoncharov.eshop.dao;

import com.vladgoncharov.eshop.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    User findFirstByUsername(String username);
    void deleteByUsername(String username);

}
