package se.lexicon.library.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import se.lexicon.library.dto.BookDto;
import se.lexicon.library.dto.LibraryUserDto;
import se.lexicon.library.exception.DataNotFoundException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class LibraryUserServiceImplTest {

    LibraryUserService testLibraryUserService;
    LibraryUserDto testLibraryUserDto;
    LibraryUserDto testLibraryUserDto2;

    @Autowired
    public void setTestLibraryUserService(LibraryUserService testLibraryUserService) {
        this.testLibraryUserService = testLibraryUserService;
    }

    @BeforeEach
    public void setUp(){
        testLibraryUserDto = new LibraryUserDto();
        testLibraryUserDto2 = new LibraryUserDto();

        testLibraryUserDto.setRegDate(LocalDate.of(1970,6,4));
        testLibraryUserDto.setName("Ola Ulvås");
        testLibraryUserDto.setEmail("olaulvas@hotmail.com");


        testLibraryUserService.create(testLibraryUserDto);

        testLibraryUserDto2.setRegDate(LocalDate.of(1999,9,9));
        testLibraryUserDto2.setName("Mr President");
        testLibraryUserDto2.setEmail("drain@swamp.com");
    }
    @Test
    @DisplayName("Test 1 - Find By Id Method")
    public void find_by_id_test() throws DataNotFoundException {
        String exceptedName = "Ola Ulvås";
        String actualName = testLibraryUserService.findById(1).getName();
        assertEquals(exceptedName, actualName);
    }

    @Test
    @DisplayName("Test 2 - Find By Email Method")
    public void find_by_email_test(){
        String expectedEmail = "olaulvas@hotmail.com";
        String actualEmail = testLibraryUserService.findByEmail("olaulvas@hotmail.com").getEmail();
        assertEquals(expectedEmail, actualEmail);
    }

    @Test
    @DisplayName("Test 3 - Find All Method")
    public void find_all_test(){
        int expectedSize = 1;
        int actualSize = testLibraryUserService.findAll().size();
        assertEquals(expectedSize, actualSize);
    }
    @Test
    @DisplayName("Test 4 - Create Method")
    public void create_test() {

        String expectedEmail = "drain@swamp.com";
        String actualEmail = testLibraryUserService.create(testLibraryUserDto2).getEmail();
        assertEquals(expectedEmail, actualEmail);
    }

    @Test
    @DisplayName("Test 5 - Update Method")
    public void update_test() throws DataNotFoundException {
        testLibraryUserDto.setId(1);
        testLibraryUserDto.setName("Donald Trump");
        assertEquals("Donald Trump", testLibraryUserService.update(testLibraryUserDto).getName());
    }

    @Test
    @DisplayName("Test 6 - Delete Method")
    public void delete_test() {
        /*testObject.create(libraryUserDto2);

        assertEquals(2, testObject.findAll().size());
        testObject.delete(2);
        assertEquals(1, testObject.findAll().size());*/

        testLibraryUserService.create(testLibraryUserDto2);
        assertEquals(2, testLibraryUserService.findAll().size());

    }




    /*



    boolean delete(int userId);


    LibraryUserDto findById(int userId)throws DataNotFoundException;
    LibraryUserDto findByEmail(String email);
    List<LibraryUserDto> findAll();
    LibraryUserDto create(LibraryUserDto libraryUserDto);
    LibraryUserDto update(LibraryUserDto libraryUserDto)throws DataNotFoundException;

    */
}
