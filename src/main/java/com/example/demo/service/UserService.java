package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;


    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findAll(){
        return userRepository.findAll();
    }

    public User saveUser(User user){
        return userRepository.save(user);
    }

    //    Удаление пользователя
    public void deleteById(int id){
        userRepository.deleteById(id);
    }

    //    Обновление информации о пользователе
    public User getOne(int id) {
        return userRepository.getOne(id);
    }

    public User updateById(User user) {
        return userRepository.updateById(user);
    }
}
