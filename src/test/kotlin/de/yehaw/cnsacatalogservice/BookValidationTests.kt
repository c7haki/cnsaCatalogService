package de.yehaw.cnsacatalogservice

import de.yehaw.cnsacatalogservice.adapter.`in`.web.BookDto
import jakarta.validation.Validation
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BookValidationTests {

    private val validator = Validation.buildDefaultValidatorFactory().validator

    @Test
    fun whenAllFieldsCorrectThenValidationSucceeds() {
        assertThat(validator.validate(BookDto("1234567890", "title", "author", 9.9, "publisher"))).isEmpty()
    }

    @Test
    fun whenIsbnDefinedButIncorrectThenValidationFails() {
        val violations = validator.validate(BookDto("a234567890", "title", "author", 9.9, "publisher"))
        assertThat(violations).hasSize(1)
        assertThat(violations.first().message).isEqualTo("The book ISBN format is invalid.")
    }

}
