package com.sda.java.gda.springdemo.controller;


import com.sda.java.gda.springdemo.exception.BindingResultException;
import com.sda.java.gda.springdemo.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User create(@RequestBody @Valid User user,
                       BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            throw new BindingResultException(bindingResult);
        }
        return user;
    }




}
