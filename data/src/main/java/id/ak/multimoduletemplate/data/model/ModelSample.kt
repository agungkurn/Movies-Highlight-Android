package id.ak.multimoduletemplate.data.model

import id.ak.multimoduletemplate.domain.entity.EntitySample

data class ModelSample(val id: Int) {
    fun toDomain() = EntitySample(id)
}