package com.perullheim.homework

fun String.validateForWord() = this.none { it.digitToIntOrNull() != null }