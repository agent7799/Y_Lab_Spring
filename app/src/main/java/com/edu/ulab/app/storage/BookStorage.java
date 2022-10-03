package com.edu.ulab.app.storage;

import com.edu.ulab.app.dto.BookDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import java.util.*;

@Slf4j
@Component
public class BookStorage {

    //todo создать хранилище в котором будут содержаться данные
    // сделать абстракции через которые можно будет производить операции с хранилищем
    // продумать логику поиска и сохранения
    // продумать возможные ошибки
    // учесть, что при сохранеии юзера или книги, должен генерироваться идентификатор
    // продумать что у узера может быть много книг и нужно создать эту связь
    // так же учесть, что методы хранилища принимают друго тип данных - учесть это в абстракции

    private static BookStorage instance;
    private BookStorage(){}
    public static BookStorage getInstance(){
        if (instance == null){
            instance =  new BookStorage();
        }
        return instance;
    }

    private static Map<Long, BookDto> booksList = new HashMap<>();


    public static List<Long> getBooksIdListFromStorageByUserID(Long requestedUserId){
        List<Long> foundBooksList = booksList.values().stream()
                .filter(Objects::nonNull)
                .filter(bookDto -> bookDto.getUserId().equals(requestedUserId))
                .map(bookDto -> bookDto.getId())
               .toList();
        return foundBooksList;
    }

    public BookDto getBookFromStorageByBookId(Long bookId){
        return booksList.get(bookId);
    }

    public void addBookToStorage(long bookId, BookDto bookDto){
        if(!booksList.containsValue(bookDto)){
            booksList.putIfAbsent(bookId, bookDto);
        }
    }

    public BookDto updateBookInStorage(long bookId, BookDto bookDto){
        booksList.put(bookId, bookDto);
        return booksList.get(bookId);
    }

    public  void deleteBookFromStorage(long bookId){
        booksList.entrySet().removeIf(entry -> entry.getValue().getId().equals(bookId));
    }

    public static void deleteBookFromStorageByUserId(long userId){
        booksList.entrySet().removeIf(entry -> entry.getValue().getUserId().equals(userId));
    }


}
