package de.yehaw.cnsacatalogservice

import de.yehaw.cnsacatalogservice.adapter.out.persistence.BookEntity
import de.yehaw.cnsacatalogservice.adapter.out.persistence.BookRepository
import de.yehaw.cnsacatalogservice.config.DataConfig
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.context.annotation.Import
import org.springframework.data.jdbc.core.JdbcAggregateTemplate
import org.springframework.test.context.ActiveProfiles

@DataJdbcTest
@Import(DataConfig::class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("integration")
class BookRepositoryJdbcTests(
    @Autowired private val bookRepository: BookRepository,
    @Autowired private val jdbcAggregateTemplate: JdbcAggregateTemplate,
) {

    @Test
    fun findBookByIsbnWhenExisting() {
        val book =
            BookEntity(isbn = "1234567890", title = "Title", author = "Author", price = 12.9, publisher = "Publisher")
        jdbcAggregateTemplate.insert(book)
        val actualBook = bookRepository.findByIsbn(book.isbn)
        assertThat(actualBook).isNotNull
        assertThat(actualBook?.isbn).isEqualTo(book.isbn)
    }

}
