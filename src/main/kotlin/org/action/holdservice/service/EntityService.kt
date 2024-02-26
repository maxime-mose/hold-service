package org.action.holdservice.service

import org.action.holdservice.model.EntityModel
import org.action.holdservice.model.HoldModel
import org.action.holdservice.repository.EntityRepository
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Service
import java.util.*
import java.util.concurrent.TimeUnit.SECONDS

@Service
class EntityService(
    private val entityRepository: EntityRepository,
    private val redisTemplate: RedisTemplate<String, String>
) {
    fun hold(model: HoldModel): EntityModel = entityRepository
        .findAll()
        .shuffled()
        .first { redisTemplate.opsForValue().setIfAbsent(it.id.toString(), "", model.timeInSeconds, SECONDS) ?: false }

    fun release(ids: List<UUID>) = redisTemplate.delete(ids.map { it.toString() })

    fun releaseAll() = redisTemplate.execute { it.serverCommands().flushDb() }

    fun findAll(): List<EntityModel> = entityRepository.findAll()

    fun save(entity: EntityModel) = entityRepository.save(entity)

    fun delete(id: UUID) = entityRepository.deleteById(id)
}
