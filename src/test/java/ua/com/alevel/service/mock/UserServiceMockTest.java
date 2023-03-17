package ua.com.alevel.service.mock;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ua.com.alevel.entity.Profile;
import ua.com.alevel.entity.User;
import ua.com.alevel.exception.EntityNotFoundException;
import ua.com.alevel.exception.UnprocessableEntityException;
import ua.com.alevel.repository.UserRepository;
import ua.com.alevel.service.ProfileService;
import ua.com.alevel.service.impl.UserServiceImpl;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@RunWith(SpringRunner.class)
//@SpringBootTest
public class UserServiceMockTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private ProfileService profileService;

    private static final String FIRST_NAME = "firstName";
    private static final String LAST_NAME = "lastName";
    private static final String EMAIL = "email@mail.com";

    private final User user = new User();
    private final Profile profile = new Profile();

    @Before
    public void setUp() {
        user.setFirstName(FIRST_NAME);
        user.setLastName(LAST_NAME);
        user.setEmail(EMAIL);
        profile.setUser(user);
    }

    @Test
    public void shouldBeCreateUserWhenEmailIsUnique() {
        // given
        when(userRepository.existsByEmail(EMAIL)).thenReturn(false);

        // when
        userService.create(user);

        // then
        verify(userRepository, times(1)).save(user);
    }

    @Test
    public void shouldBeCreateUserWhenEmailIsNotUnique() {
        // given
        Executable executable = () -> userService.create(user);
        when(userRepository.existsByEmail(EMAIL)).thenReturn(true);

        // when
        Exception exception = assertThrows(UnprocessableEntityException.class, executable);

        // then
        assertThat(exception).isInstanceOf(UnprocessableEntityException.class);
        assertThat(exception.getMessage()).isEqualTo("user already exists");
    }

    @Test
    public void shouldBeUpdateUserWhenIdIsExists() {
        // given
        when(userRepository.existsById(1L)).thenReturn(true);

        // when
        userService.update(1L, user);

        // then
        verify(userRepository, times(1)).save(user);
    }

    @Test
    public void shouldBeUpdateUserWhenIdIsNotExists() {
        // given
        Executable executable = () -> userService.update(1L, user);
        when(userRepository.existsById(1L)).thenReturn(false);

        // when
        Exception exception = assertThrows(EntityNotFoundException.class, executable);

        // then
        assertThat(exception).isInstanceOf(EntityNotFoundException.class);
        assertThat(exception.getMessage()).isEqualTo("entity not found");
    }
}
