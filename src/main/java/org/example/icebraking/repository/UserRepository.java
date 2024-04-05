package org.example.icebraking.repository;

import org.example.icebraking.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    void save(User user);

    Optional<User> findById(String userId);

    List<User> findAll();

    void changeUserInfo(User user);

}
