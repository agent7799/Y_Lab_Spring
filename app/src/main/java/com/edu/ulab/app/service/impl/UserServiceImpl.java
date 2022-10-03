//package com.edu.ulab.app.service.impl;
//
//import com.edu.ulab.app.dto.UserDto;
//import com.edu.ulab.app.entity.UserEntity;
//import com.edu.ulab.app.exception.NotFoundException;
//import com.edu.ulab.app.mapper.UserMapper;
//import com.edu.ulab.app.repository.UserRepository;
//import com.edu.ulab.app.service.UserService;
//import lombok.NonNull;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Service;
//
//import java.util.Optional;
//
//@Slf4j
//@Service
//public class UserServiceImpl implements UserService {
//
//    private final UserRepository userRepository;
//
//    private final UserMapper userMapper;
//
//    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
//        this.userRepository = userRepository;
//        this.userMapper = userMapper;
//    }
//
//
//    @Override
//    public UserDto createUser(UserDto userDto) {
//        UserEntity userEntity = userMapper.userDtoToUserEntity(userDto);
//        log.info("Mapped userEntity: {}", userEntity);
//        UserEntity savedUser = userRepository.save(userEntity);
//        log.info("Saved userEntity: {}", savedUser);
//        return userMapper.userEntityToUserDto(savedUser);
//    }
//
//    @Override
//    public UserDto updateUser(UserDto userDto, Long userId) {
//        UserEntity newUser = userMapper.userDtoToUserEntity(userDto);
//
//        newUser.setId(userId);
//        userRepository.save(newUser);
//        log.info("User {} updated", newUser);
//        return userMapper.userEntityToUserDto(newUser);
//    }
//
//    @Override
//    public UserDto getUserById(Long userId) {
//        if(userRepository.findById(userId).isPresent()){
//            Optional<UserEntity> userEntity = Optional.of(userRepository.findById(userId).orElseThrow());
//            log.info("Mapped userEntity: {}", userEntity.get());
//            return userMapper.userEntityToUserDto(userEntity.orElseThrow());
//        }else throw new NotFoundException("No user with id = " + userId + " is found!");
//    }
//
//    @Override
//    public void deleteUserById(Long id) {
//        if(userRepository.findById(id).isPresent()){
//            Optional<UserEntity> userEntity = userRepository.findById(id);
//            log.info("Deleting User {} with id: {} ", userEntity, id);
//            userRepository.delete(userEntity.orElseThrow());
//            log.info("User with id: {} is {}", id, userRepository.findById(id));
//        }else throw new NotFoundException("No user with id = " + id + " is found!");
//
//    }
//}
