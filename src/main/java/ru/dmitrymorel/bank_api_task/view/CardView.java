package ru.dmitrymorel.bank_api_task.view;

import ru.dmitrymorel.bank_api_task.controllers.CardController;

import java.util.Scanner;

public class CardView {
    private final CardController cardController;

    public CardView() {
        cardController = new CardController();
    }

    public void createCardDialog() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Выберите тип карты");

        System.out.println("Выберите платежную систему");
    }
}
