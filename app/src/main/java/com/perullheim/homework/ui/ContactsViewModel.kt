package com.perullheim.homework.ui

import androidx.lifecycle.ViewModel
import com.perullheim.homework.model.Chat
import com.perullheim.homework.service.ContactsService

class ContactsViewModel : ViewModel() {

    private val conversationsJson: String = "[\n" +
            "  {\n" +
            "    \"id\": 1,\n" +
            "    \"image\": \"https://www.alia.ge/wp-content/uploads/2022/09/grisha.jpg\",\n" +
            "    \"owner\": \"გრიშა ონიანი\",\n" +
            "    \"last_message\": \"თავის ტერიტორიას ბომბავდა\",\n" +
            "    \"last_active\": \"4:20 PM\",\n" +
            "    \"unread_messages\": 3,\n" +
            "    \"is_typing\": false,\n" +
            "    \"laste_message_type\": \"text\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"id\": 2,\n" +
            "    \"image\": null,\n" +
            "    \"owner\": \"ჯემალ კაკაურიძე\",\n" +
            "    \"last_message\": \"შემოგევლე\",\n" +
            "    \"last_active\": \"3:00 AM\",\n" +
            "    \"unread_messages\": 0,\n" +
            "    \"is_typing\": true,\n" +
            "    \"laste_message_type\": \"voice\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"id\": 3,\n" +
            "    \"image\": \"https://i.ytimg.com/vi/KYY0TBqTfQg/hqdefault.jpg\",\n" +
            "    \"owner\": \"გურამ ჯინორია\",\n" +
            "    \"last_message\": \"ცოცხალი ვარ მა რა ვარ შე.. როდის იყო კვტარი ტელეფონზე ლაპარაკობდა\",\n" +
            "    \"last_active\": \"1:00 \",\n" +
            "    \"unread_messages\": 0,\n" +
            "    \"is_typing\": false,\n" +
            "    \"laste_message_type\": \"file\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"id\": 4,\n" +
            "    \"image\": \"\",\n" +
            "    \"owner\": \"კაკო წენგუაშვილი\",\n" +
            "    \"last_message\": \"ადამიანი რო მოსაკლავად გაგიმეტებს თანაც ქალი ის დასანდობი არ არი\",\n" +
            "    \"last_active\": \"1:00 PM\",\n" +
            "    \"unread_messages\": 0,\n" +
            "    \"is_typing\": false,\n" +
            "    \"laste_message_type\": \"text\"\n" +
            "  }\n" +
            "]"

    val chats = ContactsService.adapter.fromJson(conversationsJson)

    fun search(owner: String) : List<Chat>? {
        return chats?.filter { it.owner.contains(owner) }
    }
}