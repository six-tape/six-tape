package cl.sixtape.service

class ValidationException(message: String) : IllegalArgumentException(message)

class NotFoundException(message: String) : RuntimeException(message)

class ConflictException(message: String) : RuntimeException(message)