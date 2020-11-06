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
                Actividades("PARQUE ACUÁTICO", "Mosquera",
                        "Cl. 10 #41 Mosquera, Cundinamarca", "Se encuentra ubicado en el Parque Cultural, funciona con diferentes programas para niños, adultos y personas diversamente hábiles y adultos mayores. En el Parque acuático también se llevan a cabo programas de Fitness y rumba en el agua.",
                        0, "Alcaldía de Mosquera", 320.6857415,
                        "https://firebasestorage.googleapis.com/v0/b/turisteandoprueba1.appspot.com/o/image%2FPARQUE%20ACU%C3%81TICOALCALD%C3%8DA%20DE%20MOSQUERA.jpg?alt=media&token=3d9df0d2-2888-4171-b0c4-dfc6e28500d3"),
                Actividades("REFUGIO DE LOS ARCÁNGELES", "El Rosal",
                        "Vía a la Pradera", "El Refugio de los Arcángeles es un lugar donde puedes pasar tiempo con tu familia, disfrutando de la naturaleza. La alcaldía del Rosal te quiere invitar a que vaya y participes al festival del cometas en Noviembre",
                        0, "Alcaldía del Rosal", 314.7526984,
                        "https://firebasestorage.googleapis.com/v0/b/turisteandoprueba1.appspot.com/o/image%2FREFUGIO%20DE%20LOS%20ARC%C3%81NGELESALCALD%C3%8DA%20DEL%20ROSAL.jpg?alt=media&token=d8a36a0e-9c2b-4111-a63b-b0f4e951fb19"),
                Actividades("SAN ANDRÉS GOLF CLUB", "Funza",
                        "Direccion: Calle 15 #24-11 Funza Colombia", "Está ubicado en el corazón de la sabana occidental de Bogotá, en el Municipio de Funza , elegido por gozar de un microclima especial con un 25% menos en el régimen de lluvias en un extenso terreno que cuenta con un drenaje único, facilitando la práctica de deportes al aire libre en temporadas de lluvias.",
                        0, "San Andrés Golf Club", 314.7526984,
                        "https://firebasestorage.googleapis.com/v0/b/turisteandoprueba1.appspot.com/o/image%2FSAN%20ANDR%C3%89S%20GOLF%20CLUBSAN%20ANDR%C3%89S%20GOLF%20CLUB.jpg?alt=media&token=7872095d-451c-4dc7-8d80-d6442ad455b2")

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
