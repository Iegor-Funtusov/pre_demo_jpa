package ua.com.alevel.service.unit;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.function.Executable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ua.com.alevel.entity.User;
import ua.com.alevel.exception.UnprocessableEntityException;
import ua.com.alevel.service.UserService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserServiceUnitTest {

    // given -> when -> then

    private static final String FIRST_NAME = "firstName";
    private static final String LAST_NAME = "lastName";
    private static final String EMAIL = "email@mail.com";

    private static User user = new User();

    @Autowired
    private UserService userService;

    @BeforeAll
    public static void setUp() {
        user.setFirstName(FIRST_NAME);
        user.setLastName(LAST_NAME);
        user.setEmail(EMAIL);
    }

    @Test
    @Order(1)
    public void shouldBeCreateUserWhenEmailIsUnique() {
        // given
        user.setFirstName(FIRST_NAME);
        user.setLastName(LAST_NAME);
        user.setEmail(EMAIL);
        userService.create(user);

        // when
        user = userService.findById(1L);

        // then
        assertThat(user.getId()).isEqualTo(1L);
        assertThat(user.getFirstName()).isEqualTo(FIRST_NAME);
        assertThat(user.getLastName()).isEqualTo(LAST_NAME);
        assertThat(user.getEmail()).isEqualTo(EMAIL);
    }

    @Test
    @Order(2)
    public void shouldBeCreateUserWhenEmailIsNotUnique() {
        // given
        Executable executable = () -> userService.create(user);
//        executable = new Executable() {
//            @Override
//            public void execute() throws Throwable {
//                userService.create(user);
//            }
//        };

        // when
        Exception exception = assertThrows(UnprocessableEntityException.class, executable);

        // then
        assertThat(exception).isInstanceOf(UnprocessableEntityException.class);
        assertThat(exception.getMessage()).isEqualTo("user already exists");
    }
}
