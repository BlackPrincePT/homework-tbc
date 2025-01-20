package com.perullheim.homework.service

import com.perullheim.homework.model.Chat
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.adapter
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

object ContactsService {

    private val moshi = Moshi.Builder()
        .add(ContactsAdapter())
        .add(KotlinJsonAdapterFactory())
        .build()

    val adapter: JsonAdapter<List<Chat>> =
        moshi.adapter(Types.newParameterizedType(List::class.java, Chat::class.java))

}
