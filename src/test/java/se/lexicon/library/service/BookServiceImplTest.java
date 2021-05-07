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

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class BookServiceImplTest {

    BookService testBookService;
    BookDto testBookDto1;
    BookDto testBookDto2;

    @Autowired
    public void setTestBookService(BookService testBookService) {
        this.testBookService = testBookService;
    }

    @BeforeEach
    public void setUp(){
        testBookDto1 = new BookDto();
        testBookDto2 = new BookDto();

        testBookDto1.setTitle("Från Sverige till Absurdistan");
        testBookDto1.setAvailable(true);
        testBookDto1.setReserved(false);
        testBookDto1.setFinePerDay(BigDecimal.valueOf(25));
        testBookDto1.setMaxLoanDays(31);
        testBookDto1.setDescription("A True Story");

        testBookService.create(testBookDto1);

        testBookDto2.setTitle("New Book");
        testBookDto2.setAvailable(false);
        testBookDto2.setReserved(true);
        testBookDto2.setFinePerDay(BigDecimal.valueOf(25));
        testBookDto2.setMaxLoanDays(31);
        testBookDto2.setDescription("Fake News");
    }
    @Test
    @DisplayName("Test 1 - Create Method")
    public void create_test() {

        String expectedDescription = "A True Story";
        String actualDescription = testBookService.create(testBookDto1).getDescription();
        Assertions.assertEquals(expectedDescription, actualDescription);
    }

    @Test
    @DisplayName("Test 2 - Find By Reserved Method")
    public void find_by_reserved_test() {
        String expectedTitle = "Från Sverige till Absurdistan";
        String actualTitle = testBookService.findByReserved(false).get(0).getTitle();
        Assertions.assertEquals(expectedTitle, actualTitle);
    }

    @Test
    @DisplayName("Test 3 - Find By Available Method")
    public void find_by_available_test() {
        String expectedTitle = "Från Sverige till Absurdistan";
        String actualTitle = testBookService.findByAvailable(true).get(0).getTitle();
        Assertions.assertEquals(expectedTitle, actualTitle);

    }

    @Test
    @DisplayName("Test 4 - Find By Title Method")
    public void find_by_title_test() {
        String expectedTitle = "Från Sverige till Absurdistan";
        String actualTitle = testBookService.findByTitle("Från Sverige till Absurdistan").get(0).getTitle();
        Assertions.assertEquals(expectedTitle, actualTitle);
    }

    @Test
    @DisplayName("Test 5 - Find All Method")
    public void find_all_test() {
        int expectedSize = 1;
        int actualSize = testBookService.findAll().size();
        Assertions.assertEquals(expectedSize, actualSize);

    }

    @Test
    @DisplayName("Test 6 - Find By Id Method")
    public void find_by_id_test() throws DataNotFoundException {
        Assertions.assertEquals("A True Story", testBookService.findById(1).getDescription());
    }
    @Test
    @DisplayName("Test 7 - Delete Method")
    public void delete_test() throws DataNotFoundException {
        testBookService.create(testBookDto2);
        Assertions.assertEquals(2, testBookService.findAll().size());
        testBookService.delete(1);
        Assertions.assertEquals(1, testBookService.findAll().size());
    }

    @Test
    @DisplayName("Test 8 - Update Method")
    public void update_test()throws DataNotFoundException{
        testBookDto1.setId(1);
        testBookDto1.setDescription("A new Story");
        Assertions.assertEquals("A new Story", testBookService.update(testBookDto1).getDescription());
    }


}
