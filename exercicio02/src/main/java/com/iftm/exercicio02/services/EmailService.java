package com.iftm.exercicio02.services;

import com.iftm.exercicio02.models.Email;
import com.iftm.exercicio02.models.User;
import com.iftm.exercicio02.repositories.EmailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmailService {

    @Autowired
    private EmailRepository repository;

    public List<Email> findAll() {
        return repository.findAll();
    }

    public Optional<Email> findById(Long id) {
        return repository.findById(id);
    }

    public Email save(Email email) {
        if(verifyEmail(email))
            return repository.save(email);
        return null;
    }

    public Email update(Email email) {
        var dbEmail = repository.findById(email.getId());
        if(dbEmail.isPresent() && verifyEmail(email))
            return repository.save(email);
        return null;
    }

    public String delete(Long id) {
        var dbEmail = repository.findById(id);
        if(dbEmail.isPresent()) {
            repository.deleteById(id);
            return "Email with id " + id + " has been deleted!";
        }
        return "ID " + id + " not found!";
    }

    private boolean verifyEmail(Email email) {
        if( !email.getFrom().isBlank() && !email.getFrom().isEmpty() &&
            !email.getTo().isBlank() && !email.getTo().isEmpty() &&
            !email.getSubject().isBlank() && !email.getSubject().isEmpty() &&
            !email.getBody().isBlank() && !email.getBody().isEmpty() ) {
            return true;
        }
        return false;
    }
}
