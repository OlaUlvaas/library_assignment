package se.lexicon.library.repository;

import org.springframework.data.repository.CrudRepository;
import se.lexicon.library.entity.Loan;

import java.util.List;
import java.util.Optional;

public interface LoanRepository extends CrudRepository<Loan, Long> {

    Optional<Loan> findByLoanTakerId(Long id);

    Optional<Loan> findByBookId(int id);

    List<Loan> findByTerminated(boolean status);

}
