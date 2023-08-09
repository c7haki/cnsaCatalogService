package de.yehaw.cnsacatalogservice

import de.yehaw.cnsacatalogservice.application.domain.model.Book
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.test.web.reactive.server.expectBody

@SpringBootTest(
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
)
@ActiveProfiles("integration")
class CnsaCatalogServiceApplicationTests(
    @Autowired private val webTestClient: WebTestClient,
) {

    @Test
    fun whenPostRequestThenBookCreated() {
        webTestClient.post().uri("/api/v1/books").bodyValue(Book("1234567890", "title", "author", 9.9, "publisher"))
            .exchange().expectStatus().isCreated.expectBody<Book>().value {
                assertThat(it).isNotNull
                assertThat(it.isbn).isEqualTo("1234567890")
            }
    }

}
