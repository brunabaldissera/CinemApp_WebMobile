package br.upf.cinemApp.controller

import br.upf.cinemApp.dtos.SessionDTO
import br.upf.cinemApp.dtos.SessionResponseDTO
import br.upf.cinemApp.service.SessionService
import jakarta.validation.Valid
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.util.UriComponentsBuilder

@RestController
@RequestMapping("/sessions")
class SessionController(val service: SessionService) {
    @GetMapping
    fun listar(
        @RequestParam(required = false) nomeSessao: String?,
        @PageableDefault(size = 10) paginacao: Pageable)
            : Page<SessionResponseDTO> {
        return service.listar(nomeSessao, paginacao)
    }

    @GetMapping("/{id}")
    fun searchForId(@PathVariable id: Long): SessionResponseDTO {
        return service.searchForId(id)
    }

    @PostMapping
    @Transactional
    fun cadastra(@RequestBody @Valid dto: SessionDTO,
                 uriBuilder: UriComponentsBuilder
    ): ResponseEntity<SessionResponseDTO> {
        val eventoResponse = service.register(dto)
        val uri = uriBuilder.path("/sessions/${eventoResponse.id}")
            .build().toUri()
        return ResponseEntity.created(uri).body(eventoResponse)
    }

    @PutMapping("/{id}")
    @Transactional
    fun update(@PathVariable id: Long,
                  @RequestBody @Valid dto: SessionDTO
    ): SessionResponseDTO {
        return service.update(id, dto)
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deletar(@PathVariable id: Long){
        service.deletar(id)
    }
}