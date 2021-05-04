package se.lexicon.library.dto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import se.lexicon.library.entity.LibraryUser;

@SpringBootTest
public class LibraryUserDtoTest {

    LibraryUserDto testLibraryUserDto;

    @BeforeEach
    public void setUp(){
        testLibraryUserDto = new LibraryUserDto();
        testLibraryUserDto.setName("Ola Ulvås");
        testLibraryUserDto.setEmail("olaulvas@hotmail.com");
    }
    @Test
    @DisplayName("Test 1 - Name")
    public void get_name_test(){
        String expectedName = "Ola Ulvås";
        String actualName = testLibraryUserDto.getName();
        Assertions.assertEquals(expectedName, actualName);
    }
    @Test
    @DisplayName("Test 2 - Email")
    public void get_email_test(){
        String expectedEmail = "olaulvas@hotmail.com";
        String actualEmail = testLibraryUserDto.getEmail();
        Assertions.assertEquals(expectedEmail, actualEmail);
    }
}
