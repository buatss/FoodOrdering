package com.buatss.repository.model.converter;

import com.buatss.repository.config.AppConfig;
import com.buatss.repository.model.converter.util.DataProvider;
import com.buatss.repository.model.dto.*;
import com.buatss.repository.model.entity.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import java.util.HashSet;
import java.util.List;
import java.util.TreeSet;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ContextConfiguration(classes = {AppConfig.class, EntityDtoConverter.class},
        loader = AnnotationConfigContextLoader.class)
@SpringBootTest
class EntityDtoConverterTest {
    private final EntityDtoConverter converter;

    private final DataProvider provider = new DataProvider();

    @Autowired
    EntityDtoConverterTest(EntityDtoConverter converter) {
        this.converter = converter;
    }

    @Test
    void convertCuisineEntityToDto() {
        CuisineEntity entity = new CuisineEntity();
        entity.setId(1);
        entity.setName("name");
        entity.setDesserts(new HashSet<>());
        entity.setDrinks(new HashSet<>());
        entity.setMeals(new HashSet<>());
    }

    @Test
    void ConvertCustomerEntityToDto() {
        CustomerEntity given = provider.provideCustomerEntity();
        CustomerDto expected = provider.provideCustomerDto();

        assertEquals(expected, converter.convertEntityToDto(given));
    }

    @Test
    void ConvertDessertEntityToDto() {
        CustomerEntity given = provider.provideCustomerEntity();
        CustomerDto expected = provider.provideCustomerDto();

        assertEquals(expected, converter.convertEntityToDto(given));
    }

    @Test
    void ConvertDrinkEntityToDto() {
        DrinkEntity given = provider.provideDrinkEntity();
        DrinkDto expected = provider.provideDrinkDto();

        assertEquals(expected, converter.convertEntityToDto(given));
    }

    @Test
    void ConvertDrinkExtrasEntityToDto() {
        DrinkExtrasEntity given = provider.provideDrinkExtrasEntity();
        DrinkExtrasDto expected = provider.provideDrinkExtrasDto();

        assertEquals(expected, converter.convertEntityToDto(given));
    }

    @Test
    void ConvertMealEntityToDto() {
        MealEntity given = provider.provideMealEntity();
        MealDto expected = provider.provideMealDto();

        assertEquals(expected, converter.convertEntityToDto(given));
    }

    @Test
    void ConvertOrderEntityToDto() {
        OrderEntity given = provider.provideOrderEntity();
        OrderDto expected = provider.provideOrderDto();

        assertEquals(expected, converter.convertEntityToDto(given));
    }

    @Test
    void convertMealEntityListToMealDtoTreeSet() {
        List<MealEntity> given = provider.provideMealEntityList();
        TreeSet<MealDto> expected = provider.provideTreeSetOfMealDto();

        assertEquals(expected, converter.convertMealEntityListToMealDtoTreeSet(given));
    }

    @Test
    void convertDrinkEntityListToDrinkDtoTreeSet() {
        List<DrinkEntity> given = provider.provideDrinkEntityList();
        TreeSet<DrinkDto> expected = provider.provideTreeSetOfDrinkDto();

        assertEquals(expected, converter.convertDrinkEntityListToDrinkDtoTreeSet(given));
    }

    @Test
    void convertDrinkExtrasEntityListToDrinkExtrasDtoTreeSet() {
        List<DrinkExtrasEntity> given = provider.provideDrinkExtrasEntityList();
        TreeSet<DrinkExtrasDto> expected = provider.provideTreeSetOfDrinkExtrasDto();

        assertEquals(expected, converter.convertDrinkExtrasEntityListToDrinkExtrasDtoTreeSet(given));
    }

    @Test
    void convertDessertEntityListToDessertDtoTreeSet() {
        List<DessertEntity> given = provider.provideDessertEntityList();
        TreeSet<DessertDto> expected = provider.provideTreeSetOfDessertDto();

        assertEquals(expected, converter.convertDessertEntityListToDessertDtoTreeSet(given));
    }

    @Test
    void convertCuisineEntityListToCuisineDtoTreeSet() {
        List<CuisineEntity> given = provider.provideCuisineEntityList();
        TreeSet<CuisineDto> expected = provider.provideTreeSetOfCuisineDto();

        assertEquals(expected, converter.convertCuisineEntityListToCuisineDtoTreeSet(given));
    }
}
