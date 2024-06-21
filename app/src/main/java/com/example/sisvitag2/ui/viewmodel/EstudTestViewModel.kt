import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.model.TestResponse
import com.example.data.model.response.TestListResponse
import com.example.sisvita_cus1.data.model.Result
import com.example.sisvita_cus1.domain.TestUseCase
import kotlinx.coroutines.launch

class EstudTestViewModel : ViewModel() {
    private val testUseCase = TestUseCase()

    private val _tests = MutableLiveData<TestListResponse>()
    val tests: LiveData<TestListResponse> get() = _tests

    private val _testResult = MutableLiveData<Result<TestResponse>?>()
    val testResult: LiveData<Result<TestResponse>?> get() = _testResult

    fun fetchTests() {
        viewModelScope.launch {
            _tests.value = testUseCase.getTests()
        }
    }

    fun submitTest(testResponse: TestResponse) {
        viewModelScope.launch {
            _testResult.value = testUseCase.submitTest(testResponse)
        }
    }
}
