package se.lexicon.library.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

@SpringBootTest
public class LoanTest {
    Loan testLoan;
    LibraryUser testUser;
    Book testBook;



    @BeforeEach
    public void setUp(){
        testLoan = new Loan();
        testUser = new LibraryUser();
        testUser.setName("Ola Ulvås");
        testUser.setEmail("olaulvas@hotmail.com");
        testBook = new Book();
        testBook.setTitle("Från Sverige till Absurdistan");
        testBook.setMaxLoanDays(31);
        testBook.setFinePerDay(BigDecimal.valueOf(25));
        testBook.setDescription("A True Story");

    }
    @Test
    @DisplayName("Test 1 - Lone Taker Name")
    public void create_loan_taker_name_test(){
        String expectedLoanTakerName = "Ola Ulvås";
        String actualLoanTakerName = testUser.getName();
        Assertions.assertEquals(expectedLoanTakerName, actualLoanTakerName);
    }

    @Test
    @DisplayName("Test 2 - Book Title")
    public void create_book_title_test(){
        String expectedBookTitle = "Från Sverige till Absurdistan";
        String actualBookTitle = testBook.getTitle();
        Assertions.assertEquals(expectedBookTitle, actualBookTitle);
    }

    @Test
    @DisplayName("Test 3 - Fine Per Day")
    public void create_fine_per_day_test(){
        BigDecimal expectedFine = BigDecimal.valueOf(25);
        BigDecimal actualFine = testBook.getFinePerDay();
        Assertions.assertEquals(expectedFine, actualFine);
    }

    @Test
    @DisplayName("Test 4 - Terminated")
    public void terminated_status_test(){
        boolean expectedStatus = false;
        boolean actualStatus = testLoan.isTerminated();
        Assertions.assertEquals(expectedStatus, actualStatus);
    }
}
