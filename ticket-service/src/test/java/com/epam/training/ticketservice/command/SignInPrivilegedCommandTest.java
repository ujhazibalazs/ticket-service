package com.epam.training.ticketservice.command;

import com.epam.training.ticketservice.entity.User;
import com.epam.training.ticketservice.repository.UserRepository;
import com.epam.training.ticketservice.security.Authenticator;
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
class SignInPrivilegedCommandTest {

    private static final String USERNAME = "username";
    private static final String GOOD_PASSWORD = "password";
    private static final String WRONG_PASSWORD = "wrongPassword";
    private static final String BAD_ROLE = "badRole";
    private static final String GOOD_ROLE = "administrator";

    private SignInPrivilegedCommand underTest;

    @Mock
    private UserRepository userRepository;

    @Mock
    private Authenticator authenticator;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        underTest = new SignInPrivilegedCommand(userRepository, authenticator);
    }

    @Test
    public void testExecuteShouldReturnLoginFailedDueToIncorrectCredentialsWhenUserNotFound() {
        // Given
        given(userRepository.findById(USERNAME)).willReturn(Optional.empty());

        // When
        String result = underTest.execute(USERNAME, GOOD_PASSWORD);

        // Then
        assertThat(result, equalTo("Login failed due to incorrect credentials"));
        verifyNoInteractions(authenticator);
    }

    @Test
    public void testExecuteShouldReturnLoginFailedDueToIncorrectCredentialsWhenPasswordDoesNotMatch() {
        // Given
        User user = Mockito.mock(User.class);
        given(userRepository.findById(USERNAME)).willReturn(Optional.of(user));
        given(user.getPassword()).willReturn(WRONG_PASSWORD);

        // When
        String result = underTest.execute(USERNAME, GOOD_PASSWORD);

        // Then
        assertThat(result, equalTo("Login failed due to incorrect credentials"));
        verifyNoInteractions(authenticator);
    }

    @Test
    public void testExecuteShouldReturnPermissionDeniedWhenUsersRoleIsNotAdministrator() {
        // Given
        User user = Mockito.mock(User.class);
        given(userRepository.findById(USERNAME)).willReturn(Optional.of(user));
        given(user.getPassword()).willReturn(GOOD_PASSWORD);
        given(user.getRole()).willReturn(BAD_ROLE);

        // When
        String result = underTest.execute(USERNAME, GOOD_PASSWORD);

        // Then
        assertThat(result, equalTo("Permission denied"));
        verifyNoInteractions(authenticator);
    }

    @Test
    public void testExecuteShouldReturnSuccessfullyLoggedInWhenGivenCorrectLogin() {
        // Given
        User user = Mockito.mock(User.class);
        given(userRepository.findById(USERNAME)).willReturn(Optional.of(user));
        given(user.getPassword()).willReturn(GOOD_PASSWORD);
        given(user.getRole()).willReturn(GOOD_ROLE);

        // When
        String result = underTest.execute(USERNAME, GOOD_PASSWORD);

        // Then
        assertThat(result, equalTo("Successfully logged in"));
    }
}