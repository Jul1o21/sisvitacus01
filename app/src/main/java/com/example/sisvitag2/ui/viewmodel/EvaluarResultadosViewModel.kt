import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import com.example.data.model.response.TestResponse
import com.example.sisvita_cus1.data.repository.TestRepository

class EvaluarResultadosTestViewModel : ViewModel() {
    private val testRepository = TestRepository()
    val testsRespondidos = mutableStateListOf<TestResponse>()

    fun obtenerTodosTestsRespondidos() {
        viewModelScope.launch {
            try {
                val response = testRepository.getTodosTests()
                if (response.success) {
                    testsRespondidos.clear()
                    testsRespondidos.addAll(response.data)
                }
            } catch (e: Exception) {
                // Manejar el error
            }
        }
    }
}
