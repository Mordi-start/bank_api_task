package ru.dmitrymorel.bank_api_task;

import ru.dmitrymorel.bank_api_task.controllers.UserController;

public class Main {
    public static void main(String[] args) {

        UserController userController = new UserController();

        System.out.println(userController.get(1));
        System.out.println(userController.get(2));
        System.out.println(userController.get(3));
    }
}
