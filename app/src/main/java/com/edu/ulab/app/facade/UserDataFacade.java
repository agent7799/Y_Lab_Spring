package com.edu.ulab.app.facade;

import com.edu.ulab.app.dto.BookDto;
import com.edu.ulab.app.dto.UserDto;
import com.edu.ulab.app.mapper.BookMapper;
import com.edu.ulab.app.mapper.UserMapper;
import com.edu.ulab.app.service.impl.*;
import com.edu.ulab.app.web.request.UserBookRequest;
import com.edu.ulab.app.web.response.UserBookResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Slf4j
@Component
public class UserDataFacade {
    //Implementation of local storage with Map
//    private final UserServiceStorageImpl userService;
//    private final BookServiceStorageImpl bookService;

    //implementation of storage with CrudRepository
//    private final UserServiceImpl userService;
//    private final BookServiceImpl bookService;

    //implementation of storage with JdbcTemplate
    private final UserServiceImplTemplate userService;
    private final BookServiceImplTemplate bookService;



    private final UserMapper userMapper;
    private final BookMapper bookMapper;


    //Implementation of local storage with Map
//    public UserDataFacade(UserServiceStorageImpl userService,
//                          BookServiceStorageImpl bookService,

    //implementation of storage with CrudRepository
//    public UserDataFacade(UserServiceImpl userService,
//                          BookServiceImpl bookService,

//implementation of storage with JdbcTemplate
    public UserDataFacade(UserServiceImplTemplate userService,
                          BookServiceImplTemplate bookService,

                          UserMapper userMapper,
                          BookMapper bookMapper)

    {
        this.userService = userService;
        this.bookService = bookService;
        this.userMapper = userMapper;
        this.bookMapper = bookMapper;
    }

    public UserBookResponse createUserWithBooks(UserBookRequest userBookRequest) {
        log.info("Got user book create request: {}", userBookRequest);
        UserDto userDto = userMapper.userRequestToUserDto(userBookRequest.getUserRequest());
        log.info("Mapped user request: {}", userDto);

        UserDto createdUser = userService.createUser(userDto);
        log.info("Created user: {}", createdUser);

        List<Long> bookIdList = userBookRequest.getBookRequests()
                .stream()
                .filter(Objects::nonNull)
                .map(bookMapper::bookRequestToBookDto)
                .peek(bookDto -> bookDto.setUserId(createdUser.getId()))
                .peek(mappedBookDto -> log.info("mapped book: {}", mappedBookDto))
                .map(bookService::createBook)
                .peek(createdBook -> log.info("Created book: {}", createdBook))
                .map(BookDto::getId)
                .toList();
        log.info("Collected book ids: {}", bookIdList);

        return UserBookResponse.builder()
                .userId(createdUser.getId())
                .booksIdList(bookIdList)
                .build();
    }

    public UserBookResponse updateUserWithBooks(UserBookRequest userBookRequest, Long userId) {
        log.info("Got user book update request: {}", userBookRequest);
        UserDto userDto = userMapper.userRequestToUserDto(userBookRequest.getUserRequest());
        log.info("Mapped user request: {}", userDto);
        UserDto updatedUser = userService.updateUser(userDto, userId);
        log.info("Updated user: {}", updatedUser);
        bookService.deleteBookById(updatedUser.getId());

        List<Long> bookIdList = userBookRequest.getBookRequests()
                .stream()
                .filter(Objects::nonNull)
                .map(bookMapper::bookRequestToBookDto)
                .peek(bookDto -> bookDto.setUserId(updatedUser.getId()))
                .peek(mappedBookDto -> log.info("mapped book: {}", mappedBookDto))
                .map(bookService::createBook)
                .peek(createdBook -> log.info("Created book: {}", createdBook))
                .map(BookDto::getId)
                .toList();
        log.info("Collected book ids: {}", bookIdList);

        return UserBookResponse.builder()
                .userId(updatedUser.getId())
                .booksIdList(bookIdList)
                .build();
    }

    public UserBookResponse getUserWithBooks(Long userId) {
        UserBookResponse response = UserBookResponse.builder()
                .userId(userId)
                .booksIdList(bookService.getBooksListById(userId))
                .build();
        log.info("getUserWithBooks:  response: {}", response);
        return response;

    }


    public void deleteUserWithBooks(Long userId) {
        //implementation with local Storage
//        UserStorage.deleteUserFromStorage(userId);
//        BookStorage.deleteBookFromStorageByUserId(userId);

        //implementation with repository/Jdbc
        userService.deleteUserById(userId);
        bookService.deleteBookById(userId);
    }
}
