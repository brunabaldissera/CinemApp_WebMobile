package br.upf.cinemApp.model

import com.fasterxml.jackson.annotation.JsonBackReference
import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*
import java.time.LocalDateTime
import kotlin.jvm.Transient

@Entity
data class Tickets(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    @ManyToOne
    @JoinColumn(name = "sessao_id", referencedColumnName = "id")
    val sessao_id: Sessions,
    @ManyToOne
    @JoinColumn(name = "usuario_id", referencedColumnName = "id")
    val usuario_id: User,
    val data: LocalDateTime = LocalDateTime.now()
)