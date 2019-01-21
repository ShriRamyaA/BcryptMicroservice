package com.stackroute.controller;

import com.stackroute.domain.User;
import com.stackroute.exception.UserAlreadyExistsException;
import com.stackroute.exception.UserNotFoundException;
import com.stackroute.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
//Request mapping for posting user details
    @PostMapping("user")
    public ResponseEntity<User> saveUser(@RequestBody User user) {
        ResponseEntity responseEntity;
        try {
            userService.saveUser(user);
            responseEntity = new ResponseEntity<String>("Successfully created", HttpStatus.CREATED);
        }
        catch (UserAlreadyExistsException ex){
            responseEntity = new ResponseEntity<String>(ex.getMessage(),HttpStatus.CONFLICT);
            ex.printStackTrace();
        }
        return responseEntity;
    }
//Request mapping for getting user details
    @GetMapping("users")
    public ResponseEntity<List<User>> listOfUsers() {
        List<User> allUsers = userService.getAllUsers();
        return new ResponseEntity<List<User>>(allUsers, HttpStatus.OK);
    }
//Request mapping for deleting user details
    @DeleteMapping("user/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") int userId){
        ResponseEntity responseEntity;
        try {
            User user = userService.deleteUser(userId);
            responseEntity = new ResponseEntity<User>(user, HttpStatus.GONE);
        }
        catch (UserNotFoundException userNotFoundException){
            responseEntity = new ResponseEntity<>(userNotFoundException.getMessage(), HttpStatus.NOT_FOUND);

        }
        return responseEntity;
    }
    //Request mapping for updating user details
    @PutMapping("user/{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") int userId, @RequestBody User user) {
        ResponseEntity responseEntity;
        System.out.println("I am in controller");
        try{
            User user1 = userService.updateUser(userId,user);
            responseEntity = new ResponseEntity<User>(user1, HttpStatus.OK);
        }
        catch (UserNotFoundException ex){
            responseEntity = new ResponseEntity<String>(ex.getMessage(),HttpStatus.NOT_FOUND);
            ex.printStackTrace();
        }
        return responseEntity;

    }

    @GetMapping("user/authenticate/{id}/{password}")
    public ResponseEntity<?> authenticateUser(@PathVariable("id") int id, @PathVariable("password") String password){
        ResponseEntity responseEntity;
        try{
            responseEntity = new ResponseEntity<String>(userService.findByPassword(id,password),HttpStatus.ACCEPTED);
        }catch (UserNotFoundException e){
            responseEntity=new ResponseEntity<String>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
        return responseEntity;
    }

}

