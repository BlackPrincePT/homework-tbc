package com.perullheim.homework

fun Int.toGeorgian(): String {
    var result = ""

    if (this == 0)
        return "ნული"

    val hundred = GeoDict.base20[(this % 1000) / 100]
    val twenty = GeoDict.base20[((this % 1000) % 100) / 20]
    val one = GeoDict.base20[((this % 1000) % 100) % 20]

    if (hundred != null)
        result +=
            if (hundred == "ერთი") "ას"
            else if (hundred.last() == 'ა') hundred + "ას"
            else hundred.dropLast(n = 1) + "ას"

    if (twenty != null)
        result +=
            when (twenty) {
                "ორი" -> "ორმოც"
                "სამი" -> "სამოც"
                "ოთხი" -> "ოთხმოც"
                else -> "ოც"
            }

    if (one != null)
        result +=
            if (twenty == null) one
            else "და$one"
    else
        result += 'ი'

    return result
}

fun Int.toEnglish(): String {
    var result = ""

    if (this == 0)
        return "zero"

    val hundred = (this % 1000) / 100
    val ten = ((this % 1000) % 100) / 10
    val one = ((this % 1000) % 100) % 10

    if (hundred != 0)
        result += EngDict.base100[hundred] + " " + "hundred"

    if (!(ten == 0 && one == 0)) {
        if (hundred != 0)
            result += " "

        result += when (ten) {
            0 -> EngDict.base100[one]
            1 -> EngDict.base100[10 + one]
            else -> {
                EngDict.base100[ten * 10] + if (one != 0) "-" + EngDict.base100[one] else ""
            }
        }
    }

    return result
}