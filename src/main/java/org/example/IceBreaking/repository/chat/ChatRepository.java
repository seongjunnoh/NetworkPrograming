package org.example.IceBreaking.repository.chat;

import org.example.IceBreaking.dto.ChatDto;

import java.util.List;

public interface ChatRepository {

    void saveChat(int teamId, ChatDto chat);
    List<ChatDto> findChatList(int teamId);
}
