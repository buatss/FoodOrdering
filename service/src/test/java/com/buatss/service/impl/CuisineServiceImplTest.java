package com.buatss.service.impl;

import com.buatss.repository.dao.CuisineRepository;
import com.buatss.repository.dao.DrinkExtrasRepository;
import com.buatss.repository.model.dto.CuisineDto;
import com.buatss.repository.model.dto.DrinkExtrasDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.TreeSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CuisineServiceImplTest {

    @Mock
    CuisineRepository cuisineRepository;

    @Mock
    DrinkExtrasRepository drinkExtrasRepository;

    @InjectMocks
    CuisineServiceImpl service;

    @Test
    void getCuisines_found_returnsDto() {
        Optional<TreeSet<CuisineDto>> cuisines = Optional.of(new TreeSet<>());
        Optional<TreeSet<DrinkExtrasDto>> extras = Optional.of(new TreeSet<>());

        when(cuisineRepository.findPageById(1, 5)).thenReturn(cuisines);
        when(drinkExtrasRepository.findPageById(1, 5)).thenReturn(extras);

        assertEquals(service.getCuisines(), cuisines.get());
        verify(cuisineRepository, times(1)).findPageById(1, 5);
        verify(drinkExtrasRepository, times(1)).findPageById(1, 5);
    }

    @Test
    void getCuisines_notFound_throwsRuntimeException() {
        Optional<TreeSet<CuisineDto>> cuisines = Optional.empty();
        Optional<TreeSet<DrinkExtrasDto>> extras = Optional.empty();

        when(cuisineRepository.findPageById(1, 5)).thenReturn(cuisines);
        when(drinkExtrasRepository.findPageById(1, 5)).thenReturn(extras);

        assertThrows(RuntimeException.class, () -> service.getCuisines());
        verify(cuisineRepository, times(1)).findPageById(1, 5);
        verify(drinkExtrasRepository, times(1)).findPageById(1, 5);
    }
}
