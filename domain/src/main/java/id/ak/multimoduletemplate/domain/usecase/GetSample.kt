package id.ak.multimoduletemplate.domain.usecase

import id.ak.multimoduletemplate.domain.entity.EntitySample
import id.ak.multimoduletemplate.domain.repository.DomainRepositorySample
import javax.inject.Inject

class GetSample @Inject constructor(private val repositorySample: DomainRepositorySample) {
    suspend operator fun invoke(): EntitySample {
        return repositorySample.getModel(false)
    }
}