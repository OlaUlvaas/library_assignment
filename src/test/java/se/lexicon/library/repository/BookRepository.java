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
public class BookRepository {

    BookRepository testBookRepository;
    Book testBook;

    @Autowired
    public void setTestBookRepository(BookRepository testBookRepository) {
        this.testBookRepository = testBookRepository;
    }

    @BeforeEach
    public void setUp(){
        //testBookRepository = new BookRepository();
        testBook = new Book();
        testBook.setTitle("ABC");
        testBook.setAvailable(true);
        testBook.setReserved(false);
        testBook.setMaxLoanDays(31);
        testBook.setFinePerDay(BigDecimal.valueOf(25));
        testBook.setDescription("tjabba");

        testBookRepository.save(testBook);

    }

    // findById, findAll, save and delete

    /*<S extends T> S save(S entity);

    Optional<T> findById(ID primaryKey);

    Iterable<T> findAll();

    long count();

    void delete(T entity);

    boolean existsById(ID primaryKey);*/



    @Test
    @DisplayName("Test 1 - Find By Id")
    public void find_by_id_test(){
        List<Book> allBooks = new ArrayList<>();
        testBookRepository.findAll().iterator().forEachRemaining(allBooks :: add);
        Book expectedBook = allBooks.get(0);
        Book actualBook = testBookRepository.findById(1).get();
        Assertions.assertEquals(expectedBook, actualBook);

        // JAVA CANNOT FIND SYMBOL????

    }
    @Test
    @DisplayName("Test 2 - Find All")
    public void find_all_test(){
        List<Book> allBooks = new ArrayList<>();
        testBookRepository.findAll().iterator().forEachRemaining(allBooks::add);
        Assertions.assertEquals(1, allBooks.size());

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
        testBookRepository.save(testBook);
        testBookRepository.delete(testBook);
        Assertions.assertNull(testBookRepository.findAll());
    }

}
