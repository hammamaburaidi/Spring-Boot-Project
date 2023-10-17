package com.hammam.demoTest.Controller;

import com.hammam.demoTest.Model.Users;
import com.hammam.demoTest.Service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/users")
public class UsersController {

    private final UsersService usersService;

    @Autowired
    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    @GetMapping // Getting All Users
    public List<Users> getAll(){
        return usersService.getUsers();
    }

    @PostMapping // Creating A New User
    public void registerNewUser(@RequestBody Users users){
        usersService.addNewUser(users);
    }

    @DeleteMapping(path = "{userTCKN}")
    public void deleteUser(@PathVariable("userTCKN") Long userTCKN){
        usersService.deleteUser(userTCKN);
    }

    @PutMapping(path = "{userTCKN}")
    public void updateUser(
            @PathVariable("userTCKN") Long userTCKN,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String surename,
            @RequestParam(required = false) String email) {
        usersService.updateUser(userTCKN, name, surename, email);
    }
}
