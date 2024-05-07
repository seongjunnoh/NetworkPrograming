package org.example.IceBreaking.repository.chat;

import org.example.IceBreaking.domain.Chat;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class MemoryChatRepository implements ChatRepository {

    private final Map<Integer, Chat> chatMap = new HashMap<>();

    @Override
    public void saveByTeamId(int teamId, Chat chat) {
        chatMap.put(teamId, chat);
    }

    @Override
    public List<Chat> findByTeamId(int teamId) {
        List<Chat> chatList = new ArrayList<>();
        for (Map.Entry<Integer, Chat> entry : chatMap.entrySet()) {
            if (entry.getKey() == teamId) {
                chatList.add(entry.getValue());
            }
        }
        return chatList;
    }
}
