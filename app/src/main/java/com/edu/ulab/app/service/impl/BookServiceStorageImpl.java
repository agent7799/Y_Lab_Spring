//package com.edu.ulab.app.service.impl;
//
//import com.edu.ulab.app.dto.BookDto;
//import com.edu.ulab.app.service.BookService;
//import com.edu.ulab.app.storage.BookStorage;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//
//@Slf4j
//@Service
//public class BookServiceStorageImpl implements BookService {
//    private static long bookId;
//    final
//    BookStorage bookStorage;
//
//    public BookServiceStorageImpl(BookStorage bookStorage) {
//        this.bookStorage = bookStorage;
//    }
//
//    @Override
//    public BookDto createBook(BookDto bookDto) {       //bookDto with userId, without id
//        if (bookDto.getId() == null) {
//            bookId++;
//            bookStorage.addBookToStorage(bookId, bookDto);
//            log.info("Book {} added to storage with id: {}", bookDto, bookId);
//            bookDto.setId(bookId);
//        }
//        return bookDto;
//    }
//
//    @Override
//    public BookDto updateBook(BookDto bookDto) {     // userId is present in bookDto
//        bookId++;
//        bookStorage.updateBookInStorage(bookId, bookDto);
//        bookDto.setId(bookId);
//        log.info("Book {} with id: {} updated in storage.", bookDto, bookId);
//        return bookDto;
//    }
//
//    @Override
//    public BookDto getBookById(Long id) {
//        BookDto bookDto = bookStorage.getBookFromStorageByBookId(id);
//        log.info("Book {} got from storage with id: {}", bookId, bookDto);
//        return bookDto;
//    }
//
//    @Override
//    public void deleteBookById(Long id) {
//        log.info("Book with id: {} deleted", bookStorage.getBookFromStorageByBookId(id));
//        bookStorage.deleteBookFromStorage(id);
//    }
//
//    @Override
//    public List<Long> getBooksListById(Long id) {
//        log.info("deleteUserById: deleting user with id: {}", id);
//       return bookStorage.getBooksIdListFromStorageByUserID(id);
//
//    }
//
//
//}
