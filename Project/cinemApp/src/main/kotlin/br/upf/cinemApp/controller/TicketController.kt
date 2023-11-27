package br.upf.cinemApp.controller

import br.upf.cinemApp.dtos.*
import br.upf.cinemApp.service.SessionService
import br.upf.cinemApp.service.TicketService
import jakarta.validation.Valid
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.*
import org.springframework.web.util.UriComponentsBuilder

@RestController
@CrossOrigin(origins = ["http://localhost:3000"])
@RequestMapping("/ticket")
class TicketController(val service: TicketService) {
    @GetMapping
    fun listar(): List<TicketResponseDTO> {
        return service.listar()
    }
    @GetMapping("/{id}")
    fun buscarPorId(@PathVariable id: Long): TicketResponseDTO {
        return service.searchForId(id)
    }
    @PostMapping
    @Transactional
    fun cadastrar(@RequestBody @Valid dto: TicketDTO,
                  uriBuilder: UriComponentsBuilder
    ): ResponseEntity<TicketResponseDTO> {
        val ticketResponse = service.register(dto)
        val uri = uriBuilder.path("/ticket/${ticketResponse.id}")
            .build().toUri()
        return ResponseEntity.created(uri).body(ticketResponse)
    }
    @PutMapping("/{id}")
    @Transactional
    fun atualizar(@PathVariable id: Long,
                  @RequestBody @Valid dto: TicketDTO
    ): TicketResponseDTO {
        return service.update(id, dto)
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Transactional
    fun deletar(@PathVariable id: Long) {
        service.delete(id)
    }
}