package de.yehaw.cnsacatalogservice.adapter.out.persistence

import de.yehaw.cnsacatalogservice.application.domain.model.Book
import org.springframework.stereotype.Repository

interface BookRepository {
    fun findAll() : Iterable<Book>
    fun findByIsbn(isbn: String) : Book?
    fun existsByIsbn(isbn: String) : Boolean
    fun save(book: Book) : Book
    fun deleteByIsbn(isbn: String)
}

@Repository
class InMemoryBookRepository : BookRepository {

    private val books = mutableMapOf<String, Book>()

    override fun findAll(): Iterable<Book> {
        return books.values
    }

    override fun findByIsbn(isbn: String): Book? {
        return books[isbn]
    }

    override fun existsByIsbn(isbn: String): Boolean {
        return books.containsKey(isbn)
    }

    override fun save(book: Book): Book {
        books[book.isbn] = book
        return book
    }

    override fun deleteByIsbn(isbn: String) {
        books.remove(isbn)
    }

}
