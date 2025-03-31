package com.perullheim.homework

fun String.validateForEmail(): Boolean {
    val emailParts = this.split('@', '.')

    return (emailParts.count() == 3 && emailParts.none { it.isEmpty() } && this[emailParts.first().length] == '@')
}