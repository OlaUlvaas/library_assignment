package se.lexicon.library.repository;

import org.springframework.data.repository.CrudRepository;
import se.lexicon.library.entity.Loan;

import java.util.List;
import java.util.Optional;

public interface LoanRepository extends CrudRepository<Loan, Long> {

    List<Loan> findLoansByLoanTakerId(int id);

    List<Loan> findLoansByBookId(int id);

    List<Loan> findLoansByTerminated(boolean status);

}
