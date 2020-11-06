package com.example.turistiandoapp.adapter

import android.content.Context
import android.content.res.Resources
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.turistiandoapp.Actividades
import com.example.turistiandoapp.R
import kotlinx.android.synthetic.main.item_actividades.view.*
import java.lang.IllegalArgumentException

class ActividadAdapter(val context: Context, val listaActividades : List<Actividades>,private val itemClickListener: OnActividadClickListener) : RecyclerView.Adapter<BaseViewHolder<*>>() {

    interface OnActividadClickListener{
        fun onItemClick(nomActividad: String?, nombreUser: String?, descripcion: String?, imagen: String?, municipio: String?, costo: Int?, direccion: String?, telefono: Double?)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        return ActividadesViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_actividades, parent, false))
    }

    override fun getItemCount(): Int {
        return listaActividades.size
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when(holder){
            is ActividadesViewHolder -> holder.bind(listaActividades[position], position)
            else -> throw IllegalArgumentException("Se olvidó de pasar el viewholder en el bind")
        }
    }
    inner class ActividadesViewHolder (itemView: View) : BaseViewHolder<Actividades>(itemView){
        override fun bind(item: Actividades, position: Int) {
            Glide.with(context).load(item.imagen).into(itemView.imageVActividad)
            itemView.tViewNombreActividad.text = item.nomActividad
            itemView.tViewNombrePublicador.text = item.nombreUser
            itemView.tViewDescripActividad.text = item.descripcion
            itemView.tVCosto.text =  item.costo.toString()
            itemView.tVTelefono.text = item.telefono.toString()
            itemView.tVDireccion.text = item.direccion.toString()

            itemView.setOnClickListener{
                itemClickListener.onItemClick(item.nomActividad, item.nombreUser, item.descripcion,
                    item.imagen, item.municipio, item.costo, item.direccion, item.telefono)
            }
        }

    }

}