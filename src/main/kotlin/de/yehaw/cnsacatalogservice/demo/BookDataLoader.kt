package de.yehaw.cnsacatalogservice.demo

import de.yehaw.cnsacatalogservice.adapter.out.persistence.BookEntity
import de.yehaw.cnsacatalogservice.adapter.out.persistence.BookRepository
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
@ConditionalOnProperty(prefix = "cnsa", name = ["testdata.enabled"], havingValue = "true")
class BookDataLoader(
    private val bookRepository: BookRepository,
) {

    @EventListener(ApplicationReadyEvent::class)
    // This should be enough for testing purposes.
    // Otherwise, consider using a control table instead, or using a shared redis lock,
    // or a kubernetes init container or spring job manager.
    @Transactional
    fun loadBookTestData() {
        bookRepository.deleteAll()
        bookRepository.save(
            BookEntity(
                isbn = "1234567890",
                title = "Spring in Action",
                author = "Craig Walls",
                price = 9.0,
                publisher = "Manning"
            )
        )
        bookRepository.save(
            BookEntity(
                isbn = "1234567891",
                title = "Spring Security in Action",
                author = "Laurentiu Spilca",
                price = 9.0,
                publisher = "Manning"
            )
        )
        bookRepository.save(
            BookEntity(
                isbn = "1234567892",
                title = "Cloud Native Spring in Action",
                author = "Thomas Vitale",
                price = 9.0,
                publisher = "Manning"
            )
        )
    }

}
