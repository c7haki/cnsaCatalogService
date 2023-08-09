package de.yehaw.cnsacatalogservice.application.domain.service

import de.yehaw.cnsacatalogservice.application.domain.model.Book
import de.yehaw.cnsacatalogservice.application.port.out.BookPort
import org.springframework.stereotype.Service

@Service
class BookService(
    private val bookPort: BookPort,
) {

    fun getBooks(): Iterable<Book> =
        bookPort.loadAll()

    fun getBook(isbn: String): Book =
        bookPort.load(isbn)

    fun addBook(book: Book): Book =
        bookPort.addBook(book)

    fun removeBook(isbn: String) =
        bookPort.removeBook(isbn)

    fun editBook(isbn: String, book: Book): Book =
        bookPort.editBook(isbn, book)

}

class BookAlreadyExistsException(isbn: String) : RuntimeException("A book with ISBN $isbn already exists.")

class BookNotFoundException(isbn: String) : RuntimeException("A book with ISBN $isbn does not exist.")
