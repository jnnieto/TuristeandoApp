package com.example.turistiandoapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import androidx.navigation.findNavController

class InicioOpcionesFragment : Fragment() {

    var btn_buscar: Button?= null
    var btn_publicar: Button?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btn_buscar=view.findViewById(R.id.BtnBuscar) as Button
        btn_buscar!!.setOnClickListener{
            it.findNavController().navigate(R.id.action_inicioOpcionesFragment_to_nav_actividades)
        }
        btn_publicar=view.findViewById(R.id.BtnPublicar) as Button
        btn_publicar!!.setOnClickListener{
            it.findNavController().navigate(R.id.action_navinicio_to_nav_publicaciones)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_inicio_opciones, container, false)

    }
}