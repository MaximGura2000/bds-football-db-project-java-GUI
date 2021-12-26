package org.but.feec.footballdb.services;

import org.but.feec.footballdb.data.PersonRepository;
import at.favre.lib.crypto.bcrypt.BCrypt;

public class PersonService {

    private PersonRepository personRepository;

    public PersonService(PersonRepository personRepository)
    {
        this.personRepository = personRepository;
    }

    private char[] hashPassword(char[] password) {
        return BCrypt.withDefaults().hashToChar(12, password);
    }
}