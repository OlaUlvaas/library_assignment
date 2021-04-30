package se.lexicon.library.repository;

import org.apache.tomcat.jni.Library;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import se.lexicon.library.entity.Book;
import se.lexicon.library.entity.LibraryUser;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class LibraryUserTest {

    LibraryUser testLibraryUser;
    LibraryUser testLibraryUser2;

    LibraryUserRepository testLibraryUserRepository;

    @Autowired
    public void setTestLibraryUserRepository(LibraryUserRepository testLibraryUserRepository) {
        this.testLibraryUserRepository = testLibraryUserRepository;
    }

    @BeforeEach
    public void setUp(){
        testLibraryUser = new LibraryUser();
        testLibraryUser.setRegDate(LocalDate.of(1999,9,9));
        testLibraryUser.setName("Pelle Plutt");
        testLibraryUser.setEmail("pp@home.se");
        testLibraryUserRepository.save(testLibraryUser);

        testLibraryUser2 = new LibraryUser();
        testLibraryUser2.setRegDate(LocalDate.of(2000, 10,10));
        testLibraryUser2.setName("Milda Matilda");
        testLibraryUser2.setEmail("mm@live.se");
        testLibraryUserRepository.save(testLibraryUser2);
    }
    @Test
    @DisplayName("Test 1 - Find By Id")
    public void find_by_id_test(){
        List<LibraryUser> allUsers = new ArrayList<>();
        testLibraryUserRepository.findAll().iterator().forEachRemaining(allUsers :: add);
        LibraryUser expectedUser = allUsers.get(0);
        LibraryUser actualUser = testLibraryUserRepository.findById(1).get();
        Assertions.assertEquals(expectedUser, actualUser);
    }

    @Test
    @DisplayName("Test 2 - Find All")
    public void find_all_test(){
        List<LibraryUser> allUsers = new ArrayList<>();
        testLibraryUserRepository.findAll().iterator().forEachRemaining(allUsers::add);
        Assertions.assertEquals(2, allUsers.size());
    }

    @Test
    @DisplayName("Test 3 - Save")
    public void save_test(){
        testLibraryUserRepository.save(testLibraryUser);
        Assertions.assertNotNull(testLibraryUserRepository.findAll());

    }

    @Test
    @DisplayName("Test 4 - Delete")
    public void delete_test(){
        List<LibraryUser> allLibraryUsers = new ArrayList<>();
        testLibraryUserRepository.save(testLibraryUser);
        testLibraryUserRepository.save(testLibraryUser2);
        testLibraryUserRepository.delete(testLibraryUser);
        testLibraryUserRepository.delete(testLibraryUser2);
        List<LibraryUser> emptyLibraryUserList= new ArrayList<>();
        testLibraryUserRepository.findAll().iterator().forEachRemaining(allLibraryUsers::add);

        List<LibraryUser> expectedList = emptyLibraryUserList;
        List<LibraryUser> actualList = allLibraryUsers;
        Assertions.assertEquals(expectedList, actualList);
    }

}
