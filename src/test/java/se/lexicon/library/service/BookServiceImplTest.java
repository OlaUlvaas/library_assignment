package se.lexicon.library.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import se.lexicon.library.dto.BookDto;
import se.lexicon.library.entity.Book;
import se.lexicon.library.exception.DataNotFoundException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class BookServiceImplTest {

    BookService testBookService;
    BookDto testBookDto1;
    //BookDto testBookDto2;

    @Autowired
    public void setTestBookService(BookService testBookService) {
        this.testBookService = testBookService;
    }

    @BeforeEach
    public void setUp(){
        testBookDto1 = new BookDto();
        //testBookDto2 = new BookDto();

        testBookDto1.setTitle("Från Sverige till Absurdistan");
        testBookDto1.setAvailable(true);
        testBookDto1.setReserved(false);
        testBookDto1.setFinePerDay(BigDecimal.valueOf(25));
        testBookDto1.setMaxLoanDays(31);
        testBookDto1.setDescription("A True Story");

        testBookService.create(testBookDto1);
    }
    @Test
    @DisplayName("Test 1 - Create Method")
    public void create() {

        String expectedDescription = "A True Story";
        String actualDescription = testBookService.create(testBookDto1).getDescription();
        Assertions.assertEquals(expectedDescription, actualDescription);
    }

    @Test
    @DisplayName("Test 2 - Find By Reserved Method")
    public void findByReserved() {
        String expectedTitle = "Från Sverige till Absurdistan";
        String actualTitle = testBookService.findByReserved(false).get(0).getTitle();
        Assertions.assertEquals(expectedTitle, actualTitle);
    }

    @Test
    @DisplayName("Test 3 - Find By Available Method")
    public void findByAvailable() {
        String expectedTitle = "Från Sverige till Absurdistan";
        String actualTitle = testBookService.findByAvailable(true).get(0).getTitle();
        Assertions.assertEquals(expectedTitle, actualTitle);

    }

    @Test
    @DisplayName("Test 4 - Find By Title Method")
    public void findByTitle() {
        String expectedTitle = "Från Sverige till Absurdistan";
        String actualTitle = testBookService.findByTitle("Från Sverige till Absurdistan").get(0).getTitle();
        Assertions.assertEquals(expectedTitle, actualTitle);
    }

    @Test
    @DisplayName("Test 5 - Find All Method")
    public void findAll() {
        int expectedSize = 1;
        int actualSize = testBookService.findAll().size();
        Assertions.assertEquals(expectedSize, actualSize);

    }



}
