package se.lexicon.library.service;

import se.lexicon.library.dto.LibraryUserDto;
import se.lexicon.library.exception.DataNotFoundException;

import java.util.List;

public interface LibraryUserService {
    LibraryUserDto findById(int userId)throws DataNotFoundException;
    LibraryUserDto findByEmail(String email);
    List<LibraryUserDto> findAll();
    LibraryUserDto create(LibraryUserDto libraryUserDto);
    LibraryUserDto update(LibraryUserDto libraryUserDto)throws DataNotFoundException;
    boolean delete(int userId)throws DataNotFoundException;
}
