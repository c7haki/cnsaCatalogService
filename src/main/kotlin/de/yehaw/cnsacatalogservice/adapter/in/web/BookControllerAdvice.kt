package de.yehaw.cnsacatalogservice.adapter.`in`.web

import de.yehaw.cnsacatalogservice.application.domain.service.BookAlreadyExistsException
import de.yehaw.cnsacatalogservice.application.domain.service.BookNotFoundException
import org.springframework.http.HttpStatus
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class BookControllerAdvice {

    @ExceptionHandler(BookAlreadyExistsException::class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    fun bookAlreadyExistsHandler(e: BookAlreadyExistsException) = e.message

    @ExceptionHandler(BookNotFoundException::class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun bookNotFoundHandler(e: BookNotFoundException) = e.message

    @ExceptionHandler(MethodArgumentNotValidException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleMethodArgumentNotValidException(e: MethodArgumentNotValidException)
        = e.bindingResult.allErrors.associate { error -> Pair(error.objectName, error.defaultMessage ?: "") }

}
