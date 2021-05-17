package ru.dmitrymorel.bank_api_task.controllers;

import java.util.List;

public interface CrudController<T> {

    T get(int id);

    List<T> getAll();

    void save(T t);

    void update(int id, T t);

    void delete(int id);
}
