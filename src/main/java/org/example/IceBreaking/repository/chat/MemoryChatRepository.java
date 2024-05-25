package org.example.IceBreaking.repository.chat;

import org.example.IceBreaking.dto.ChatDto;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class MemoryChatRepository implements ChatRepository {

    private final Map<Integer, ArrayList<ChatDto>> chatMap = new HashMap<>();

    @Override
    public void saveChat(int teamId, ChatDto chat) {
        chatMap.computeIfAbsent(teamId, k -> new ArrayList<>());        // 신설 채팅방일 경우
        chatMap.get(teamId).add(chat);
    }

    @Override
    public List<ChatDto> findChatList(int teamId) {
        if (chatMap.get(teamId) == null) {
            ArrayList<ChatDto> newChats = new ArrayList<>();
            chatMap.put(teamId, newChats);
        }
        return chatMap.get(teamId);
    }
}
