package org.action.holdservice.repository

import org.action.holdservice.model.EntityModel
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface EntityRepository : JpaRepository<EntityModel, UUID>
