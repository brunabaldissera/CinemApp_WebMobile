package br.upf.cinemApp.controller

import br.upf.cinemApp.dtos.UserDTO
import br.upf.cinemApp.dtos.UserResponseDTO
import br.upf.cinemApp.service.UserService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.*
import org.springframework.web.util.UriComponentsBuilder

@RestController
@CrossOrigin(origins = ["http://localhost:3000"])
@RequestMapping("/usuarios")
class UserController(val service: UserService) {
    @GetMapping
    fun listar(): List<UserResponseDTO> {
        return service.listar()
    }
    @GetMapping("/{id}")
    fun buscarPorId(@PathVariable id: Long): UserResponseDTO {
        return service.searchForId(id)
    }
    @PostMapping
    @Transactional
    fun cadastrar(@RequestBody @Valid dto: UserDTO,
                  uriBuilder: UriComponentsBuilder
    ): ResponseEntity<UserResponseDTO> {
        val userResponse = service.register(dto)
        val uri = uriBuilder.path("/users/${userResponse.id}")
            .build().toUri()
        return ResponseEntity.created(uri).body(userResponse)
    }
    @PutMapping("/{id}")
    @Transactional
    fun atualizar(@PathVariable id: Long,
                  @RequestBody @Valid dto: UserDTO
    ): UserResponseDTO {
        return service.update(id, dto)
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Transactional
    fun deletar(@PathVariable id: Long) {
        service.delete(id)
    }

}