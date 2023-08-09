package de.yehaw.cnsacatalogservice.application.port.out

import de.yehaw.cnsacatalogservice.application.domain.model.Book

interface BookPort {

    fun loadAll(): Iterable<Book>
    fun existsByIsbn(isbn: String): Boolean
    fun load(isbn: String): Book
    fun addBook(book: Book): Book
    fun editBook(isbn: String, book: Book): Book
    fun removeBook(isbn: String)

}
