package se.lexicon.library.dto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

@SpringBootTest
public class BookDtoTest {

    BookDto testBookDto;

    @BeforeEach
    public void setUp(){

        testBookDto = new BookDto();
        testBookDto.setTitle("Från Sverige till Absurdistan");
        testBookDto.setAvailable(true);
        testBookDto.setReserved(false);
        testBookDto.setMaxLoanDays(31);
        testBookDto.setFinePerDay(BigDecimal.valueOf(25));
        testBookDto.setDescription("A True Story");

    }
    @Test
    @DisplayName("Test 1 - Title")
    public void get_title_test(){
        String expectedTitle = "Från Sverige till Absurdistan";
        String actualTitle = testBookDto.getTitle();
        Assertions.assertEquals(expectedTitle, actualTitle);
    }
    @Test
    @DisplayName("Test 2 - Availability")
    public void get_availability_test(){
        boolean expectedAvailability = true;
        boolean actualAvailability = testBookDto.isAvailable();
        Assertions.assertEquals(expectedAvailability, actualAvailability);
    }

    @Test
    @DisplayName("Test 3 - Reservation")
    public void get_reservation_status_test(){
        boolean expectedReservation = false;
        boolean actualReservation = testBookDto.isReserved();
        Assertions.assertEquals(expectedReservation, actualReservation);
    }

    @Test
    @DisplayName("Test 4 - MaxLoanDays")
    public void get_max_loan_days_test(){
        int expectedMaxLoanDays = 31;
        int actualMaxLoanDays = testBookDto.getMaxLoanDays();
        Assertions.assertEquals(expectedMaxLoanDays, actualMaxLoanDays);
    }

    @Test
    @DisplayName("Test 5 - FinePerDay")
    public void get_fine_per_day_test(){
        BigDecimal expectedFinePerDay = BigDecimal.valueOf(25);
        BigDecimal actualFinePerDay = testBookDto.getFinePerDay();
        Assertions.assertEquals(expectedFinePerDay, actualFinePerDay);
    }
    @Test
    @DisplayName("Test 6 - Description")
    public void get_description_test(){
        String expectedDescription = "A True Story";
        String actualDescription = testBookDto.getDescription();
        Assertions.assertEquals(expectedDescription, actualDescription);
    }
}
