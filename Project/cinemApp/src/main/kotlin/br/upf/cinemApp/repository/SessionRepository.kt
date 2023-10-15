package br.upf.cinemApp.repository

import br.upf.cinemApp.model.Sessions
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface SessionRepository: JpaRepository<Sessions, Long> {
    fun findByFilmname(nomeSessao: String, paginacao: Pageable): Page<Sessions>
}