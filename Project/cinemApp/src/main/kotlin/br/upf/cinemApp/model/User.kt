package br.upf.cinemApp.model

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonManagedReference
import jakarta.persistence.*

@Entity
data class User(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long? = null,
        val nome: String,
        val cidade: String,
        val telefone: String,
        val cpf: String,
        @OneToMany(mappedBy = "usuario_id")
        val inscricoes: List<Tickets> = listOf()
)
