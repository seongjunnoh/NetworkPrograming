package org.example.IceBreaking.repository.user;

import org.example.IceBreaking.domain.User;
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
    public User findById(String userId) {
        return userMap.get(userId);
    }

    @Override
    public List<User> findAll() {
        return new ArrayList<>(userMap.values());
    }

    @Override
    public User changeUserInfo(User user) {
        if (userMap.get(user.getUserId()) != null) {
            User beforeChangeUser = userMap.get(user.getUserId());
            user.setIdForEdit(beforeChangeUser.getId());        // id값 set

            userMap.put(user.getUserId(), user);

            return userMap.get(user.getUserId());
        }

        return null;
    }


}
