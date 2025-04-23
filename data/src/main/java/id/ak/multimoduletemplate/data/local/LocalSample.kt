package id.ak.multimoduletemplate.data.local

import id.ak.multimoduletemplate.data.model.ModelSample
import javax.inject.Inject

class LocalSample @Inject constructor() {
    suspend fun getModel() = ModelSample(1)
}