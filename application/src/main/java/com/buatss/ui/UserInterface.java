package com.buatss.ui;

import com.buatss.constant.MessagesConstants;
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
        System.out.println(MessagesConstants.EXIT_MESSAGE);
    }

    private boolean askToRetry() {
        System.out.println(MessagesConstants.ASK_TO_RETRY);
        System.out.println();
        return scanner.nextLine().equalsIgnoreCase(MessagesConstants.YES);
    }

    private void printBill() {
        OrderDto orderDto = orderService.createOrder(order);
        System.out.println(MessagesConstants.YOU_HAVE_ORDERED);
        int index = 0;
        index = printOrderedProducts(orderDto.getMeals(), index);
        index = printOrderedProducts(orderDto.getDesserts(), index);
        index = printOrderedProducts(orderDto.getDrinks(), index);
        printOrderedProducts(orderDto.getDrinkExtras(), index);
        System.out.printf(
                MessagesConstants.TOTAL_COST + ": %8s " + MessagesConstants.CURRENCY + "%n",
                orderDto.getTotalPrice()
        );
    }

    private void interactiveAskForDrinkExtraOrder() {
        System.out.println(MessagesConstants.ASK_TO_SELECT_DRINK_EXTRA_FROM_MENU);
        Optional<AbstractProductDto<?>> result =
                interactiveSelectProductFromCollection(selectedCuisine.getDrinkExtras(), true);
        if (result.isPresent()) {
            DrinkExtrasDto dto = (DrinkExtrasDto) result.get();
            DrinkExtrasEntity entity = converter.convertDtoToEntity(dto);
            order.getDrinkExtras().add(entity);
        }
    }

    private void interactiveAskForDrinkOrder() {
        System.out.println(MessagesConstants.ASK_TO_ORDER_DRINK_FROM_MENU);
        Optional<AbstractProductDto<?>> result =
                interactiveSelectProductFromCollection(selectedCuisine.getDrinks(), false);
        if (result.isPresent()) {
            DrinkDto dto = (DrinkDto) result.get();
            DrinkEntity entity = converter.convertDtoToEntity(dto);
            order.getDrinks().add(entity);
        }
    }

    private void interactiveAskForDessertOrder() {
        System.out.println(MessagesConstants.ASK_TO_SELECT_DESSERT_FROM_MENU);
        Optional<AbstractProductDto<?>> result =
                interactiveSelectProductFromCollection(selectedCuisine.getDesserts(), true);
        if (result.isPresent()) {
            DessertDto dto = (DessertDto) result.get();
            DessertEntity entity = converter.convertDtoToEntity(dto);
            order.getDesserts().add(entity);
        }
    }

    private void interactiveAskForMainCourseOrder() {
        System.out.println(MessagesConstants.ASK_TO_SELECT_COURSE_FROM_MENU);
        Optional<AbstractProductDto<?>> result =
                interactiveSelectProductFromCollection(selectedCuisine.getMeals(), false);
        if (result.isPresent()) {
            MealDto meal = (MealDto) result.get();
            MealEntity mealEntity = converter.convertDtoToEntity(meal);
            order.getMeals().add(mealEntity);
        }
    }

    private boolean askForDrink() {
        System.out.println(MessagesConstants.ASK_TO_ORDER_DRINK);

        return scanner.nextLine().equalsIgnoreCase(MessagesConstants.YES);
    }

    private boolean askForLunch() {
        System.out.println(MessagesConstants.ASK_TO_ORDER_LUNCH);
        return scanner.nextLine().equalsIgnoreCase(MessagesConstants.YES);
    }

    private void printSelectedCuisineMenu() {
        printIndexedProductsList(MessagesConstants.MAIN_COURSES, selectedCuisine.getMeals());
        printIndexedProductsList(MessagesConstants.DESSERTS, selectedCuisine.getDesserts());
        printIndexedProductsList(MessagesConstants.DRINKS, selectedCuisine.getDrinks());
        printIndexedProductsList(MessagesConstants.DRINK_EXTRAS, selectedCuisine.getDrinkExtras());
    }

    private void interactivelySelectCuisine() {
        System.out.println(MessagesConstants.AVAILABLE_CUISINES);
        List<CuisineDto> cuisines = new ArrayList<>(cuisineService.getCuisines());
        cuisines.stream()
                .map(cuisine -> String.format("%d. %s", 1 + cuisines.indexOf(cuisine), cuisine.getName()))
                .forEach(System.out::println);
        int selected;
        System.out.println(MessagesConstants.ASK_TO_SELECT_CUISINE);
        while (true) {
            try {
                selected = scanner.nextInt();
                selectedCuisine = cuisines.get(--selected);
                break;
            } catch (InputMismatchException | IndexOutOfBoundsException e) {
                System.out.println(MessagesConstants.INVALID_ARGUMENT);
            } finally {
                scanner.nextLine();
            }
        }
        System.out.println(
                MessagesConstants.YOU_HAVE_SELECTED + selectedCuisine.getName() + MessagesConstants.CUISINE + ".");
    }

    private void printWelcomeMessage() {
        System.out.println(MessagesConstants.WELCOME);
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
                System.out.println(MessagesConstants.INVALID_ARGUMENT);
            } finally {
                scanner.nextLine();
            }
        }
    }

    private int printOrderedProducts(Collection<? extends AbstractProductDto<?>> collection, int startIndex) {
        int[] index = {startIndex};
        collection.stream().map(product -> String.format("| %-2s | %-20s | %5s " + MessagesConstants.CURRENCY + " |",
                ++index[0] + ".",
                product.getName(),
                product.getPrice()
        )).forEach(System.out::println);
        return index[0];
    }
}
