package com.example.turistiandoapp.ui.publicaciones

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.turistiandoapp.R

class PublicacionesFragment : Fragment() {

    private lateinit var publicacionesViewModel: PublicacionesViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        publicacionesViewModel =
            ViewModelProvider(this).get(PublicacionesViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_publicaciones, container, false)
        val textView: TextView = root.findViewById(R.id.text_gallery)
        publicacionesViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }
}