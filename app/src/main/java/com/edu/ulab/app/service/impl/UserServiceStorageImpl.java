//package com.edu.ulab.app.service.impl;
//
//import com.edu.ulab.app.dto.UserDto;
//import com.edu.ulab.app.exception.NotFoundException;
//import com.edu.ulab.app.service.UserService;
//import com.edu.ulab.app.storage.UserStorage;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Service;
//
//
//@Slf4j
//@Service
//public class UserServiceStorageImpl implements UserService {
//    private static long userId;
//    final UserStorage userStorage;
//
//    public UserServiceStorageImpl(UserStorage userStorage) {
//        this.userStorage = userStorage;
//    }
//
//    // todo сгенерировать идентификатор
//    //  создать пользователя
//    //  вернуть сохраненного пользователя со всеми необходимыми полями id
//
//    @Override
//    public UserDto createUser(UserDto userDto) {    //userDto without userId
//        userStorage.addUserToStorage(userDto, userId);
//        userDto.setId(userId);
//        userId++;
//        log.info("User created: {}", userDto);
//        return userDto;     //userDto with userId
//    }
//
//    @Override
//    public UserDto updateUser(UserDto userDto, Long userId) {
//        UserDto initialUser = userStorage.getUserFromStorage(userId);
//        UserDto updatedUser = userStorage.updateUserInStorage(userId, userDto);
//        log.info("User updated from: {} to {}", initialUser, updatedUser);
//        return updatedUser;
//    }
//
//    @Override
//    public UserDto getUserById(Long userId) {
//        if(userStorage.getUserFromStorage(userId) != null){
//            UserDto foundUser = userStorage.getUserFromStorage(userId);
//            log.info("User found: {}", foundUser);
//            return foundUser;
//        }else throw new NotFoundException("No user with id = " + userId + " is  found!");
//
//    }
//
//    @Override
//    public void deleteUserById(Long id) {
//        if(userStorage.getUserFromStorage(userId) != null) {
//            UserDto deletedUser = userStorage.getUserFromStorage(userId);
//            userStorage.deleteUserFromStorage(id);
//            log.info("User deleted: {}", deletedUser);
//        }else throw new NotFoundException("No user with id = " + userId + " is  found!");
//    }
//}
