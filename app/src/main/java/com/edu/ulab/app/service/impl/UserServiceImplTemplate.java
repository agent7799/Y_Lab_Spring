//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.edu.ulab.app.service.impl;

import com.edu.ulab.app.dto.UserDto;
import com.edu.ulab.app.entity.UserEntity;
import com.edu.ulab.app.exception.NotFoundException;
import com.edu.ulab.app.mapper.UserMapper;
import com.edu.ulab.app.repository.UserRepository;
import com.edu.ulab.app.service.UserService;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImplTemplate implements UserService {
    private static final Logger log = LoggerFactory.getLogger(UserServiceImplTemplate.class);
    private final JdbcTemplate jdbcTemplate;
    private final UserMapper userMapper;
    UserRepository userRepository;

    public UserServiceImplTemplate(JdbcTemplate jdbcTemplate, UserMapper userMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.userMapper = userMapper;
    }

    public UserDto createUser(UserDto userDto) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update((connection) -> {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO PERSON(FULL_NAME, TITLE, AGE) VALUES (?,?,?)", new String[]{"id"});
            ps.setString(1, userDto.getFullName());
            ps.setString(2, userDto.getTitle());
            ps.setLong(3, (long) userDto.getAge());
            return ps;
        }, keyHolder);
        userDto.setId((Objects.requireNonNull(keyHolder.getKey())).longValue());
        log.info("UserServiceImplTemplate: {}", userDto);
        return userDto;
    }

    public UserDto updateUser(UserDto userDto, Long userId) {

        // UserEntity userEntity = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User with id " + userId + " is not found, can't update"));
        final String UPDATE_SQL = "UPDATE PERSON SET FULL_NAME=?, TITLE=?, AGE=?  WHERE ID=?";
        jdbcTemplate.update(UPDATE_SQL, userDto.getFullName(), userDto.getTitle(), userDto.getAge(), userId);
        UserDto updatedUserDto = getUserById(userId);
        return updatedUserDto;
    }

    public UserDto getUserById(Long userId) {
        isUserFound(userId);
        final String GET_SQL = "SELECT * FROM PERSON WHERE ID=?";
        return userMapper.userEntityToUserDto(jdbcTemplate.queryForObject(GET_SQL,
                new Object[]{userId}, new BeanPropertyRowMapper<>(UserEntity.class)));
    }

    public void isUserFound(Long id) {
        final String GET_SQL = "SELECT * FROM PERSON WHERE ID=?";
        List<UserEntity> list = jdbcTemplate.queryForList(GET_SQL, UserEntity.class, id);
        log.info("isUserFound: found: {}", list);
        if (list.isEmpty()) {
            throw new NotFoundException("No user with id = " + id + " is found!");
        }
    }

    public void deleteUserById(Long userId) {
        isUserFound(userId);
        String DELETE_SQL = "DELETE FROM PERSON WHERE ID=?";
        jdbcTemplate.update(DELETE_SQL, new Object[]{userId});
        log.info("deleteUserById: deleting user with id: {}", userId);
    }



}
