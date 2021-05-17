package ru.dmitrymorel.bank_api_task.view;

import ru.dmitrymorel.bank_api_task.controllers.CardController;

import java.awt.*;
import java.util.Scanner;

public class CardMenu {
    private static CardMenu cardMenu;
    private CardView cardView;
    private final CardController cardController;

    public static final Color COLOR_RESET = Color.WHITE;
    public static final Color COLOR_GRAY = Color.GRAY;
    public static final Color COLOR_GREEN = Color.GREEN;

    private CardMenu() {
        cardView = new CardView();
        cardController = new CardController();
    }

    public static CardMenu getInstance() {
        if (cardMenu == null) {
            cardMenu = new CardMenu();
        }

        return cardMenu;
    }

    public void showMainMenu() {
        System.out.println("Выберите действие:" +
                "[1] Выпустить новую карту по счету." +
                "[2] Показать список всех карт." +
                "[3] Внести средства." +
                "[4] Показать баланс.");

        int select = matchMenuNumber();
        switch (select) {
            case 1: {
                showCreateCardMenu();
            }
            case 2: {

            }
            case 3: {

            }
            case 4: {

            }
            default: {
                System.out.println("Выбран несуществующий пункт меню");
                showMainMenu();
            }
        }
    }

    public void showCreateCardMenu() {
        System.out.println("Выберите действие:" +
                "[1] Привязать карту к сущестующему счету." +
                "[2] Привязать карту к новому счету.");

        int select = matchMenuNumber();
        switch (select) {
            case 1: {
                createCardMenu();
            }
            case 2: {
                System.out.println("Пока не реализовано)");
            }
            default: {
                System.out.println("Выбран несуществующий пункт меню");
                showCreateCardMenu();
            }
        }
    }

    public void showChoiceCardTypeMenu() {
        System.out.println("Выберите тип карты:" +
                "[1] СберКарта \n" + COLOR_GRAY +
                "Наша лучшая карта на каждый день" + COLOR_GREEN +
                "Бесплатно или 150₽ в месяц" + COLOR_RESET +
                "[2] СберКарта для пособий и пенсий \n" +
                "" +
                "[3] Зарплатная \n" +
                "" +
                "[4] Сберкарта Travel \n" +
                "" +
                "[5] Большие бонусы \n" +
                "" +
                "[6] Для пособий и пенсий \n" +
                "" +
                "[7] Золотая \n" +
                "" +
                "[8] Молодежная \n" +
                "" +
                "[9] Цифровая \n" +
                "" +
                "[10] СберKids \n" +
                "" +
                "[11] Аэрофлот \n" +
                "" +
                "[12] Подари жизнь \n" +
                "" +
                "");

        int select = matchMenuNumber();
        String type = null;
        switch (select) {
            case 1: {
                type = "Сберкарта";
            }
            default: {
                System.out.println("Выбран несуществующий пункт меню");
            }
        }

        showChoicePaymentSystemMenu();
        System.out.println("Выберите платежную систему");
    }

    private void showChoicePaymentSystemMenu() {
        System.out.println("Выберите платежную систему \n" +
                "[1] Мир \n" +
                "[2] Visa \n" +
                "[3] MasterCard \n");

        int select = matchMenuNumber();
        String paymentSystem = null;
        switch (select) {
            case 1: {
                paymentSystem = "Сберкарта";
            }
            default: {
                System.out.println("Выбран несуществующий пункт меню");
            }
        }

        cardView.createCardDialog();
    }

    public void createCardMenu() {
        cardView.createCardDialog();
        showMainMenu();
    }

    private int matchMenuNumber() {
        int select = -1;

        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        while (!input.matches("[0-9]")) {
            System.out.println("Ошибка ввода, попробуйте еще раз.");
            input = sc.nextLine();
        }

        try {
            select = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("Ошибка парсинга!");
        }

        return select;
    }
}
