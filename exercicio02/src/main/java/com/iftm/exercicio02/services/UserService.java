package com.iftm.exercicio02.services;

import com.iftm.exercicio02.models.Email;
import com.iftm.exercicio02.models.User;
import com.iftm.exercicio02.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public List<User> findAll() {
        return repository.findAll();
    }

    public Optional<User> findById(Long id) {
        return repository.findById(id);
    }

    public User save(User user) {
        if(verifyUser(user)) {
            return repository.save(user);
        }
        return null;
    }

    public User update(User user) {
        var dbUser = repository.findById(user.getId());
        if(dbUser.isPresent() && verifyUser(user))
        {
            return repository.save(user);
        }
        return null;
    }

    public String delete(Long id) {
        var dbUser = repository.findById(id);
        if(dbUser.isPresent()) {
            repository.deleteById(id);
            return "User with id " + id + " has been deleted!";
        }else
            return "ID " + id + " not found!";
    }

    private boolean verifyUser(User user) {
        if( !user.getFirstName().isBlank() && !user.getFirstName().isEmpty() &&
            !user.getLastName().isBlank() && !user.getLastName().isEmpty()) {
            return true;
        }
        return false;
    }
}
