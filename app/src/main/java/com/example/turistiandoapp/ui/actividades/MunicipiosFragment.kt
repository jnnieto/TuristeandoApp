package com.example.turistiandoapp.ui.actividades

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.turistiandoapp.MainAppTuristeando
import com.example.turistiandoapp.R
import com.example.turistiandoapp.login_turisteando

class MunicipiosFragment : Fragment() {

    var btn_bojaca: ImageButton ?= null
    var btn_rosal: ImageButton ?= null
    var btn_facatativa: ImageButton ?= null
    var btn_funza: ImageButton ?= null
    var btn_madrid: ImageButton ?= null
    var btn_mosquera: ImageButton ?= null
    var btn_subachoque: ImageButton ?= null
    var btn_zipacon: ImageButton ?= null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?{
        return inflater.inflate(R.layout.fragment_municipios, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btn_bojaca=view.findViewById(R.id.IBBojaca) as ImageButton
        btn_bojaca!!.setOnClickListener{
            it.findNavController().navigate(R.id.action_nav_actividades_to_registro_turisteando)
        }
        btn_rosal=view.findViewById(R.id.IBElRosal) as ImageButton
        btn_rosal!!.setOnClickListener{
            it.findNavController().navigate(R.id.action_nav_actividades_to_registro_turisteando)
        }
        btn_facatativa=view.findViewById(R.id.IBFacatativa) as ImageButton
        btn_facatativa!!.setOnClickListener{
            it.findNavController().navigate(R.id.action_nav_actividades_to_registro_turisteando)
        }
        btn_funza=view.findViewById(R.id.IBFunza) as ImageButton
        btn_funza!!.setOnClickListener{
            it.findNavController().navigate(R.id.action_nav_actividades_to_registro_turisteando)
        }
        btn_madrid=view.findViewById(R.id.IBMadrid) as ImageButton
        btn_madrid!!.setOnClickListener{
            it.findNavController().navigate(R.id.action_nav_actividades_to_registro_turisteando)
        }
        btn_mosquera=view.findViewById(R.id.IBMosquera) as ImageButton
        btn_mosquera!!.setOnClickListener{
            it.findNavController().navigate(R.id.action_nav_actividades_to_registro_turisteando)
        }
        btn_subachoque=view.findViewById(R.id.IBSubachoque) as ImageButton
        btn_subachoque!!.setOnClickListener{
            it.findNavController().navigate(R.id.action_nav_actividades_to_registro_turisteando)
        }
        btn_zipacon=view.findViewById(R.id.IBZipacon) as ImageButton
        btn_zipacon!!.setOnClickListener{
            it.findNavController().navigate(R.id.action_nav_actividades_to_registro_turisteando)
        }
    }



}