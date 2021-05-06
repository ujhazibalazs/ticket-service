package com.epam.training.ticketservice.command;

import com.epam.training.ticketservice.entity.Screening;
import com.epam.training.ticketservice.id.ScreeningId;
import com.epam.training.ticketservice.repository.ScreeningRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class)
class DeleteScreeningCommandTest {

    private static final String MOVIE_NAME = "movieName";
    private static final String ROOM_NAME = "roomName";
    private static final String GOOD_DATE_STRING = "2020-01-01 10:00";

    private DeleteScreeningCommand underTest;

    @Mock
    private ScreeningRepository screeningRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        underTest = new DeleteScreeningCommand(screeningRepository);
    }

    @Test
    public void testExecuteShouldReturnBadDateFormatWhenGivenBadDate() {
        // Given
        String badDate = "badDate";

        // When
        String result = underTest.execute(MOVIE_NAME, ROOM_NAME, badDate);

        // Then
        assertThat(result, equalTo("Bad date format"));
        verifyNoInteractions(screeningRepository);
    }

    @Test
    public void testExecuteShouldReturnScreeningDoesntExistWhenScreeningNotFound() {
        // Given
        given(screeningRepository.findById(any())).willReturn(Optional.empty());

        // When
        String result = underTest.execute(MOVIE_NAME, ROOM_NAME, GOOD_DATE_STRING);

        // Then
        assertThat(result, equalTo("Screening doesn't exist"));
    }

    @Test
    public void testExecuteShouldReturnScreeningDeletedSuccessfullyWhenScreeningFound() {
        // Given
        Screening screening = Mockito.mock(Screening.class);
        given(screeningRepository.findById(any())).willReturn(Optional.of(screening));

        // When
        String result = underTest.execute(MOVIE_NAME, ROOM_NAME, GOOD_DATE_STRING);

        // Then
        assertThat(result, equalTo("Screening deleted successfully"));
    }
}