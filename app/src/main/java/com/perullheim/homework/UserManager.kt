package com.perullheim.homework

object UserManager {
    private val users = mutableListOf(
        User(
            id = 1,
            firstName = "გრიშა",
            lastName = "ონიანი",
            birthday = "1724647601641",
            address = "სტალინის სახლმუზეუმი",
            email = "grisha@mail.ru"
        ),
        User(
            id = 2,
            firstName = "Jemal",
            lastName = "Kakauridze",
            birthday = "1714647601641",
            address = "თბილისი, ლილოს მიტოვებული ქარხანა",
            email = "jemal@gmail.com"
        ),
        User(
            id = 2,
            firstName = "Omger",
            lastName = "Kakauridze",
            birthday = "1724647701641",
            address = "თბილისი, ასათიანი 18",
            email = "omger@gmail.com"
        ),
        User(
            id = 32,
            firstName = "ბორის",
            lastName = "გარუჩავა",
            birthday = "1714947701641",
            address = "თბილისი, იაშვილი 14",
            email = ""
        ),
        User(
            id = 34,
            firstName = "აბთო",
            lastName = "სიხარულიძე",
            birthday = "1711947701641",
            address = "ფოთი",
            email = "tebzi@gmail.com",
            desc = null
        )
    )

    fun searchForUser(data: String): User? {
        // ================= If Data is Int =================
        data.toLongOrNull()?.let { intData ->
            val userByID = users.firstOrNull { it.id == intData.toInt() }
            if (userByID != null)
                return userByID

            val userByBirthday = users.firstOrNull { it.birthday == intData.toString() }
            if (userByBirthday != null)
                return userByBirthday
        }

        // ================= If Data is String =================
        val userByFirstName = users.firstOrNull { it.firstName == data }
        if (userByFirstName != null)
            return userByFirstName

        val userByLastName = users.firstOrNull { it.lastName == data }
        if (userByLastName != null)
            return userByLastName

        val userByAddress = users.firstOrNull { it.lastName == data }
        if (userByAddress != null)
            return userByAddress

        val userByEmail = users.firstOrNull { it.lastName == data }
        if (userByEmail != null)
            return userByEmail

        return null
    }

    fun addNewUser(user: User) {
        users.add(user)
    }
}