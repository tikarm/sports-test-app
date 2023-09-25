package com.example.testapp.api.exception

class ApiException(message: String, e: Exception? = null) : Exception(message, e)