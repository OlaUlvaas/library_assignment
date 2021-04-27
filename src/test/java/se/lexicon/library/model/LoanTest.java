package se.lexicon.library.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
@SpringBootTest
public class LoanTest {

    Loan testLoan;
    LibraryUser testUser;
    Book testBook;



    @BeforeEach
    public void setUp(){
        testLoan = new Loan();
        testUser = new LibraryUser(null, "Ola Ulvås", "olaulvas@hotmail.com");
        testBook = new Book("Från Sverige till Absurdistan", 31, BigDecimal.valueOf(25),
                "A True Story");

    }

    @Test
    @DisplayName("Test 1 - LoneTaker")
    public void create_loan_taker_test(){
        LibraryUser expectedLoanTaker = new LibraryUser(null, "Ola Ulvås",
                "olaulvas@hotmail.com");
        LibraryUser actualLoanTaker = testUser;
        Assertions.assertEquals(expectedLoanTaker, actualLoanTaker);
    }

    @Test
    @DisplayName("Test 2 - Book")
    public void create_book_test(){
        Book expectedBook = new Book("Från Sverige till Absurdistan", 31,
                BigDecimal.valueOf(25), "A True Story");
        Book actualBook = testBook;
        Assertions.assertEquals(expectedBook, actualBook);
    }
    @Test
    @DisplayName("Test 3 - Terminated")
    public void terminated_status_test(){
        boolean expectedStatus = false;
        boolean actualStatus = testLoan.isTerminated();
        Assertions.assertEquals(expectedStatus, actualStatus);
    }
}
