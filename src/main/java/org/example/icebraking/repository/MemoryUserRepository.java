package org.example.icebraking.repository;

import lombok.RequiredArgsConstructor;
import org.example.icebraking.domain.User;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class MemoryUserRepository implements UserRepository {

    private final Map<String, User> userMap = new HashMap<>();

    @Override
    public void save(User user) {
        userMap.put(user.getUserId(), user);
    }

    @Override
    public Optional<User> findById(String userId) {
        return Optional.ofNullable(userMap.get(userId));
    }

    @Override
    public List<User> findAll() {
        return new ArrayList<>(userMap.values());
    }

    @Override
    public void changeInfo(User user) {

    }
}
