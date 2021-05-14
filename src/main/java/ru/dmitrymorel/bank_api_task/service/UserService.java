package ru.dmitrymorel.bank_api_task.service;

import ru.dmitrymorel.bank_api_task.dao.UserDAO;
import ru.dmitrymorel.bank_api_task.model.User;

import java.util.List;

public class UserService implements CrudService<User>{

    private static UserDAO userDAO = new UserDAO();

    @Override
    public User get(int id) {
        return userDAO.get(id);
    }

    @Override
    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }

    @Override
    public void save(User user) {
        userDAO.save(user);
    }

    @Override
    public void update(int id, User user) {
        userDAO.update(id, user);
    }

    @Override
    public void delete(int id) {
        userDAO.delete(id);
    }
}
