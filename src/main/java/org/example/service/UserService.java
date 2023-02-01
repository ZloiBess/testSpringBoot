package org.example.service;

import org.example.entity.UserEntity;
import org.example.exception.PageErrorException;
import org.example.exception.UserNotFoundException;
import org.example.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;


    public void createUser(UserEntity user) {
        userRepo.save(user);
    }

    public List<UserEntity> getAllUsers() {
        List<UserEntity> list = new ArrayList<>();
        userRepo.findAll().forEach(list::add);
        return list;
    }

    public UserEntity getUser(Long id) throws UserNotFoundException, PageErrorException {
        UserEntity user = null;
        try {
            user = userRepo.findById(id).get();
        }catch (NoSuchElementException e){
            throw new UserNotFoundException("");
        }catch (Exception e){
            throw new PageErrorException();
        }
        return user;
    }

    public void delete(Long id) throws UserNotFoundException, PageErrorException {
        userRepo.delete(getUser(id));
    }

    public void update(Long idUser, UserEntity updateUser) {
        userRepo.save(updateUser);
    }


}
