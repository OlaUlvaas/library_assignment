package se.lexicon.library.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.lexicon.library.dto.LibraryUserDto;
import se.lexicon.library.entity.LibraryUser;
import se.lexicon.library.repository.LibraryUserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LibraryUserServiceImpl implements LibraryUserService{

    LibraryUserRepository libraryUserRepository;
    ModelMapper modelMapper;

    @Autowired
    public void setLibraryUserRepository(LibraryUserRepository libraryUserRepository) {
        this.libraryUserRepository = libraryUserRepository;
    }

    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }


    @Override
    public LibraryUserDto findById(int userId) {
        /*if(userId == 0)*/
        LibraryUserDto result = modelMapper.map(libraryUserRepository.findById(userId), LibraryUserDto.class);
        return result;
    }

    @Override
    public LibraryUserDto findByEmail(String email) {
        /*if(email.equals(null))*/
        LibraryUserDto result = modelMapper.map(libraryUserRepository.findByEmailIgnoreCase(email),
                LibraryUserDto.class);
        return result;
    }

    @Override
    public List<LibraryUserDto> findAll() {
        List<LibraryUser> libraryUserList = new ArrayList<>();
        libraryUserRepository.findAll().iterator().forEachRemaining(libraryUserList :: add);
        List<LibraryUserDto> LibraryUserDtoList = libraryUserList.stream().map(libraryUser ->
                modelMapper.map(libraryUser, LibraryUserDto.class)).collect(Collectors.toList());
        return LibraryUserDtoList;
    }

    @Override
    public LibraryUserDto create(LibraryUserDto libraryUserDto) {
        LibraryUser libraryUserEntity = modelMapper.map(libraryUserDto, LibraryUser.class);

        LibraryUser createdUser = libraryUserRepository.save(libraryUserEntity);
        LibraryUserDto convertedToDto = modelMapper.map(createdUser, LibraryUserDto.class);
        return convertedToDto;
    }

    @Override
    public LibraryUserDto update(LibraryUserDto libraryUserDto) {

        Optional<LibraryUser> userOptional = libraryUserRepository.findById(libraryUserDto.getId());
        if(userOptional.isPresent()){
            LibraryUser libraryUserEntity = modelMapper.map(libraryUserDto, LibraryUser.class);
            LibraryUser savedUser = libraryUserRepository.save(libraryUserEntity);
            LibraryUserDto convertedToDto = modelMapper.map(savedUser, LibraryUserDto.class);
            return  convertedToDto;
        }
        return null;
    }

    @Override
    public boolean delete(int userId) {

        Optional<LibraryUser> optionalLibraryUser = libraryUserRepository.findById(userId);
        if(optionalLibraryUser.isPresent()){
            LibraryUser libraryUserEntity =modelMapper.map(optionalLibraryUser, LibraryUser.class);
            libraryUserRepository.delete(libraryUserEntity);
            return true;
        }
        return false;
    }
}