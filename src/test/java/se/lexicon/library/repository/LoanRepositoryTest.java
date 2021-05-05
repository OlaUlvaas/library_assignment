package se.lexicon.library.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import se.lexicon.library.entity.Book;
import se.lexicon.library.entity.LibraryUser;
import se.lexicon.library.entity.Loan;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@SpringBootTest
public class LoanRepositoryTest {

    Book testBook;
    LibraryUser testLibraryUser;
    Loan testLoan;

    BookRepository testBookRepository;
    LibraryUserRepository testLibraryUserRepository;
    LoanRepository testLoanRepository;

    @Autowired
    public void setTestBookRepository(BookRepository testBookRepository) {
        this.testBookRepository = testBookRepository;
    }

    @Autowired
    public void setTestLibraryUserRepository(LibraryUserRepository testLibraryUserRepository) {
        this.testLibraryUserRepository = testLibraryUserRepository;
    }

    @Autowired
    public void setTestLoanRepository(LoanRepository testLoanRepository) {
        this.testLoanRepository = testLoanRepository;
    }

    @BeforeEach
    public void setUp(){
        testBook = new Book();
        testBook.setTitle("ABC");
        testBook.setAvailable(true);
        testBook.setReserved(false);
        testBook.setMaxLoanDays(31);
        testBook.setFinePerDay(BigDecimal.valueOf(25));
        testBook.setDescription("tjabba");
        testBookRepository.save(testBook);

        testLibraryUser = new LibraryUser();
        testLibraryUser.setRegDate(LocalDate.of(1999,9,9));
        testLibraryUser.setName("Pelle Plutt");
        testLibraryUser.setEmail("pp@home.se");
        testLibraryUserRepository.save(testLibraryUser);

        testLoan = new Loan();
        testLoan.setLoanTaker(testLibraryUser);
        testLoan.setBook(testBook);
        testLoan.setLoanDay(LocalDate.now());
        testLoan.setTerminated(false);
        testLoanRepository.save(testLoan);

    }

    @Test
    @DisplayName("Test 1 - Find By Id")
    public void find_by_id_test(){
        List<Loan> allLoans = new ArrayList<>();
        testLoanRepository.findAll().iterator().forEachRemaining(allLoans :: add);
        Loan expectedLoan = allLoans.get(0);
        Loan actualLoan = testLoanRepository.findById(1L).get();
        Assertions.assertEquals(expectedLoan, actualLoan);
    }

    @Test
    @DisplayName("Test 2 - Find All")
    public void find_all_test(){
        List<Loan> allLoans = new ArrayList<>();
        testLoanRepository.findAll().iterator().forEachRemaining(allLoans :: add);
        Assertions.assertEquals(1, allLoans.size());
    }

    @Test
    @DisplayName("Test 3 - Save")
    public void save_test(){
        testLoanRepository.save(testLoan);
        Assertions.assertNotNull(testLoanRepository.findAll());

    }

    @Test
    @DisplayName("Test 4 - Delete")
    public void delete_test(){
        List<Loan> allLoans = new ArrayList<>();
        testLoanRepository.save(testLoan);
        testLoanRepository.delete(testLoan);
        List<Loan> emptyLoanList = new ArrayList<>();
        testLoanRepository.findAll().iterator().forEachRemaining(allLoans :: add);

        List<Loan> expectedList = emptyLoanList;
        List<Loan> actualList = allLoans;
        Assertions.assertEquals(expectedList, actualList);
    }

    @Test
    @DisplayName("Test 5 - Find By Loan Taker Id")
    public void find_by_loan_taker_id_test(){

        String expectedName = "Pelle Plutt";
        String actualName = testLoanRepository.findLoansByLoanTakerId(1).get(0).getLoanTaker().getName();
        Assertions.assertEquals(expectedName, actualName);

    }

    @Test
    @DisplayName("Test 6 - Find By Book Id")
    public void find_by_book_id_test(){

        int expectedLoanDays = 31;
        int actualLoanDays = testLoanRepository.findLoansByBookId(1).get(0).getBook().getMaxLoanDays();
        Assertions.assertEquals(expectedLoanDays, actualLoanDays);
    }

    @Test
    @DisplayName("Test 7 - Find By Terminated")
    public void find_by_terminated_test(){

        String expectedBook = "ABC";
        String actualBook = testLoanRepository.findLoansByTerminated(false).get(0).getBook().getTitle();
        Assertions.assertEquals(expectedBook, actualBook);
    }
}
