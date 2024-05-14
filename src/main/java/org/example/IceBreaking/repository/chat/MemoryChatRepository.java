package org.example.IceBreaking.repository.chat;

import org.example.IceBreaking.domain.Chat;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class MemoryChatRepository implements ChatRepository {

    private final Map<Integer, ArrayList<Chat>> chatMap = new HashMap<>();

    @Override
    public void saveChat(int teamId, Chat chat) {
        chatMap.computeIfAbsent(teamId, k -> new ArrayList<>());        // 신설 채팅방일 경우
        chatMap.get(teamId).add(chat);
    }

    @Override
    public List<Chat> findChatList(int teamId) {
        if (chatMap.get(teamId) == null) {
            ArrayList<Chat> newChats = new ArrayList<>();
            chatMap.put(teamId, newChats);
        }
        return chatMap.get(teamId);
    }
}
