import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.model.response.TestResult
import com.example.domain.EvaluarResultadosTestUseCase
import kotlinx.coroutines.launch


class EvaluarResultadosTestViewModel : ViewModel() {

    private val evaluarResultadosTestUseCase = EvaluarResultadosTestUseCase()

    val testsRespondidos = mutableStateListOf<TestResult>()

    fun obtenerTodosTestsRespondidos() {
        viewModelScope.launch {
            try {
                val response = evaluarResultadosTestUseCase.getTestResueltos()
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

