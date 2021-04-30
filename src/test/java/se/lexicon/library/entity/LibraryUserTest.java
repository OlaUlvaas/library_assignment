package se.lexicon.library.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class LibraryUserTest {

    LibraryUser testUser;

    @BeforeEach
    public void setUp(){
        testUser = new LibraryUser();
        testUser.setName("Ola Ulvås");
        testUser.setEmail("olaulvas@hotmail.com");
    }
    @Test
    @DisplayName("Test 1 - Name")
    public void get_name_test(){
        String expectedName = "Ola Ulvås";
        String actualName = testUser.getName();
        Assertions.assertEquals(expectedName, actualName);
    }
    @Test
    @DisplayName("Test 2 - Email")
    public void get_email_test(){
        String expectedEmail = "olaulvas@hotmail.com";
        String actualEmail = testUser.getEmail();
        Assertions.assertEquals(expectedEmail, actualEmail);
    }

    /*@Test
    @DisplayName("Test 3 - HashCode")
    public void get_hash_code_test(){
        LibraryUser expectedUser = new LibraryUser(null,
                "Ola Ulvås", "olaulvas@hotmail.com");
        LibraryUser actualUser = testUser;
        Assertions.assertEquals(expectedUser.hashCode(), actualUser.hashCode());
    }*/

}
