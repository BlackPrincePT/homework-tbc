package com.perullheim.homework.service

import com.perullheim.homework.model.Chat
import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson

class ContactsAdapter {
    @FromJson
    fun eventFromJson(chatJson: Chat.JSON): Chat {
        return Chat(
            id = chatJson.id,
            image = chatJson.image ?: "",
            owner = chatJson.owner,
            lastMessage = chatJson.lastMessage,
            lastActive = chatJson.lastActive,
            unreadMessages = chatJson.unreadMessages,
            isTyping = chatJson.isTyping,
            lastMessageType = Chat.Type.valueOf(chatJson.lastMessageType.uppercase())
        )
    }

    @ToJson
    fun eventToJson(chat: Chat): Chat.JSON {
        return Chat.JSON(
            id = chat.id,
            image = chat.image.ifEmpty { null },
            owner = chat.owner,
            lastMessage = chat.lastMessage,
            lastActive = chat.lastActive,
            unreadMessages = chat.unreadMessages,
            isTyping = chat.isTyping,
            lastMessageType = chat.lastMessageType.name.lowercase()
        )
    }
}