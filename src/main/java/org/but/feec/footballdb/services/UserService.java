package org.but.feec.footballdb.services;

import org.but.feec.footballdb.data.UserRepository;
import org.but.feec.footballdb.api.UserBasicView;
import org.but.feec.footballdb.api.UserDetailView;
import at.favre.lib.crypto.bcrypt.BCrypt;

import java.util.List;

public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository)
    {
        this.userRepository = userRepository;
    }

    public UserDetailView getPersonDetailView(Long id) {
        return userRepository.findPersonDetailedView(id);
    }

    public List<UserBasicView> getPersonsBasicView() {
        return userRepository.getPersonsBasicView();
    }

    private char[] hashPassword(char[] password) {
        return BCrypt.withDefaults().hashToChar(12, password);
    }
}