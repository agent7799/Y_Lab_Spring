package com.edu.ulab.app.storage;

import com.edu.ulab.app.dto.UserDto;
import com.edu.ulab.app.exception.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class UserStorage {
    //todo создать хранилище в котором будут содержаться данные
    // сделать абстракции через которые можно будет производить операции с хранилищем
    // продумать логику поиска и сохранения
    // продумать возможные ошибки
    // учесть, что при сохранеии юзера или книги, должен генерироваться идентификатор
    // продумать что у узера может бытмного книг и нужно создать эту связь
    // так же учесть, что методы хранилища принимают друго тип данных - учесть это в абстракции

    private static UserStorage instance;
    private UserStorage(){}
    public static UserStorage getInstance(){
        if (instance == null){
            instance =  new UserStorage();
        }
        return instance;
    }
    private static Map<Long, UserDto> usersList = new HashMap<>();

    public void addUserToStorage(UserDto userDto, long userId){
            usersList.put(userId, userDto);
    }

    public UserDto getUserFromStorage(Long userId){
            return usersList.get(userId);
    }

    public static void deleteUserFromStorage(Long userId){
            usersList.remove(userId);
    }

    public UserDto updateUserInStorage(long userId, UserDto userDto){
            UserDto initialUser = usersList.get(userId);
            userDto.setId(userId);
            usersList.put(userId, userDto);
            UserDto updatedUser = usersList.get(userId);
        log.info("User {} updated to {}", initialUser, updatedUser);
            return updatedUser;
    }


}
