package org.but.feec.footballdb.services;

import org.but.feec.footballdb.data.PersonRepository;
import at.favre.lib.crypto.bcrypt.BCrypt;
import org.but.feec.footballdb.exceptions.SearchException;
import org.but.feec.footballdb.api.PersonAuthView;

public class AuthService {

    private PersonRepository personRepository;

    public AuthService(PersonRepository personRepository)
    {
        this.personRepository = personRepository;
    }

    private PersonAuthView findPersonByEmail(String email) {
        return personRepository.findPersonByEmail(email);
    }

    public boolean authenticate(String username, String password) {
        if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
            return false;
        }

        PersonAuthView personAuthView = findPersonByEmail(username);

        if (personAuthView == null) {
            throw new SearchException("Provided username is not found.");
        }

        //BCrypt.Result result = BCrypt.verifyer().verify(password.toCharArray(), personAuthView.getPassword());

        boolean compare = personAuthView.getPassword().equals(password);
        if (compare)
            return true;

        return false;

        //return result.verified;
    }
}
