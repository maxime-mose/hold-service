package org.action.holdservice.model

import jakarta.persistence.Entity
import jakarta.persistence.Id
import java.util.*

@Entity
class EntityModel(@Id val id: UUID, val name: String)

data class HoldModel(val timeInSeconds: Long)
