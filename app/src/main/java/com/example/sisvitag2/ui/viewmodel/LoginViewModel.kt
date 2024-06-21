package com.example.sisvitag2.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State
import androidx.lifecycle.viewModelScope
import com.example.data.model.Especialista
import com.example.data.model.request.LoginRequest
import com.example.data.repository.LoginRepository
import com.example.sisvita_cus1.data.model.Estudiante
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {

    private val repository = LoginRepository()

    private val _correoState = mutableStateOf("")
    val correoState: State<String> = _correoState

    private val _contraseniaState = mutableStateOf("")
    val contraseniaState: State<String> = _contraseniaState

    private val _rememberMeState = mutableStateOf(false)
    val rememberMeState: State<Boolean> = _rememberMeState

    private val _isError = mutableStateOf(false)
    val isError: State<Boolean> = _isError

    private val _isLoading = mutableStateOf(false)
    val isLoading: State<Boolean> = _isLoading



    private val _loginSuccess = mutableStateOf(false)
    val loginSuccess: State<Boolean> = _loginSuccess

    private val _showDialog = mutableStateOf(false)
    val showDialog: State<Boolean> = _showDialog

    private val _dialogMessage = mutableStateOf("")
    val dialogMessage: State<String> = _dialogMessage

    private val _estudiante = mutableStateOf<Estudiante?>(null)
    val estudiante: State<Estudiante?> = _estudiante

    private val _especialista = mutableStateOf<Especialista?>(null)
    val especialista: State<Especialista?> = _especialista
    fun setEmail(email: String) {
        _correoState.value = email
    }

    fun setPassword(password: String) {
        _contraseniaState.value = password
    }

    fun setRememberMe(checked: Boolean) {
        _rememberMeState.value = checked
    }

    fun submitLoginForm() {
        login()
    }

    fun setDialogMessage(message: String) {
        _dialogMessage.value = message
        mostrarDialog()
    }

    fun mostrarDialog() {
        println("se llama a mostrarDialog()")
        _showDialog.value = true
    }
    fun dismissDialog() {
        println("se llama a dismissDialog()")
        _showDialog.value = false
        _dialogMessage.value = ""
        _loginSuccess.value = false
    }

    fun login() {
        println("se llama a la funcion login()")
        _isLoading.value = true
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val loginRequest = LoginRequest(_correoState.value, _contraseniaState.value)
                val response = repository.login(loginRequest)

                if (response.success && response.data != null) {
                    println("JSON recibido con éxito: $response") // Mensaje para la consola de Tomcat

                    val usuarioResponse = response.data.usuario
                    println("UsuarioResponse: $usuarioResponse") // Mensaje para la consola de Tomcat
                    // Verificar el tipo de usuario
                    if (usuarioResponse.tipo_usuario == "estudiante") {
                        println("Es un estudiante")
                        _estudiante.value = Estudiante(
                            id_estudiante = usuarioResponse.id_estudiante ?: 0,
                            id_usuario = usuarioResponse.id_usuario,
                            nombre_completo = usuarioResponse.nombre_completo,
                            correo = usuarioResponse.correo,
                            contrasenia = usuarioResponse.contrasenia,
                            numero_documento = usuarioResponse.numero_documento,
                            tipo_documento = usuarioResponse.tipo_documento,
                            sexo = usuarioResponse.sexo,
                            edad = usuarioResponse.edad,
                            numero_celular = usuarioResponse.numero_celular,
                            pais = usuarioResponse.pais,
                            departamento = usuarioResponse.departamento ?: "",
                            distrito = usuarioResponse.distrito ?: "",
                            universidad = usuarioResponse.universidad ?: ""
                        )
                    } else if (usuarioResponse.tipo_usuario == "especialista") {
                        println("Es un especialista")
                        _especialista.value = Especialista(
                            id_especialista = usuarioResponse.id_especialista ?: 0,
                            id_usuario = usuarioResponse.id_usuario,
                            nombre_completo = usuarioResponse.nombre_completo,
                            correo = usuarioResponse.correo,
                            contrasenia = usuarioResponse.contrasenia,
                            numero_documento = usuarioResponse.numero_documento,
                            tipo_documento = usuarioResponse.tipo_documento,
                            sexo = usuarioResponse.sexo,
                            edad = usuarioResponse.edad,
                            numero_celular = usuarioResponse.numero_celular,
                            pais = usuarioResponse.pais
                        )
                    }
                    _loginSuccess.value = true
                } else {
                    _isError.value = true
                }
                _isLoading.value = false
            } catch (e: Exception) {
                _isError.value = true
                _isLoading.value = false
            }
        }
    }

}