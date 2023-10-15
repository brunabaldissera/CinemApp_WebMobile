package br.upf.cinemApp

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class CinemAppApplication

fun main(args: Array<String>) {
	runApplication<CinemAppApplication>(*args)
}
