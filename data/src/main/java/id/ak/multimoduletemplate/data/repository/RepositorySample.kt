package id.ak.multimoduletemplate.data.repository

import id.ak.multimoduletemplate.data.local.LocalSample
import id.ak.multimoduletemplate.data.remote.RemoteSample
import id.ak.multimoduletemplate.domain.entity.EntitySample
import id.ak.multimoduletemplate.domain.repository.DomainRepositorySample
import javax.inject.Inject

class RepositorySample @Inject constructor(
    private val localSample: LocalSample,
    private val remoteSample: RemoteSample
): DomainRepositorySample {
    override suspend fun getModel(newest: Boolean): EntitySample {
        return (if (newest) remoteSample.getModel() else localSample.getModel()).toDomain()
    }
}