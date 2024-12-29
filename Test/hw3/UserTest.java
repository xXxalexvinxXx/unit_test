package hw3;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserTest {

    @Test
    void userCreation(){
        User user = new User("user_0", "psw123", false);
        assertTrue(user.authenticate("user_0", "psw123"));
    }

    @Test
    void userCreationFailed(){
        User user = new User("user_0", "123psw", false);
        assertFalse(user.authenticate("user_0", "psw123"));
    }

    @Test
    void userRepository(){
        UserRepository userRepository = new UserRepository();
        User userNA = new User("user_0", "psw123", false);
        userRepository.addUser(userNA);
        assertFalse(userRepository.findByName(userNA.name));
    }

    @Test
    void userRepositoryNA(){
        UserRepository userRepository = new UserRepository();
        User userNA = new User("user_0", "psw123", false);
        userNA.authenticate("user_0", "psw23");
        userRepository.addUser(userNA);
        assertFalse(userRepository.findByName(userNA.name));
    }

    @Test
    void logoutAllExceptAdminTest() {
        UserRepository repository = new UserRepository();

        User admin = new User("Admin", "admin123", true);
        User user1 = new User("User1", "password1", false);
        User user2 = new User("User2", "password2", false);

        admin.authenticate("Admin", "admin123");
        user1.authenticate("User1", "password1");
        user2.authenticate("User2", "password2");

        repository.addUser(admin);
        repository.addUser(user1);
        repository.addUser(user2);

        assertTrue(admin.isAuthenticate());
        assertTrue(user1.isAuthenticate());
        assertTrue(user2.isAuthenticate());

        repository.logoutAllExceptAdmin();

        assertTrue(admin.isAuthenticate());
        assertFalse(user1.isAuthenticate());
        assertFalse(user2.isAuthenticate());
    }


}