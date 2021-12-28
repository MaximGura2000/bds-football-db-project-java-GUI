package org.but.feec.footballdb.services;

import org.but.feec.footballdb.data.UserRepository;
import org.but.feec.footballdb.api.PersonBasicView;
import org.but.feec.footballdb.api.PersonDetailView;
import at.favre.lib.crypto.bcrypt.BCrypt;

import java.util.List;

public class PersonService {

    private UserRepository userRepository;

    public PersonService(UserRepository userRepository)
    {
        this.userRepository = userRepository;
    }

    public PersonDetailView getPersonDetailView(Long id) {
        return userRepository.findPersonDetailedView(id);
    }

    public List<PersonBasicView> getPersonsBasicView() {
        return userRepository.getPersonsBasicView();
    }

    private char[] hashPassword(char[] password) {
        return BCrypt.withDefaults().hashToChar(12, password);
    }
}