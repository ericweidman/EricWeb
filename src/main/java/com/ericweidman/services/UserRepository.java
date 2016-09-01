package com.ericweidman.services;

import com.ericweidman.entities.User;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by ericweidman on 8/26/16.
 */
public interface UserRepository extends CrudRepository <User, Integer>{
    User findByUserName(String userName);
}
