package org.action.holdservice.contoller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import org.action.holdservice.model.EntityModel
import org.action.holdservice.model.HoldModel
import org.action.holdservice.service.EntityService
import org.springframework.http.ResponseEntity.ok
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/entity")
class EntityController(private val entityService: EntityService) {

    @PostMapping("/v1/hold")
    @Operation(summary = "Арендовать сущность на определённое время")
    fun hold(@RequestBody body: HoldModel) = ok(entityService.hold(body))

    @PostMapping("/v1/release")
    @Operation(summary = "Освободить арендованные сущности по идентификаторам")
    @ApiResponse(responseCode = "200", description = "Release successful")
    fun release(@RequestBody body: List<UUID>) = ok(entityService.release(body))

    @PostMapping("/v1/release_all")
    @Operation(summary = "Освободить все арендованные сущности")
    fun releaseAll() = ok(entityService.releaseAll())

    @GetMapping("/v1/get_all")
    @Operation(summary = "Список доступных сущностей для аренды")
    fun getAll() = ok(entityService.findAll())

    @PostMapping("/v1/save")
    @Operation(summary = "Добавить новую сущность в механизм аренды или обновить имеющуюся")
    fun save(@RequestBody entity: EntityModel) = ok(entityService.save(entity))

    @DeleteMapping("/v1/delete/{id}")
    @Operation(summary = "Удалить сущность из механизма аренды")
    fun delete(@PathVariable id: UUID) = ok(entityService.delete(id))
}
