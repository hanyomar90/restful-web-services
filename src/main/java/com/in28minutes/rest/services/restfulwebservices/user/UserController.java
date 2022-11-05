package com.in28minutes.rest.services.restfulwebservices.user;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
public class UserController {

    private final UserDao dao;

    public UserController(UserDao dao) {
        this.dao = dao;
    }

    @GetMapping(path = "/user/{id}")
    public User getUserById(@PathVariable int id) {
        User user = dao.findOne(id);
        if (user == null)
            throw new UserNotFoundException("id: " + id);
        return user;
    }

    @GetMapping(path = "/users")
    public List<User> getAllUsers() {
        return dao.findAll();
    }

    @PostMapping(path = "/user")
    public ResponseEntity<User> addNewUser(@Valid @RequestBody User user) {
        User savedUser = dao.saveUser(user);
        ServletUriComponentsBuilder servletUriComponentsBuilder = ServletUriComponentsBuilder.fromCurrentRequest();
        UriComponentsBuilder path = servletUriComponentsBuilder.path("/{id}");
        URI location = path.buildAndExpand(savedUser.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping(path = "/user/{id}")
    public void deleteUserById(@PathVariable int id) {
        dao.deleteById(id);
    }
}
