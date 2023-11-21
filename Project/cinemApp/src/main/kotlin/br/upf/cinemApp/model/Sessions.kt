package br.upf.cinemApp.model

import br.upf.cinemApp.dtos.TicketResponseDTO
import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*
import java.time.LocalDate
import java.time.LocalDateTime

@Entity
data class Sessions(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long? = null,
        val filmname: String,
        val data: LocalDate,
        val description: String,
        @Enumerated(value = EnumType.STRING)
        val status: StatusEvento,
        @OneToMany(mappedBy = "sessao_id")
        @JsonIgnore
        val inscritos: List<Tickets> = listOf()
)
