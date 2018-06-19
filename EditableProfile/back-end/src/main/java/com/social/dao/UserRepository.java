package com.social.dao;

import com.social.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author shafat saeed
 *
 */
/*
 * this the user Repository interface
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    public User findOneByUsername(String username);

    public User findByUsernameAndPassword(String username, String password);
}
