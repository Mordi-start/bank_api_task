package ru.dmitrymorel.bank_api_task.controllers;

import ru.dmitrymorel.bank_api_task.model.User;
import ru.dmitrymorel.bank_api_task.service.UserService;

import java.util.List;

public class UserController {

    private static UserService userService = new UserService();

    public User get(int id) {
        return userService.get(id);
    }

    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    public void save(User user) {
        userService.save(user);
    }

    public void update(int id, User user) {
        userService.update(id, user);
    }

    public void delete(int id) {
        userService.delete(id);
    }
}
