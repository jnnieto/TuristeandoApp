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
import androidx.navigation.fragment.findNavController
import com.example.turistiandoapp.MainAppTuristeando
import com.example.turistiandoapp.R
import com.example.turistiandoapp.login_turisteando

class MunicipiosFragment : Fragment() {

    lateinit var btnFaca: ImageButton
    lateinit var actividad : ActividadesFragment

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?{
        val view = inflater.inflate(R.layout.fragment_municipios, container, false)
        btnFaca = view.findViewById(R.id.IBFacatativa)

        btnFaca.setOnClickListener{

            val transition : FragmentTransaction = childFragmentManager.beginTransaction()
            transition.replace(R.id.sViewMunicipios, actividad)
            transition.addToBackStack(null)
            transition.commit()
        }

        return view;
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }



}