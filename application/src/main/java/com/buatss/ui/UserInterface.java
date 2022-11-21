package com.buatss.ui;

import com.buatss.repository.model.converter.EntityDtoConverter;
import com.buatss.repository.model.dto.*;
import com.buatss.repository.model.entity.*;
import com.buatss.service.CuisineService;
import com.buatss.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class UserInterface {
    private final CuisineService cuisineService;

    private final OrderService orderService;

    private final EntityDtoConverter converter;

    private final OrderEntity order = new OrderEntity();

    private final Scanner scanner = new Scanner(System.in);

    CuisineDto selectedCuisine;

    @Autowired
    public UserInterface(CuisineService cuisineService, OrderService orderService, EntityDtoConverter converter) {
        this.cuisineService = cuisineService;
        this.orderService = orderService;
        this.converter = converter;
        initOrder();
    }

    private void initOrder() {
        order.setMeals(new HashSet<>());
        order.setDrinks(new HashSet<>());
        order.setDrinkExtras(new HashSet<>());
        order.setDesserts(new HashSet<>());
    }

    public void applicationLoop() {
        while (true) {
            printWelcomeMessage();
            interactivelySelectCuisine();
            printSelectedCuisineMenu();
            boolean isLunchOrdered = askForLunch();
            if (isLunchOrdered) {
                interactiveAskForMainCourseOrder();
                interactiveAskForDessertOrder();
            }
            boolean isDrinkOrdered = askForDrink();
            if (isDrinkOrdered) {
                interactiveAskForDrinkOrder();
                interactiveAskForDrinkExtraOrder();
            }
            if (isDrinkOrdered || isLunchOrdered) {
                printBill();
                printExitMessage();
                break;
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
        System.out.println("Thank you for visiting our restaurant!");
    }

    private boolean askToRetry() {
        System.out.println("You haven't chosen lunch nor drink, do you want to retry?");
        System.out.println("Type 'yes' to retry otherwise you will leave application.");
        return scanner.nextLine().equalsIgnoreCase("yes");
    }

    private void printBill() {
        OrderDto orderDto = orderService.createOrder(order);
        System.out.println("You've ordered:");
        int index = 0;
        index = printOrderedProducts(orderDto.getMeals(), index);
        index = printOrderedProducts(orderDto.getDesserts(), index);
        index = printOrderedProducts(orderDto.getDrinks(), index);
        printOrderedProducts(orderDto.getDrinkExtras(), index);
        System.out.printf("Your order costs in total: %8s PLN%n", orderDto.getTotalPrice());
    }

    private void interactiveAskForDrinkExtraOrder() {
        System.out.println("Please select drink extra from menu. In case you don't want any enter 0.");
        Optional<AbstractProductDto<?>> result =
                interactiveSelectProductFromCollection(selectedCuisine.getDrinkExtras(), true);
        if (result.isPresent()) {
            DrinkExtrasDto dto = (DrinkExtrasDto) result.get();
            DrinkExtrasEntity entity = converter.convertDtoToEntity(dto);
            order.getDrinkExtras().add(entity);
        }
    }

    private void interactiveAskForDrinkOrder() {
        System.out.println("Please select drink from menu.");
        Optional<AbstractProductDto<?>> result =
                interactiveSelectProductFromCollection(selectedCuisine.getDrinks(), false);
        if (result.isPresent()) {
            DrinkDto dto = (DrinkDto) result.get();
            DrinkEntity entity = converter.convertDtoToEntity(dto);
            order.getDrinks().add(entity);
        }
    }

    private void interactiveAskForDessertOrder() {
        System.out.println("Please select dessert from menu.");
        Optional<AbstractProductDto<?>> result =
                interactiveSelectProductFromCollection(selectedCuisine.getDesserts(), true);
        if (result.isPresent()) {
            DessertDto dto = (DessertDto) result.get();
            DessertEntity entity = converter.convertDtoToEntity(dto);
            order.getDesserts().add(entity);
        }
    }

    private void interactiveAskForMainCourseOrder() {
        System.out.println("Please select main course from menu.");
        Optional<AbstractProductDto<?>> result =
                interactiveSelectProductFromCollection(selectedCuisine.getMeals(), false);
        if (result.isPresent()) {
            MealDto meal = (MealDto) result.get();
            MealEntity mealEntity = converter.convertDtoToEntity(meal);
            order.getMeals().add(mealEntity);
        }
    }

    private boolean askForDrink() {
        System.out.println("Do you wish to order drink? ");
        System.out.println("Type 'yes' to order drink otherwise this step will be skipped.");
        return scanner.nextLine().equalsIgnoreCase("yes");
    }

    private boolean askForLunch() {
        System.out.println(
                "Do you wish to order lunch? Lunch consists one main course(mandatory) and one dessert(optional).");
        System.out.println("Type 'yes' to order lunch otherwise this step will be skipped.");
        return scanner.nextLine().equalsIgnoreCase("yes");
    }

    private void printSelectedCuisineMenu() {
        printIndexedProductsList("Main Courses", selectedCuisine.getMeals());
        printIndexedProductsList("Desserts", selectedCuisine.getDesserts());
        printIndexedProductsList("Drinks", selectedCuisine.getDrinks());
        printIndexedProductsList("Drink Extras", selectedCuisine.getDrinkExtras());
    }

    private void interactivelySelectCuisine() {
        System.out.println("Available cuisines:");
        List<CuisineDto> cuisines = new ArrayList<>(cuisineService.getCuisines());
        cuisines.stream()
                .map(cuisine -> String.format("%d. %s", 1 + cuisines.indexOf(cuisine), cuisine.getName()))
                .forEach(System.out::println);
        int selected;
        System.out.println("Please select cuisine by inserting it's number:");
        while (true) {
            try {
                selected = scanner.nextInt();
                selectedCuisine = cuisines.get(--selected);
                break;
            } catch (InputMismatchException | IndexOutOfBoundsException e) {
                System.out.println("Invalid argument, try again.");
            } finally {
                scanner.nextLine();
            }
        }
        System.out.println("You have selected " + selectedCuisine.getName() + " cuisine.");
    }

    private void printWelcomeMessage() {
        System.out.println("Welcome in food ordering system!");
    }

    private void printIndexedProductsList(String label, Set<? extends AbstractProductDto<?>> products) {
        System.out.printf("%n%s:%n", label);
        int[] index = {0};
        products.stream()
                .map(product -> String.format("| %-3s | %-20s | %5s PLN |",
                        ++index[0] + ".",
                        product.getName(),
                        product.getPrice()
                ))
                .forEach(System.out::println);
    }

    private Optional<AbstractProductDto<?>> interactiveSelectProductFromCollection(
            Collection<? extends AbstractProductDto<?>> productCollection, boolean isOptional) {
        List<? extends AbstractProductDto<?>> products = new ArrayList<AbstractProductDto<?>>(productCollection);
        int selected;
        AbstractProductDto<?> selectedProduct;
        while (true) {
            try {
                selected = scanner.nextInt();
                if (selected == 0 && isOptional) {
                    return Optional.empty();
                }
                selectedProduct = products.get(--selected);
                return Optional.of(selectedProduct);
            } catch (InputMismatchException | IndexOutOfBoundsException e) {
                System.out.println("Invalid argument, try again.");
            } finally {
                scanner.nextLine();
            }
        }
    }

    private int printOrderedProducts(Collection<? extends AbstractProductDto<?>> collection, int startIndex) {
        int[] index = {startIndex};
        collection.stream()
                  .map(product -> String.format("| %-2s | %-20s | %5s PLN |",
                          ++index[0] + ".",
                          product.getName(),
                          product.getPrice()
                  ))
                  .forEach(System.out::println);
        return index[0];
    }
}
