package com.example.turistiandoapp.ui.actividades

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.turistiandoapp.R

class MunicipiosFragment : Fragment() {

    private lateinit var municipiosViewModel: MunicipiosViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        municipiosViewModel =
            ViewModelProvider(this).get(MunicipiosViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_municipios, container, false)
        municipiosViewModel.text.observe(viewLifecycleOwner, Observer {
        })
        return root
    }
}