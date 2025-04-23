package id.ak.multimoduletemplate.domain.repository

import id.ak.multimoduletemplate.domain.entity.EntitySample

interface DomainRepositorySample {
    suspend fun getModel(newest: Boolean): EntitySample
}