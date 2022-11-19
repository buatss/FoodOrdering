package com.buatss.ui;

import com.buatss.repository.model.entity.CuisineEntity;
import com.buatss.service.logic.CuisineService;
import com.buatss.service.logic.OrderService;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class UserInterface {
    private final CuisineService cuisineService;

    private final OrderService orderService;

    CuisineEntity selectedCuisine;

    private final Scanner scanner = new Scanner(System.in);

    public UserInterface(CuisineService cuisineService, OrderService orderService) {
        this.cuisineService = cuisineService;
        this.orderService = orderService;
    }

    public void applicationLoop() {
        while (true) {
            printWelcomeMessage();
            selectedCuisine = askToSelectCuisine();
            printCuisineMenu(selectedCuisine);
            boolean isLunchOrdered = askForLunch();
            if (isLunchOrdered) {
                askForMainCourseOrder();
                askForDessertOrder();
            }
            boolean isDrinkOrdered = askForDrink();
            if (isDrinkOrdered) {
                askForDrinkOrder();
                askForDrinkExtraOrder();
            }
            if (isDrinkOrdered || isLunchOrdered) {
                printBill();
                printExitMessage();
            } else {
                boolean retry = askToRetry();
                if (!retry) {
                    printExitMessage();
                    break;
                }
            }
        }

    }

    private void printExitMessage() {
    }

    private boolean askToRetry() {
        throw new UnsupportedOperationException();
    }

    private void printBill() {
    }

    private void askForDrinkExtraOrder() {
    }

    private void askForDrinkOrder() {
    }

    private void askForDessertOrder() {
    }

    private void askForMainCourseOrder() {
    }

    private boolean askForDrink() {
        throw new UnsupportedOperationException();
    }

    private boolean askForLunch() {
        throw new UnsupportedOperationException();
    }

    private void printCuisineMenu(CuisineEntity selectedCuisine) {
    }

    private CuisineEntity askToSelectCuisine() {
        System.out.println("Available cuisines:");
        int[] index = {0};
        cuisineService.getCuisines()
                      .stream()
                      .map(cuisine -> String.format("%d, %s", ++index[0], cuisine.getName()))
                      .forEach(System.out::println);
        int selected;
        while (true) {
            if (scanner.hasNextInt()) {
                selected = scanner.nextInt();
                scanner.nextLine();
                break;
            }
        }
        return null;
    }

    private void printWelcomeMessage() {
        System.out.println("Welcome in food ordering system!");
    }
}
