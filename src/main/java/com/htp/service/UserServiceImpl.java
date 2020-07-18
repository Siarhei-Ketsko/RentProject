package com.htp.service;

import com.htp.dao.UserRepository;
import com.htp.domain.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

//@Transactional(readOnly = true)
@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

//    @Override
//    public List<User> search(String searchParam) {
//        return userRepository.search(searchParam);
//    }

    @Override
    public Optional<User> findById(Long userId) {
        return userRepository.findById(userId);
    }

    @Override
    public User findOne(Long userId) {
        return userRepository.findById(userId).get();
    }

    @Override
    @Transactional
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    @Transactional
    public User update(User user) {
        return userRepository.save(user);
    }

    @Override
    public void hardDelete(Long userId) {
        userRepository.deleteById(userId);
    }


}
