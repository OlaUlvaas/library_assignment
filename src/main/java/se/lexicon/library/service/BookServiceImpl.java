package se.lexicon.library.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.lexicon.library.dto.BookDto;
import se.lexicon.library.entity.Book;
import se.lexicon.library.exception.DataNotFoundException;
import se.lexicon.library.repository.BookRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService{

    BookRepository bookRepository;
    ModelMapper modelMapper;

    @Autowired
    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }



    @Override
    public List<BookDto> findByReserved(boolean reserved) {
        List<BookDto> status = bookRepository.findBooksByReserved(reserved).stream().map(book ->
                modelMapper.map(book, BookDto.class)).collect(Collectors.toList());
        return status;
    }

    @Override
    public List<BookDto> findByAvailable(boolean available) {
        List<BookDto> status = bookRepository.findBooksByAvailable(available).stream().map(book ->
                modelMapper.map(book, BookDto.class)).collect(Collectors.toList());
        return status;
    }

    @Override
    public List<BookDto> findByTitle(String title) {
        if(title.equals(null)) throw new IllegalArgumentException("Title should not be null");

        List<BookDto> titleList = bookRepository.findBookByTitle(title).stream().map(b ->
                modelMapper.map(b, BookDto.class)).collect(Collectors.toList());

        return titleList;
    }

    @Override
    public BookDto findById(int bookId) throws DataNotFoundException {

        if (bookId == 0) throw new IllegalArgumentException("Book Id should not be zero");
        BookDto result = modelMapper.map(bookRepository.findById(bookId)
                .orElseThrow(() -> new DataNotFoundException("BookDto not found")),BookDto.class);
        return result;
    }

    @Override
    public List<BookDto> findAll() {
        List<Book> bookList = new ArrayList<>();
        bookRepository.findAll().iterator().forEachRemaining(bookList::add);
        List<BookDto> bookDtoList = bookList.stream().map(book ->
                modelMapper.map(book, BookDto.class)).collect(Collectors.toList());
        return bookDtoList;
    }

    @Override
    public BookDto create(BookDto bookDto) {
        if(bookDto.equals(null)) throw new IllegalArgumentException("BookDto not found");
        if(bookDto.getId() != 0) throw new IllegalArgumentException("Id should be zero");

        BookDto result =  modelMapper.map(bookRepository.save(modelMapper.map(bookDto, Book.class)),BookDto.class);
        return result;
    }

    @Override
    public BookDto update(BookDto bookDto) throws DataNotFoundException {
        if (bookDto.equals(null)) throw new IllegalArgumentException("BookDto not found");
        if (bookDto.getId() == 0) throw new IllegalArgumentException("BookDto is not valid");

        Optional<Book> bookOptional = bookRepository.findById(modelMapper.map(bookDto,Book.class).getId());
        if (bookOptional.isPresent()){
            BookDto result = modelMapper.map(bookRepository.save(modelMapper.map(bookDto,Book.class)),BookDto.class);
            return result;
        }
        return null;

    }

    @Override
    public boolean delete(int bookId) throws DataNotFoundException {

        /*if (bookId == 0) throw new IllegalArgumentException("Book Id should not be zero");
        Optional<Book> optionalBook = bookRepository.findById(bookId);
        if (optionalBook.isPresent()) {
            Book bookEntity = modelMapper.map(optionalBook, Book.class);
            bookRepository.delete(bookEntity);
            return true;
        }
        return false;*/

        bookRepository.delete(modelMapper.map(bookRepository.findById(bookId)
                .orElseThrow(() -> new DataNotFoundException("Id ")), Book.class));
        return true;
    }
}
