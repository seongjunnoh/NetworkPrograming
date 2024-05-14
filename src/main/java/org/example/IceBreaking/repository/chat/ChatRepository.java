package org.example.IceBreaking.repository.chat;

import org.example.IceBreaking.domain.Chat;

import java.util.List;

public interface ChatRepository {

    void saveChat(int teamId, Chat chat);
    List<Chat> findChatList(int teamId);
}
