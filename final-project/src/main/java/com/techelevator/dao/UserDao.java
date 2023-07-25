package com.techelevator.dao;

import com.techelevator.model.User;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public interface UserDao {



    List<User> findAll();

    User getUserById(int userId);

    abstract User findByUsername(String username);

    int findIdByUsername(String username);

    User create(User newUser);
}
