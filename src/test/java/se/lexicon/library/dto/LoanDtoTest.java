package se.lexicon.library.dto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import se.lexicon.library.entity.Book;
import se.lexicon.library.entity.LibraryUser;
import se.lexicon.library.entity.Loan;

import java.math.BigDecimal;

@SpringBootTest
public class LoanDtoTest {

    LoanDto testLoanDto;
    LibraryUserDto testUserDto;
    BookDto testBookDto;



    @BeforeEach
    public void setUp(){
        testLoanDto = new LoanDto();
        testUserDto = new LibraryUserDto();
        testUserDto.setName("Ola Ulvås");
        testUserDto.setEmail("olaulvas@hotmail.com");
        testBookDto = new BookDto();
        testBookDto.setTitle("Från Sverige till Absurdistan");
        testBookDto.setMaxLoanDays(31);
        testBookDto.setFinePerDay(BigDecimal.valueOf(25));
        testBookDto.setDescription("A True Story");

    }
    @Test
    @DisplayName("Test 1 - Lone Taker Name")
    public void create_loan_taker_name_test(){
        String expectedLoanTakerName = "Ola Ulvås";
        String actualLoanTakerName = testUserDto.getName();
        Assertions.assertEquals(expectedLoanTakerName, actualLoanTakerName);
    }

    @Test
    @DisplayName("Test 2 - Book Title")
    public void create_book_title_test(){
        String expectedBookTitle = "Från Sverige till Absurdistan";
        String actualBookTitle = testBookDto.getTitle();
        Assertions.assertEquals(expectedBookTitle, actualBookTitle);
    }

    @Test
    @DisplayName("Test 3 - Fine Per Day")
    public void create_fine_per_day_test(){
        BigDecimal expectedFine = BigDecimal.valueOf(25);
        BigDecimal actualFine = testBookDto.getFinePerDay();
        Assertions.assertEquals(expectedFine, actualFine);
    }

    @Test
    @DisplayName("Test 4 - Terminated")
    public void terminated_status_test(){
        boolean expectedStatus = false;
        boolean actualStatus = testLoanDto.isTerminated();
        Assertions.assertEquals(expectedStatus, actualStatus);
    }
}
