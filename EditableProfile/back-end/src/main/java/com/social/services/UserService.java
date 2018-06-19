package com.social.services;

import com.social.dao.UserRepository;
import com.social.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author shafat saeed
 *
 */
@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public User save(User user) {
        return userRepository.saveAndFlush(user);
    }

    public User update(User user) {
        return userRepository.save(user);
    }

    public User find(String userName) {
        return userRepository.findOneByUsername(userName);
    }

    public User find(Long id) {
        return userRepository.findOne(id);
    }

    public User findByUserAndPwd(String userName, String pwd) {
        return userRepository.findByUsernameAndPassword(userName, pwd);
    }
}
