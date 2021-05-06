package se.lexicon.library.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import se.lexicon.library.entity.Book;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class BookRepositoryTest {

    BookRepository testBookRepository;
    Book testBook;
    Book testBook2;

    @Autowired
    public void setTestBookRepository(BookRepository testBookRepository) {
        this.testBookRepository = testBookRepository;
    }

    @BeforeEach
    public void setUp(){
        //testBookRepository = new BookRepository();
        testBook = new Book();
        testBook.setTitle("ABC");
        testBook.setAvailable(false);
        testBook.setReserved(false);
        testBook.setMaxLoanDays(31);
        testBook.setFinePerDay(BigDecimal.valueOf(25));
        testBook.setDescription("tjabba");

        testBookRepository.save(testBook);

        testBook2 = new Book();
        testBook2.setTitle("EFG");
        testBook2.setAvailable(true);
        testBook2.setReserved(true);
        testBook2.setMaxLoanDays(31);
        testBook2.setFinePerDay(BigDecimal.valueOf(25));
        testBook2.setDescription("hello");

        testBookRepository.save(testBook2);



    }





    @Test
    @DisplayName("Test 1 - Find By Id")
    public void find_by_id_test(){
        List<Book> allBooks = new ArrayList<>();
        testBookRepository.findAll().iterator().forEachRemaining(allBooks :: add);
        Book expectedBook = allBooks.get(0);
        Book actualBook = testBookRepository.findById(1).get();
        Assertions.assertEquals(expectedBook, actualBook);
    }

    @Test
    @DisplayName("Test 2 - Find All")
    public void find_all_test(){
        List<Book> allBooks = new ArrayList<>();
        testBookRepository.findAll().iterator().forEachRemaining(allBooks::add);
        Assertions.assertEquals(2, allBooks.size());

    }

    @Test
    @DisplayName("Test 3 - Save")
    public void save_test(){
        testBookRepository.save(testBook);
        Assertions.assertNotNull(testBookRepository.findAll());

    }

    @Test
    @DisplayName("Test 4 - Delete")
    public void delete_test(){
        List<Book> allBooks = new ArrayList<>();
        testBookRepository.save(testBook);
        testBookRepository.save(testBook2);
        testBookRepository.delete(testBook);
        testBookRepository.delete(testBook2);
        List<Book> emptyBookList= new ArrayList<>();
        testBookRepository.findAll().iterator().forEachRemaining(allBooks::add);

        List<Book> expectedList = emptyBookList;
        List<Book> actualList = allBooks;
        Assertions.assertEquals(expectedList, actualList);
    }

    @Test
    @DisplayName("Test 5 - Find Books By Reserved")
    public void find_by_reserved_test(){
        String expectedBook = "ABC";
        String actualBook = testBookRepository.findBooksByReserved(false).get(0).getTitle();
        Assertions.assertEquals(expectedBook, actualBook);
    }

    @Test
    @DisplayName("Test 6 - Find Books By Available")
    public void find_by_available_test(){
        String expectedBook = "EFG";
        String actualBook = testBookRepository.findBooksByAvailable(true).get(0).getTitle();
        Assertions.assertEquals(expectedBook, actualBook);
    }

    @Test
    @DisplayName("Test 7 - Find Book By Title")
    public void find_by_title_test(){
        String expectedDescription = "tjabba";
        String actualDescription = testBookRepository.findBookByTitle("ABC").get(0).getDescription();
        Assertions.assertEquals(expectedDescription, actualDescription);
    }
}
