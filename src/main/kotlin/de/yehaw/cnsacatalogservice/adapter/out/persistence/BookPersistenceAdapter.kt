package de.yehaw.cnsacatalogservice.adapter.out.persistence

import de.yehaw.cnsacatalogservice.application.domain.model.Book
import de.yehaw.cnsacatalogservice.application.domain.service.BookAlreadyExistsException
import de.yehaw.cnsacatalogservice.application.domain.service.BookNotFoundException
import de.yehaw.cnsacatalogservice.application.port.out.BookPort
import org.springframework.stereotype.Component

@Component
class BookPersistenceAdapter(
    private val bookRepository: BookRepository
) : BookPort {
    override fun loadAll(): Iterable<Book> {
        return bookRepository.findAll().map { it.toBook() }
    }

    override fun existsByIsbn(isbn: String): Boolean {
        return bookRepository.existsByIsbn(isbn)
    }

    override fun load(isbn: String): Book {
        return bookRepository.findByIsbn(isbn)?.toBook() ?: throw BookNotFoundException(isbn)
    }

    override fun addBook(book: Book): Book {
        if (bookRepository.existsByIsbn(book.isbn)) {
            throw BookAlreadyExistsException(book.isbn)
        } else {
            return bookRepository.save(book.toBookEntity()).toBook()
        }
    }

    override fun editBook(isbn: String, book: Book): Book {
        return bookRepository.findByIsbn(isbn)?.also {
                bookRepository.save(
                    it.copy(
                        title = book.title,
                        author = book.author,
                        price = book.price,
                        publisher = book.publisher
                    )
                )
            }?.toBook() ?: addBook(book)
    }

    override fun removeBook(isbn: String) {
        bookRepository.deleteByIsbn(isbn)
    }

}

fun BookEntity.toBook() = Book(isbn, title, author, price, publisher)
fun Book.toBookEntity() = BookEntity(isbn = isbn, title = title, author = author, price = price, publisher = publisher)
