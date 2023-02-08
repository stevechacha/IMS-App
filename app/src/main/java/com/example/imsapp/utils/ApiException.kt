package com.example.imsapp.utils

class ApiException(
    val statusCode: Int = 0, val statusMessage: String
) : Throwable(statusMessage)