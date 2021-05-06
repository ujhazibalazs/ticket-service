package com.epam.training.ticketservice.command;

import com.epam.training.ticketservice.entity.User;
import com.epam.training.ticketservice.security.Authenticator;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class SignOutCommandTest {

    private SignOutCommand underTest;

    @Mock
    private Authenticator authenticator;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        underTest = new SignOutCommand(authenticator);
    }

    @Test
    public void testExecuteShouldReturnYouAreNotSignedInWhenLoggedInUserIsNull() {
        // Given
        given(authenticator.getLoggedInUser()).willReturn(null);

        // When
        String result = underTest.execute();

        // Then
        assertThat(result, equalTo("You are not signed in"));
    }

    @Test
    public void testExecuteShouldReturnSuccessfullyLoggedOutWhenLoggedInUserIsNotNull() {
        // Given
        User user = Mockito.mock(User.class);
        given(authenticator.getLoggedInUser()).willReturn(user);

        // When
        String result = underTest.execute();

        // Then
        assertThat(result, equalTo("Successfully logged out"));
    }
}