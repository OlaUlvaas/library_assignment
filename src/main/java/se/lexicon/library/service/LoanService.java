package se.lexicon.library.service;

import se.lexicon.library.dto.LoanDto;
import se.lexicon.library.exception.DataNotFoundException;

import java.util.List;

public interface LoanService {
    LoanDto findById(long loanId)throws DataNotFoundException;
    List<LoanDto> findByBookId(int bookId);
    List<LoanDto> findByUserId(int userId);
    List<LoanDto>  findByTerminated(boolean status);
    List<LoanDto> findAll();
    LoanDto create(LoanDto loanDto);
    LoanDto update(LoanDto loanDto)throws DataNotFoundException;
    boolean delete(long loanId);


}

