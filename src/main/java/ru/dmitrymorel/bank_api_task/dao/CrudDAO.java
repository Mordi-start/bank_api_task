package ru.dmitrymorel.bank_api_task.dao;

import java.util.List;

public interface CrudDAO<T> {

    T get(int id);

    List<T> getAllUsers();

    void save(T t);

    void update(int id, T t);

    void delete(int id);
}
