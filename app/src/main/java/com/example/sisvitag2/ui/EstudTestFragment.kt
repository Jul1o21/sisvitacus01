package com.example.sisvitag2.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.sisvitag2.databinding.FragmentEstudTestBinding
import com.example.sisvitag2.viewmodel.EstudTestViewModel

class EstudTestFragment : Fragment() {

    private lateinit var viewModel: EstudTestViewModel
    private lateinit var binding: FragmentEstudTestBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflar y obtener el binding
        binding = FragmentEstudTestBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner

        // Inicializar el ViewModel
        viewModel = ViewModelProvider(this).get(EstudTestViewModel::class.java)
        binding.viewModel = viewModel

        // Observar los cambios en las preguntas
        observeViewModel()

        // Devolver la vista raíz del binding
        return binding.root
    }

    private fun observeViewModel() {
        viewModel.questions.observe(viewLifecycleOwner, Observer { questions ->
            // Aquí puedes manejar la lista de preguntas observada
            questions?.let {
                // Por ejemplo, puedes actualizar la interfaz de usuario con las preguntas
            }
        })
    }
}
