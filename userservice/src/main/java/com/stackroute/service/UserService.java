package com.stackroute.service;

import com.stackroute.domain.User;
import com.stackroute.exception.UserAlreadyExistsException;
import com.stackroute.exception.UserNotFoundException;

import java.util.List;

public interface UserService {

        public User saveUser (User user) throws UserAlreadyExistsException;
        public List<User> getAllUsers();
        public User deleteUser(int userId) throws UserNotFoundException;
        public User updateUser(int userId, User user) throws UserNotFoundException;
        public String findByPassword(int id, String password) throws UserNotFoundException;
    }

