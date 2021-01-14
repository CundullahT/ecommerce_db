package com.ecommerce_db.services;

import com.ecommerce_db.enums.UserStatus;
import com.ecommerce_db.model.User;
import com.ecommerce_db.repository.UserRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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

//        if(!foundedUser.getEmail().equals(user.getEmail())){
//            //TODO what if user change email
//        }

        user.setId(foundedUser.getId());
        userRepository.save(user);

    }

    public List<User> readAll(){
        return userRepository.findAll(Sort.by("username"));
    }

    public User readById(Integer id) throws Exception {
        return userRepository.findById(id).orElseThrow(() -> new Exception("There Is No Such User"));
    }

    public User readByUsername(String username){
        return userRepository.findByUsername(username).orElse(null);
    }

    public User readByEmail(String email){
        return userRepository.findByEmail(email).orElse(null);
    }

    public List<User> readByStatus(UserStatus status){
        return userRepository.findByStatus(status);
    }

    @Transactional
    public void deActivateAccount(Integer id) throws Exception {
        readById(id).setStatus(UserStatus.SUSPENDED);
        userRepository.save(readById(id));
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
