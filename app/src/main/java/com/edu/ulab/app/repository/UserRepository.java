package com.edu.ulab.app.repository;

import com.edu.ulab.app.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;


public interface UserRepository extends CrudRepository<UserEntity, Long> {


}
