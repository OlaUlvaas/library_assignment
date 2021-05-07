package se.lexicon.library.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import se.lexicon.library.dto.BookDto;
import se.lexicon.library.dto.LibraryUserDto;
import se.lexicon.library.dto.LoanDto;
import se.lexicon.library.entity.Book;
import se.lexicon.library.entity.LibraryUser;
import se.lexicon.library.exception.DataNotFoundException;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class LoanServiceImplTest {

    LoanService testLoanService;
    BookService testBookService;
    LibraryUserService testLibraryUserService;

    LoanDto testLoanDto;
    LoanDto testLoanDto2;
    BookDto testBookDto;
    BookDto testBookDto2;
    LibraryUserDto testLibraryUserDto;
    LibraryUserDto testLibraryUserDto2;


    @Autowired
    public void setTestLoanService(LoanService testLoanService) {
        this.testLoanService = testLoanService;
    }

    @Autowired
    public void setTestBookService(BookService testBookService) {
        this.testBookService = testBookService;
    }

    @Autowired
    public void setTestLibraryUserService(LibraryUserService testLibraryUserService) {
        this.testLibraryUserService = testLibraryUserService;
    }

    @BeforeEach
    public void setUp(){
        testLoanDto = new LoanDto();
        testBookDto = new BookDto();
        testLibraryUserDto = new LibraryUserDto();

        testLibraryUserDto.setRegDate(LocalDate.of(1970,6,4));
        testLibraryUserDto.setName("Ola Ulvås");
        testLibraryUserDto.setEmail("olaulvas@hotmail.com");

        testBookDto.setTitle("Från Sverige till Absurdistan");
        testBookDto.setAvailable(true);
        testBookDto.setReserved(false);
        testBookDto.setFinePerDay(BigDecimal.valueOf(25));
        testBookDto.setMaxLoanDays(31);
        testBookDto.setDescription("A True Story");

        testLoanDto.setLoanTakerDto(testLibraryUserDto);
        testLoanDto.setBookDto(testBookDto);
        testLoanDto.setLoanDay(LocalDate.of(2021,5,7));
        testLoanDto.setTerminated(false);

        testBookService.create(testBookDto);
        testLibraryUserService.create(testLibraryUserDto);

        testLoanService.create(testLoanDto);

        /*testLibraryUserDto2.setRegDate(LocalDate.of(1999,9,9));
        testLibraryUserDto2.setName("Mr President");
        testLibraryUserDto2.setEmail("drain@swamp.com");*/
    }
    /*@Test
    @DisplayName("Test 1 - Find By Id Method")
    public void find_by_id_test() throws DataNotFoundException {
        LoanDto expectedDto = testLoanDto;
        LoanDto actualDto = testLoanService.findById(1);
        assertEquals(expectedDto, actualDto);
    }*/

    /*@Test
    @DisplayName("Test 2 - Find By Book Id")
    public void find_by_book_id_test(){
        assertEquals(1, testLoanService.findAll().get(0).getId());
    }*/
    /*@Test
    @DisplayName("Test 3 - Find By User Id")
    public void find_by_user_id_test(){
        assertEquals();
    }*/
    /*@Test
    @DisplayName("Test 4 - Find By Terminated")
    public void find_by_terminated_test(){
        List<LoanDto> allLoans = new ArrayList<>();
        testLoanService.findByTerminated(false).iterator().forEachRemaining(allLoans :: add);
        List<LoanDto> expectedList = allLoans;
        List<LoanDto> actualList = testLoanService.findByTerminated(false);

        assertEquals(expectedList, actualList);
    }*/
    /*@Test
    @DisplayName("Test 5 - Find All")
    public void find_all_test(){
        List<LoanDto> allLoans = new ArrayList<>();
        testLoanService.findAll().iterator().forEachRemaining(allLoans :: add);


        assertEquals(1, allLoans.size());
    }*/
    /*@Test
    @DisplayName("Test 6 - Create")
    public void create_test(){

        testLoanService.create(testLoanDto2);

        int expectedSize = 2;
        int actualSize = testLoanService.findAll().size();


        assertEquals(expectedSize, actualSize);
    }*/

    /*@Test
    @DisplayName("Test 7 - Update")
    public void update_test() throws DataNotFoundException {

        *//*testLoanDto.setId(1);
        testLoanDto.setTerminated(true);
        assertEquals(true, testLoanService.update(testLoanDto).isTerminated());

        *//*


        *//*testLoanDto.setTerminated(true);
        try {
            testLoanService.update(testLoanDto);
            assertEquals(true, testLoanService.update(true).isTerminated());
        } catch (DataNotFoundException e) {
            e.printStackTrace();
        }*//*
    }*/

    /*@Test
    @DisplayName("Test 8 - Delete")
    public void delete_test(){
        testLoanService.create(testLoanDto2);
        Assertions.assertEquals(2, testLoanService.findAll().size());
        testLoanService.delete(1);
        Assertions.assertEquals(1, testLoanService.findAll().size());
    }*/


    /*
    LoanDto findById(long loanId)throws DataNotFoundException; todo: whats wrong
    List<LoanDto> findByBookId(int bookId); todo: whats wrong
    List<LoanDto> findByUserId(int userId); todo: whats wrong
    List<LoanDto>  findByTerminated(boolean status); todo: whats wrong
    List<LoanDto> findAll(); todo: whats wrong
    LoanDto create(LoanDto loanDto); todo: whats wrong
    LoanDto update(LoanDto loanDto)throws DataNotFoundException; todo: whats wrong
    boolean delete(long loanId); todo: whats wrong

    */
}
