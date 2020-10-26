package com.example.turistiandoapp.ui.favoritos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.turistiandoapp.R

class FavoritosFragment : Fragment() {

    private lateinit var favoritosViewModel: FavoritosViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        favoritosViewModel =
            ViewModelProvider(this).get(FavoritosViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_favoritos, container, false)
        val textView: TextView = root.findViewById(R.id.text_slideshow)
        favoritosViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }
}