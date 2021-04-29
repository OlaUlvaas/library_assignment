package se.lexicon.library.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

@SpringBootTest
public class BookTest {

    Book testBook;

    @BeforeEach
    public void setUp() {
        testBook = new Book();
        testBook.setTitle("Från Sverige till Absurdistan");
        testBook.setAvailable(false);
        testBook.setReserved(false);
        testBook.setMaxLoanDays(31);
        testBook.setFinePerDay(BigDecimal.valueOf(25));
        testBook.setDescription("A True Story");

    }

    @Test
    @DisplayName("Test 1 - Title")
    public void get_title_test(){

        String expectedTitle = "Från Sverige till Absurdistan";
        String actualTitle = testBook.getTitle();
        Assertions.assertEquals(expectedTitle, actualTitle);
    }

    @Test
    @DisplayName("Test 2 - Availability")
    public void get_availability_test(){
        boolean expectedAvailability = true;
        boolean actualAvailability = testBook.isAvailable();
        Assertions.assertEquals(expectedAvailability, actualAvailability);
    }

    @Test
    @DisplayName("Test 3 - Reservation")
    public void get_reservation_status_test(){
        boolean expectedReservation = false;
        boolean actualReservation = testBook.isReserved();
        Assertions.assertEquals(expectedReservation, actualReservation);
    }

    @Test
    @DisplayName("Test 4 - MaxLoanDays")
    public void get_max_loan_days_test(){
        int expectedMaxLoanDays = 31;
        int actualMaxLoanDays = testBook.getMaxLoanDays();
        Assertions.assertEquals(expectedMaxLoanDays, actualMaxLoanDays);
    }

    @Test
    @DisplayName("Test 5 - FinePerDay")
    public void get_fine_per_day_test(){
        BigDecimal expectedFinePerDay = BigDecimal.valueOf(25);
        BigDecimal actualFinePerDay = testBook.getFinePerDay();
        Assertions.assertEquals(expectedFinePerDay, actualFinePerDay);

    }
    @Test
    @DisplayName("Test 6 - Description")
    public void get_description_test(){
        String expectedDescription = "A True Story";
        String actualDescription = testBook.getDescription();
        Assertions.assertEquals(expectedDescription, actualDescription);
    }
    @Test
    @DisplayName("Test 7 - HashCode")
    public void get_hash_code_test(){
        Book expectedBook = new Book("Från Sverige till Absurdistan", 31,
                BigDecimal.valueOf(25), "A True Story");
        Book actualBook = testBook;
        Assertions.assertEquals(expectedBook.hashCode(), actualBook.hashCode());

    }

}
