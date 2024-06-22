import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.model.request.TestRequest
import com.example.data.model.response.TestResponse
import com.example.sisvita_cus1.domain.TestUseCase
import kotlinx.coroutines.launch

class EstudTestViewModel : ViewModel() {
    private val testUseCase = TestUseCase()

    private val _testResponse = MutableLiveData<TestResponse>()
    val testResponse: LiveData<TestResponse> get() = _testResponse

    fun obtenerTest(testRequest: TestRequest) {
        viewModelScope.launch {
            try {
                val response = testUseCase.getTest(testRequest)
                _testResponse.value = response
            } catch (e: Exception) {
                // Manejar el error
            }
        }
    }
}
