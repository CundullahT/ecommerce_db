package com.ecommerce_db.services;

import com.ecommerce_db.model.User;
import com.ecommerce_db.repository.UserRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User create(User user) throws Exception {

        Optional<User> foundedUser = userRepository.findById(user.getId());
        if (foundedUser.isPresent()) throw new Exception("This User Already Exists.");

        return userRepository.save(user);

    }

    public void update(User user) throws Exception {

        User foundedUser = userRepository.findById(user.getId()).orElseThrow(() -> new Exception("There Is No Such User."));

        user.setId(foundedUser.getId());
        userRepository.save(user);

    }

    public List<User> readAll(){
        return userRepository.findAll(Sort.by("username"));
    }

    public User readById(Integer id){
        return userRepository.findById(id).orElse(null);
    }

    public User readByUsername(String username){
        return userRepository.findByUsername(username).orElse(null);
    }

    public User readByEmail(String email){
        return userRepository.findByEmail(email).orElse(null);
    }

//    public void deleteById(Integer id) throws Exception {
//
//        User foundedUser = userRepository.findById(id).orElseThrow(() -> new Exception("There Is No Such User"));
//
//        foundedUser.setIsDeleted(true);
//        userRepository.save(foundedUser);
//
//    }

}
