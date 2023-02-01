package org.example.service;

import org.example.entity.UserEntity;
import org.example.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    public UserEntity getUser(Long id) {
        return userRepo.findById(id).get();
    }

    public void delete(Long id) {
        userRepo.delete(getUser(id));
    }

    public void update(Long idUser, UserEntity updateUser) {
        userRepo.save(updateUser);
    }


}
