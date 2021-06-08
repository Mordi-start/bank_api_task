package ru.dmitrymorel.bank_api_task.service;

import ru.dmitrymorel.bank_api_task.dao.UserDAOImpl;
import ru.dmitrymorel.bank_api_task.model.User;

import java.util.List;

public class UserService implements CrudService<User>{

    private static UserDAOImpl userDAOImpl = new UserDAOImpl();

    @Override
    public User get(int id) {
        return userDAOImpl.get(id);
    }

    public User getUserById(int id) {
        return userDAOImpl.get(id);
    }

    @Override
    public List<User> getAll() {
        return userDAOImpl.getAll();
    }

    @Override
    public void save(User user) {
        userDAOImpl.save(user);
    }

    @Override
    public void update(int id, User user) {
        userDAOImpl.update(id, user);
    }

    @Override
    public void delete(int id) {
        userDAOImpl.delete(id);
    }
}
