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
    lateinit var listaTodasActividades :ArrayList<Actividades>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_actividades_disponibles)
        //setUpRecyclerView()

        database = FirebaseDatabase.getInstance().reference.child("Actividades")
        rvActividades.layoutManager = LinearLayoutManager(this)
        database.child("Actividades").addValueEventListener(object: ValueEventListener{
            override fun onCancelled(error: DatabaseError) {
                Log.w( "loadPost:onCancelled", error.toException())
            }

            override fun onDataChange(snapshot: DataSnapshot) {

                if(snapshot.exists()){
                    for (postSnapshot in snapshot.children) {
                        /*var nomActividad2:String = postSnapshot.child("nomActividad").value.toString()
                        var descripcion2:String = postSnapshot.child("descripcion").value.toString()
                        var costo2:Int = postSnapshot.child("costo").value.toString().toInt()
                        var direccion2:String = postSnapshot.child("direccion").value.toString()
                        var municipio2:String= postSnapshot.child("municipio").value.toString()
                        var nombreUser2:String = postSnapshot.child("nombreUser").value.toString()
                        var telefono2 : Double = postSnapshot.child("telefono").value.toString().toDouble()
                        var imagen2:String = postSnapshot.child("imagen").value.toString()

                        listaTodasActividades.add(Actividades(nomActividad2, municipio2, direccion2,
                                descripcion2, costo2 , nombreUser2, telefono2, imagen2))*/

                        val actividad : Actividades? = postSnapshot.getValue(Actividades::class.java)
                        if (actividad != null) {
                            listaTodasActividades.add(actividad)
                        }
                    }
                    rvActividades.adapter = ActividadAdapter(this@ActividadesDisponibles, listaTodasActividades)
                }
            }
        })
    }
    private fun setUpRecyclerView (){
        rvActividades.layoutManager = LinearLayoutManager(this)
        rvActividades.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        val listActividades = listOf(
                Actividades("Montar bici", "Facatativa", "Calle 1", "Disfruta un lindo paseo con tu familia en un recorrido al aire libre",
                        12000, "NicoYTatis", 312.0001212, "https://firebasestorage.googleapis.com/v0/b/turisteandoprueba1.appspot.com/o/image%2FHOLA%20FJDJFJFNICOL%C3%81S.jpg?alt=media&token=56fd408c-03e7-42a8-93fa-4a4b8b180e9e"),
                Actividades("Montar bici", "Facatativa", "Calle 1", "Disfruta un lindo paseo con tu familia en un recorrido al aire libre",
                        12000, "NicoYTatis", 312.0001212, "https://firebasestorage.googleapis.com/v0/b/turisteandoprueba1.appspot.com/o/image%2FHOLA%20FJDJFJFNICOL%C3%81S.jpg?alt=media&token=56fd408c-03e7-42a8-93fa-4a4b8b180e9e"),
                Actividades("ksdjfkjsdhfjkf", "pdsjfkdshfkjf", "sdjfkdsjldsjf", "Disfruta un lindo paseo con tu familia en un recorrido al aire libre",
                        12000, "NicoYTatis", 312.0001212, "https://firebasestorage.googleapis.com/v0/b/turisteandoprueba1.appspot.com/o/image%2FHOLA%20FJDJFJFNICOL%C3%81S.jpg?alt=media&token=56fd408c-03e7-42a8-93fa-4a4b8b180e9e")
                )
        //rvActividades.adapter = ActividadAdapter(this, listActividades, this)
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

                        //listaTodasActividades = listOf(Actividades(nomActividad as String, municipio as String, direccion as String,
                          //      descripcion as String, 12000, nombreUser as String, telefono as Double, imagen as String))
                    }
                    rvActividades.adapter = ActividadAdapter(this@ActividadesDisponibles, listaTodasActividades)
                }
            }

        })
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
