package org.but.feec.footballdb.services;

import org.but.feec.footballdb.data.UserRepository;
import org.but.feec.footballdb.exceptions.SearchException;
import org.but.feec.footballdb.api.UserAuthView;

public class AuthService {

    private UserRepository userRepository;

    public AuthService(UserRepository userRepository)
    {
        this.userRepository = userRepository;
    }

    private UserAuthView findPersonByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

    public boolean authenticate(String username, String password) {
        if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
            return false;
        }

        UserAuthView userAuthView = findPersonByEmail(username);

        if (userAuthView == null) {
            throw new SearchException("Provided username is not found.");
        }

        //BCrypt.Result result = BCrypt.verifyer().verify(password.toCharArray(), userAuthView.getPassword());

        boolean compare = userAuthView.getPassword().equals(password);
        if (compare)
            return true;

        return false;

        //return result.verified;
    }
}
