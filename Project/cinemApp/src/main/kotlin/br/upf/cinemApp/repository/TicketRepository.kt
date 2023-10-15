package br.upf.cinemApp.repository

import br.upf.cinemApp.model.Tickets
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TicketRepository: JpaRepository<Tickets,Long> {
}