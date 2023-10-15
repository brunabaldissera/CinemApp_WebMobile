package br.upf.cinemApp.controller

import br.upf.cinemApp.dtos.LoginDTO
import br.upf.cinemApp.dtos.LoginResponseDTO
import br.upf.cinemApp.dtos.UserDTO
import br.upf.cinemApp.model.User
import br.upf.cinemApp.service.TokenService
import br.upf.cinemApp.service.UserService
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("auth")
class AuthenticationController(
        val authenticationManager: AuthenticationManager,
        val service: UserService,
        val tokenService: TokenService) {
    @PostMapping("/login")
    fun login(@RequestBody data: LoginDTO): ResponseEntity<LoginResponseDTO>{
        val userPassword = UsernamePasswordAuthenticationToken(
                data.login, data.password)
        val auth = authenticationManager.authenticate(userPassword)
        val token = tokenService.generateToken(auth.principal as User)
        return ResponseEntity.ok(LoginResponseDTO(token))
    }
    @PostMapping("/register")
    fun login(@RequestBody data: UserDTO): ResponseEntity<String> {
        service.register(data)
        return ResponseEntity.ok().build()
    }
}