package de.yehaw.cnsacatalogservice.application.domain.service

import de.yehaw.cnsacatalogservice.adapter.out.persistence.BookRepository
import de.yehaw.cnsacatalogservice.application.domain.model.Book
import org.springframework.stereotype.Service

@Service
class BookService(
    private val bookRepository: BookRepository
) {

    fun getBooks(): Iterable<Book> = bookRepository.findAll()

    fun getBook(isbn: String): Book = bookRepository.findByIsbn(isbn) ?: throw BookNotFoundException(isbn)

    fun addBook(book: Book): Book {
        if (bookRepository.existsByIsbn(book.isbn)) {
            throw BookAlreadyExistsException(book.isbn)
        } else {
            return bookRepository.save(book)
        }
    }

    fun removeBook(isbn: String) {
        bookRepository.deleteByIsbn(isbn)
    }

    fun editBook(isbn: String, book: Book): Book {
        return bookRepository.findByIsbn(isbn)
            ?.also { bookRepository.save(it.copy(title = book.title, author = book.author, price = book.price)) }
            ?: addBook(book)
    }

}

class BookAlreadyExistsException(isbn: String) : RuntimeException("A book with ISBN $isbn already exists.")

class BookNotFoundException(isbn: String) : RuntimeException("A book with ISBN $isbn does not exist.")
