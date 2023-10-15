package br.upf.cinemApp.dtos

import br.upf.cinemApp.model.StatusEvento
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import jdk.jfr.Description
import org.springframework.cglib.core.Local
import java.time.LocalDate
import java.time.LocalDateTime

data class SessionDTO(
        @field:NotEmpty (message = "Evento sempre deve ter um nome")
        val filmname: String,
        @field:NotNull (message = "Evento sempre deve ter uma data")
        val data: LocalDate,
        @field:NotNull (message = "Deve haver uma data de inicio")
        val initdata: LocalDateTime,
        @field:NotNull (message = "Deve haver uma data de fim")
        val enddata: LocalDateTime,
        val description: String,
        val status: StatusEvento
)
