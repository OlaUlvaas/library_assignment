package se.lexicon.library.repository;

import org.springframework.data.repository.CrudRepository;
import se.lexicon.library.entity.LibraryUser;

public interface LibraryUserRepository extends CrudRepository<LibraryUser, Integer> {

    LibraryUser findByEmailIgnoreCase(String email);

}
