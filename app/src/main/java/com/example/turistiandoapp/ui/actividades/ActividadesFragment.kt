package com.example.turistiandoapp.ui.actividades

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.turistiandoapp.R

class ActividadesFragment : Fragment() {

    private lateinit var actividadesViewModel: ActividadesViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        actividadesViewModel =
            ViewModelProvider(this).get(ActividadesViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_actividades, container, false)
        val textView: TextView = root.findViewById(R.id.text_home)
        actividadesViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }
}