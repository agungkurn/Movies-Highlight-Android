package id.ak.multimoduletemplate.data.remote

import id.ak.multimoduletemplate.data.model.ModelSample
import javax.inject.Inject

class RemoteSample @Inject constructor() {
    suspend fun getModel() = ModelSample(1)
}