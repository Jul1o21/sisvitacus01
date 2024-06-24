import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.snapshots.SnapshotStateMap
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.model.Especialista
import com.example.data.model.request.LoginRequest
import com.example.data.model.request.PregRespuesta
import com.example.data.model.request.RegTestRequest
import com.example.data.model.request.TestRequest
import com.example.data.model.response.TestResponse
import com.example.domain.RegTestUseCase
import com.example.sisvita_cus1.data.model.Estudiante
import com.example.sisvita_cus1.domain.TestUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EstudTestViewModel : ViewModel() {
    private val testUseCase = TestUseCase()
    private val regTestUseCase = RegTestUseCase()

    private val _testResponse = MutableLiveData<TestResponse>()
    val testResponse: LiveData<TestResponse> get() = _testResponse

    private val _respuestas = mutableStateListOf<PregRespuesta>()
    val respuestas: SnapshotStateList<PregRespuesta> get() = _respuestas

    private val _selectedOptions = mutableStateMapOf<Int, Int>()
    val selectedOptions: SnapshotStateMap<Int, Int> get() = _selectedOptions

    fun updateRespuesta(id_preg: Int, puntaje: Int) {
        _selectedOptions[id_preg] = puntaje
        _respuestas.removeAll { it.id_preg == id_preg }
        _respuestas.add(PregRespuesta(id_preg = id_preg, puntaje = puntaje))
        _respuestas.forEach { respuesta ->
            println("id_preg: ${respuesta.id_preg}, puntaje: ${respuesta.puntaje}")
        }
    }

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

    fun regTest(id_est: Int) {
        println("se llama a la funcion regTest()")
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val regTestRequest = RegTestRequest(id_est, _respuestas.toList())
                val response = regTestUseCase.regTest(regTestRequest)

                if(response.success && response.message == "COMPLETE") {
                    println("JSON recibido con éxito: $response")
                }
            } catch(e: Exception) {
                println("ERROR: "+e)
            }
        }
    }
}
