package org.example.IceBreaking.repository.user;

import org.example.IceBreaking.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    void save(User user);

    Optional<User> findById(String userId);

    List<User> findAll();

    void changeUserInfo(User user);

}
