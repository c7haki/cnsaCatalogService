package de.yehaw.cnsacatalogservice.adapter.out.persistence

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.annotation.Version
import org.springframework.data.jdbc.repository.query.Modifying
import org.springframework.data.jdbc.repository.query.Query
import org.springframework.data.relational.core.mapping.Table
import org.springframework.data.repository.CrudRepository
import org.springframework.transaction.annotation.Transactional
import java.time.Instant

interface BookRepository : CrudRepository<BookEntity, Long> {
    fun findByIsbn(isbn: String): BookEntity?
    fun existsByIsbn(isbn: String): Boolean

    @Modifying
    @Transactional
    @Query("delete from book where isbn = :isbn")
    fun deleteByIsbn(isbn: String)
}

@Table("book")
data class BookEntity(
    @Id val id: Long? = null,
    val isbn: String,
    val title: String,
    val author: String,
    val price: Double,
    @CreatedDate
    val createdDate: Instant? = null,
    @LastModifiedDate
    val lastModifiedDate: Instant ? = null,
    @Version
    val version: Int? = null,
)
