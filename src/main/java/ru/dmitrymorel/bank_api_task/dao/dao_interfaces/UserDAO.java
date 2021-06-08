package ru.dmitrymorel.bank_api_task.dao.dao_interfaces;

import ru.dmitrymorel.bank_api_task.model.User;

public interface UserDAO {
    void saveUser(User user);

    void changeUserStatus(int userId);
}
