package id.ak.multimoduletemplate.home

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import id.ak.multimoduletemplate.domain.usecase.GetSample
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val getSampleUseCase: GetSample): ViewModel() {
}