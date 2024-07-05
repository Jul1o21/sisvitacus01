import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.snapshots.SnapshotStateMap
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.model.request.PregRespuestaRequest
import com.example.data.model.request.RegTestRequest
import com.example.data.model.request.TestRequest
import com.example.data.model.response.DiagnosticoAutomatico
import com.example.data.model.response.TestSingleResponse
import com.example.domain.RealizarTestUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EstudRealizarTestViewModel : ViewModel() {
    private val realizarTestUseCase = RealizarTestUseCase()

    private val _testSingleResponse = MutableLiveData<TestSingleResponse>()
    val testSingleResponse: LiveData<TestSingleResponse> get() = _testSingleResponse

    private val _respuestas = mutableStateListOf<PregRespuestaRequest>()
    val respuestas: SnapshotStateList<PregRespuestaRequest> get() = _respuestas

    private val _selectedOptions = mutableStateMapOf<Int, Int>()
    val selectedOptions: SnapshotStateMap<Int, Int> get() = _selectedOptions

    private val _diagnosticoAutomatico = MutableLiveData<DiagnosticoAutomatico>()
    val diagnosticoAutomatico: LiveData<DiagnosticoAutomatico> get() = _diagnosticoAutomatico


    fun updateRespuesta(id_preg: Int, puntaje: Int) {
        _selectedOptions[id_preg] = puntaje
        _respuestas.removeAll { it.id_preg == id_preg }
        _respuestas.add(PregRespuestaRequest(id_preg = id_preg, puntaje = puntaje))
        _respuestas.forEach { respuesta ->
            println("id_preg: ${respuesta.id_preg}, puntaje: ${respuesta.puntaje}")
        }
    }

    fun obtenerTest(testRequest: TestRequest) {
        viewModelScope.launch {
            try {
                val response = realizarTestUseCase.getTest(testRequest)
                _testSingleResponse.value = response
            } catch (e: Exception) {
                // Manejar el error
            }
        }
    }


    fun setDiagnosticoAutomatico(diagnosticoAutomatico: DiagnosticoAutomatico) {
        _diagnosticoAutomatico.postValue(diagnosticoAutomatico)
        println("Diagnostico automatico actualizado: $_diagnosticoAutomatico")
    }

    fun regTest(id_est: Int) {
        println("se llama a la funcion regTest()")
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val regTestRequest = RegTestRequest(id_est, _respuestas.toList())
                val response = realizarTestUseCase.regTest(regTestRequest)

                if(response.success && response.message == "COMPLETE") {
                    println("JSON recibido con éxito: $response")

                    val diagnosticoAutomatico = response.data.diagnostico_automatico
                    println("Diagnostico automatico: $diagnosticoAutomatico")

                    // Usar postValue para actualizar el LiveData desde un hilo de fondo
                    setDiagnosticoAutomatico(diagnosticoAutomatico)

                }
            } catch(e: Exception) {
                println("ERROR: "+e)
            }
        }
    }
}
