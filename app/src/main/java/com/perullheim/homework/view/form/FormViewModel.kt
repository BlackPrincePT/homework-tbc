package com.perullheim.homework.view.form

import androidx.lifecycle.ViewModel
import com.perullheim.homework.model.Field
import kotlinx.serialization.json.Json

class FormViewModel : ViewModel() {

    private val jsonString = """[
        [
            {"field_id":1,"hint":"UserName","field_type":"input","keyboard":"text","required":false,"is_active":true,"icon":"https://jemala.png"},
            {"field_id":2,"hint":"Email","field_type":"input","required":true,"keyboard":"text","is_active":true,"icon":"https://jemala.png"},
            {"field_id":3,"hint":"phone","field_type":"input","required":true,"keyboard":"number","is_active":true,"icon":"https://jemala.png"}
        ],
        [
            {"field_id":4,"hint":"FullName","field_type":"input","keyboard":"text","required":true,"is_active":true,"icon":"https://jemala.png"},
            {"field_id":14,"hint":"Jemali","field_type":"input","keyboard":"text","required":false,"is_active":true,"icon":"https://jemala.png"},
            {"field_id":89,"hint":"Birthday","field_type":"chooser","required":false,"is_active":true,"icon":"https://jemala.png"},
            {"field_id":898,"hint":"Gender","field_type":"chooser","required":false,"is_active":true,"icon":"https://jemala.png"}
        ]
    ]"""

    val fields: MutableList<ArrayList<Field>> = Json.decodeFromString(jsonString)
    val savedData = mutableMapOf<Int, String>()
}