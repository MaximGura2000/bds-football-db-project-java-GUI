package org.but.feec.footballdb.services;

import org.but.feec.footballdb.data.PersonRepository;

public class AuthService {

    private PersonRepository personRepository;

    public AuthService(PersonRepository personRepository)
    {
        this.personRepository = personRepository;
    }
}
