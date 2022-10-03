//package com.edu.ulab.app.service.impl;
//
//import com.edu.ulab.app.dto.BookDto;
//import com.edu.ulab.app.entity.BookEntity;
//import com.edu.ulab.app.exception.NotFoundException;
//import com.edu.ulab.app.mapper.BookMapper;
//import com.edu.ulab.app.repository.BookRepository;
//import com.edu.ulab.app.service.BookService;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Service;
//
//import java.util.*;
//
//@Slf4j
//@Service
//public class BookServiceImpl implements BookService {
//
//    private final BookRepository bookRepository;
//    private final BookMapper bookMapper;
//
//
//    public BookServiceImpl(BookRepository bookRepository, BookMapper bookMapper) {
//        this.bookRepository = bookRepository;
//        this.bookMapper = bookMapper;
//    }
//
//    @Override
//    public BookDto createBook(BookDto bookDto) {
//        BookEntity bookEntity = bookMapper.bookDtoToBookEntity(bookDto);
//        log.info("Mapped bookEntity: {}", bookEntity);
//        BookEntity savedBook = bookRepository.save(bookEntity);
//        log.info("Saved bookEntity: {}", savedBook);
//        return bookMapper.bookEntityToBookDto(savedBook);
//    }
//
//    @Override
//    public BookDto updateBook(BookDto bookDto) {
//        BookEntity bookEntity = bookMapper.bookDtoToBookEntity(bookDto);
//        bookRepository.save(bookEntity);
//        log.info("Book {} with id: {} updated in storage.", bookDto);
//        return bookMapper.bookEntityToBookDto(bookEntity);
//
//    }
//
//    @Override
//    public BookDto getBookById(Long userId) {
//        Optional<BookEntity> bookEntity = bookRepository.findById(userId);
//        BookDto bookDto = bookMapper.bookEntityToBookDto(bookEntity.orElseThrow());
//        log.info("Book {} with id: {} get.", bookDto, userId);
//        return bookDto;
//    }
//
//    @Override
//    public void deleteBookById(Long userId) {
//        BookEntity bookEntity = bookRepository.findById(userId).orElseThrow(() -> new NotFoundException("User with id " + userId + " is not found"));
//        log.info("Books with userId: {} to be deleted", bookEntity);
//        bookRepository.delete(bookEntity);
//        log.info("Book {} of user {} deleted", bookEntity, userId);
//    }
//
//
//    @Override
//    public List<Long> getBooksListById(Long userId) {
//        List<Long> ids = new ArrayList<>();
//        for (BookEntity book : bookRepository.findAll()) {
//            if (book.getUserId().equals(userId)) {
//                ids.add(book.getId());
//            }
//        }
//        log.info("Books ID's list for user {} : {}", userId, ids);
//        return ids;
//    }
//
//
//}
