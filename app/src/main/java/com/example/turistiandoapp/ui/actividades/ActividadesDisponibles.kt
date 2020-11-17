package com.example.turistiandoapp.ui.actividades

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.turistiandoapp.Actividades
import com.example.turistiandoapp.R
import com.example.turistiandoapp.adapter.ActividadAdapter
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.google.firebase.database.*
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_actividades_disponibles.*
import kotlinx.android.synthetic.main.item_actividades.view.*
import kotlinx.coroutines.DEBUG_PROPERTY_NAME

class ActividadesDisponibles : AppCompatActivity(), ActividadAdapter.OnActividadClickListener {

    lateinit var mRecyclerView : RecyclerView
    lateinit var database : DatabaseReference
    lateinit var actividadAdapter : ActividadAdapter
    lateinit var listaTodasActividades :List<Actividades>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_actividades_disponibles)
        setUpRecyclerView()
        title = "Actividades"

        database = FirebaseDatabase.getInstance().reference.child("Actividades")
        rvActividades.layoutManager = LinearLayoutManager(this)

        getActividadesFromFirebase()

    }

    fun getActividadesFromFirebase() {
        rvActividades.layoutManager = LinearLayoutManager(this)
        database.child("Actividades").addValueEventListener(object: ValueEventListener{
            override fun onCancelled(error: DatabaseError) {
                Log.w( "loadPost:onCancelled", error.toException())
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists()){
                    for (postSnapshot in snapshot.children) {
                        val nomActividad = postSnapshot.child("nomActividad").value
                        val descripcion = postSnapshot.child("descripcion").value
                        //val costo = postSnapshot.child("costo").value.toString()
                        val direccion = postSnapshot.child("direccion").value
                        val municipio = postSnapshot.child("municipio").value
                        val nombreUser = postSnapshot.child("nombreUser").value
                        val telefono = postSnapshot.child("telefono").value
                        val imagen = postSnapshot.child("imagen").value

                        listaTodasActividades = listOf(Actividades(nomActividad as String, municipio as String, direccion as String,
                               descripcion as String, 12000, nombreUser as String, telefono as Double, imagen as String))
                    }
                    rvActividades.adapter = ActividadAdapter(this@ActividadesDisponibles, listaTodasActividades, this@ActividadesDisponibles)
                }
            }

        })
    }

    private fun setUpRecyclerView (){
        rvActividades.layoutManager = LinearLayoutManager(this)
        rvActividades.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        listaTodasActividades= listOf(Actividades("EL PASEO HYGGE", "Subachoque",
                "Calle 4 4 72, Subachoque 250220 Colombia", "Es un espacio comercial ubicado en Subachoque, a tan solo 1 hora de Bogotá, especialmente diseñado para brindar tranquilidad, rodeado por un entorno natural sin perder el estilo de la ciudad. en el paseo hygge gozara de lugares agradables donde podría tomar un descanso con placidez , alegría y bienestar.",
                0, "Nicolás Nieto", 314.7526984,
                "https://firebasestorage.googleapis.com/v0/b/turisteandoprueba1.appspot.com/o/image%2FEL%20PASEO%20HYGGENICOL%C3%81S%20NIETO.jpg?alt=media&token=34b83583-ec01-45bf-bb41-a0ccfe0f8afb"),
                Actividades("PIEDRAS DEL TUNJO", "Facatativá",
                        "Cl. 5, Facatativá, Cundinamarca", "El Parque Arqueológico de Facatativá (Piedras del Tunjo) comprende aproximadamente 27 hectáreas donde se pueden encontrar abrigos rocosos, pintura rupestre y paisajes con gran riqueza ambiental.\n" +
                        "El parque se encuentra a una altura de 2.600 metros sobre el nivel del mar. Se encuentra ubicado a 1.150 metros al nordeste de la plaza de Facatativá y a 40 km de Bogotá. En sus inicios, fue adquirido por el Ministerio de la Cultura en 1.945 y declarado parque arqueológico en 1.946.",
                        0, "PIEDRAS DEL TUNJO", 312.4571234,
                        "https://firebasestorage.googleapis.com/v0/b/turisteandoprueba1.appspot.com/o/image%2FPIERDAS%20DEL%20TUNJOPIEDRAS%20DE%20TUNJO.jpg?alt=media&token=f342df7f-7923-41b9-9dd4-4a4c24a495db"),
                Actividades("RECREACIÓN Y DIVERSIÓN EN FAMILIA", "Facatativá",
                        "Calle 1AE num5-69", "El Parque es un hermoso lugar, único en el Occidente de la Sabana, situado en el municipio de Madrid a sólo 15 minutos de la entrada de faca, creado para la recreación de las familias de toda la región.",
                        0, "Adriana  Molina, Directora del Instituto Municipal Para El Deporte y La Recreación", 325.8694125,
                        "https://firebasestorage.googleapis.com/v0/b/turisteandoprueba1.appspot.com/o/image%2FRECREACI%C3%93N%20Y%20DIVERSI%C3%93N%20EN%20FAMILIARECREACI%C3%93N%20Y%20DIVERSI%C3%93N%20EN%20FAMILIA.jpg?alt=media&token=65f199f9-9771-4448-91f6-5752368ba2dd"),
                Actividades("SUSTENTACION PI", "Facatativá",
                        "UDEC", "Sustentacion proyecto PI",
                        0, "Sebastian", 302.4443337,
                        "https://firebasestorage.googleapis.com/v0/b/turisteandoprueba1.appspot.com/o/image%2FACTIVIDAD%20EN%20EL%20ROSALDGHGFHFG.jpg?alt=media&token=c2e615c7-968b-46ca-a359-484b8b7b1def")
        )
        rvActividades.adapter = ActividadAdapter(this, listaTodasActividades, this)
    }

    override fun onItemClick(nomActividad: String?, nombreUser: String?, descripcion: String?, imagen: String?, municipio: String?, costo: Int?, direccion: String?, telefono: Double?) {
        val intent = Intent(this, DetalleActividad::class.java)
        intent.putExtra("imagenActividad", imagen)
        intent.putExtra("tNombreActividad", nomActividad)
        intent.putExtra("tNombrePublicador", nombreUser)
        intent.putExtra("tDescripActividad", descripcion)
        intent.putExtra("tValorCosto", costo)
        intent.putExtra("tTelefono", telefono)
        intent.putExtra("tDireccion", direccion)
        startActivity(intent)
    }


}
