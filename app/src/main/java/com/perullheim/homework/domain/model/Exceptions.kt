package com.perullheim.homework.domain.model

import java.io.IOException

class NetworkUnavailableException(message: String = "No network available") : IOException(message)