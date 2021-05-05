package se.lexicon.library.service;

import se.lexicon.library.dto.LoanDto;

import java.util.List;

public interface LoanService {
    LoanDto findById(long loanId);
    List<LoanDto> findByBookId(int bookId);
    List<LoanDto> findByUserId(int userId);
    List<LoanDto>  findByTerminated(boolean status);
    List<LoanDto> findAll();
    LoanDto create(LoanDto loanDto);
    LoanDto update(LoanDto loanDto);
    boolean delete(long loanId);


}

