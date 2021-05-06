package se.lexicon.library.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.lexicon.library.dto.LibraryUserDto;
import se.lexicon.library.dto.LoanDto;
import se.lexicon.library.entity.LibraryUser;
import se.lexicon.library.entity.Loan;
import se.lexicon.library.exception.DataNotFoundException;
import se.lexicon.library.repository.LoanRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LoanServiceImpl implements LoanService{

    LoanRepository loanRepository;
    ModelMapper modelMapper;

    @Autowired
    public void setLoanRepository(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }

    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }



    @Override
    public LoanDto findById(long loanId) throws DataNotFoundException {

        if (loanId == 0) throw new IllegalArgumentException("Id should not be zero");
        LoanDto result = modelMapper.map(loanRepository.findById(loanId)
                .orElseThrow(() -> new DataNotFoundException("LoanDto not found")), LoanDto.class);

        return result;
    }

    @Override
    public List<LoanDto> findByBookId(int bookId) {
        if (bookId == 0) throw new IllegalArgumentException("Id should not be zero");
        List<LoanDto> loanDtoList = loanRepository.findLoansByBookId(bookId).stream().map(loan ->
                modelMapper.map(loan, LoanDto.class)).collect(Collectors.toList());
        return loanDtoList;
    }

    @Override
    public List<LoanDto> findByUserId(int userId) {
        if (userId == 0) throw new IllegalArgumentException("Id should not be zero");
        List<LoanDto> loanDtoList = loanRepository.findLoansByLoanTakerId(userId).stream().map(loan ->
                modelMapper.map(loan, LoanDto.class)).collect(Collectors.toList());
        return loanDtoList;
    }

    @Override
    public List<LoanDto> findByTerminated(boolean status) {
        List<LoanDto> loanDtoList = loanRepository.findLoansByTerminated(status).stream().map(loan ->
                modelMapper.map(loan, LoanDto.class)).collect(Collectors.toList());
        return loanDtoList;
    }

    @Override
    public List<LoanDto> findAll() {
        List<Loan> loanList = new ArrayList<>();
        loanRepository.findAll().iterator().forEachRemaining(loanList :: add);
        List<LoanDto> loanDtoList = loanList.stream().map(loan ->
                modelMapper.map(loan, LoanDto.class)).collect(Collectors.toList());
        return loanDtoList;
    }

    @Override
    public LoanDto create(LoanDto loanDto) {
        if (loanDto.equals(null)) throw new IllegalArgumentException("LoanDto should not be null");
        if (loanDto.getId() != 0) throw new IllegalArgumentException("Id is not valid");
        Loan loanEntity = modelMapper.map(loanDto, Loan.class);
        Loan createdLoan = loanRepository.save(loanEntity);
        LoanDto convertedToDto = modelMapper.map(createdLoan, LoanDto.class);

        return convertedToDto;
    }

    @Override
    public LoanDto update(LoanDto loanDto) throws DataNotFoundException{
        if (loanDto.equals(null)) throw new IllegalArgumentException("LoanDto should not be null");
        if (loanDto.getId() == 0) throw new IllegalArgumentException("Id is not valid");
        Optional<Loan> loanOptional = loanRepository.findById(loanDto.getId());
        if(loanOptional.isPresent()){
            Loan loanEntity = modelMapper.map(loanDto, Loan.class);
            Loan savedLoan = loanRepository.save(loanEntity);
            LoanDto convertedToDto = modelMapper.map(savedLoan, LoanDto.class);
            return convertedToDto;
        }
        return null;
    }

    @Override
    public boolean delete(long loanId) {
        if (loanId == 0) throw new IllegalArgumentException("Id should not be zero");
        Optional<Loan> optionalLoan = loanRepository.findById(loanId);
        if (optionalLoan.isPresent()) {
            Loan loanEntity = modelMapper.map(optionalLoan, Loan.class);
            loanRepository.delete(loanEntity);
            return true;
        }
        return false;
    }
}
