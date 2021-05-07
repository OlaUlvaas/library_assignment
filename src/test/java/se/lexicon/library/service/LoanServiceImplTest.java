package se.lexicon.library.service;

import org.springframework.boot.test.context.SpringBootTest;
import se.lexicon.library.dto.BookDto;
import se.lexicon.library.dto.LoanDto;
import se.lexicon.library.exception.DataNotFoundException;

import java.util.List;

@SpringBootTest
public class LoanServiceImplTest {

    LoanService testLoanService;
    LoanDto testLoanDto;



    /*LoanDto findById(long loanId)throws DataNotFoundException;
    List<LoanDto> findByBookId(int bookId);
    List<LoanDto> findByUserId(int userId);
    List<LoanDto>  findByTerminated(boolean status);
    List<LoanDto> findAll();
    LoanDto create(LoanDto loanDto);
    LoanDto update(LoanDto loanDto)throws DataNotFoundException;
    boolean delete(long loanId);*/
}
