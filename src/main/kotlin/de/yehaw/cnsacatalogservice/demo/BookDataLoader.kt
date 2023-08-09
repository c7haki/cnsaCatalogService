package de.yehaw.cnsacatalogservice.demo

import de.yehaw.cnsacatalogservice.adapter.out.persistence.BookEntity
import de.yehaw.cnsacatalogservice.adapter.out.persistence.BookRepository
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component

@Component
@ConditionalOnProperty(prefix = "cnsa", name = ["testdata.enabled"], havingValue = "true")
class BookDataLoader(
    private val bookRepository: BookRepository,
) {

    @EventListener(ApplicationReadyEvent::class)
    fun loadBookTestData() {
        bookRepository.deleteAll()
        bookRepository.save(
            BookEntity(
                isbn = "1234567890",
                title = "Spring in Action",
                author = "Craig Walls",
                price = 9.0
            )
        )
        bookRepository.save(
            BookEntity(
                isbn = "1234567891",
                title = "Spring Security in Action",
                author = "Laurentiu Spilca",
                price = 9.0
            )
        )
        bookRepository.save(
            BookEntity(
                isbn = "1234567892",
                title = "Cloud Native Spring in Action",
                author = "Thomas Vitale",
                price = 9.0
            )
        )
    }

}
