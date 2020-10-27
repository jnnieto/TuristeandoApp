package com.example.turistiandoapp.ui.publicaciones

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.turistiandoapp.Publicaciones
import com.example.turistiandoapp.R
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference

class PublicacionesFragment : Fragment() {

    private lateinit var publicacionesViewModel: PublicacionesViewModel
    /*lateinit var filePath: Uri
    internal var storage: FirebaseStorage ?= null
    internal var storageReference: StorageReference ?=null*/

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        publicacionesViewModel =
            ViewModelProvider(this).get(PublicacionesViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_publicaciones, container, false)
        publicacionesViewModel.text.observe(viewLifecycleOwner, Observer {
        })
        /*storage = FirebaseStorage.getInstance()
        storageReference = storage!!.reference*/

        var database = FirebaseDatabase.getInstance().reference
        val act = (root.findViewById<View>(R.id.textNomActividad) as EditText)
        val dire = (root.findViewById<View>(R.id.textDirecccion) as EditText)
        val desc = (root.findViewById<View>(R.id.textDescripcion) as EditText)
        val costo = (root.findViewById<View>(R.id.textCosto) as EditText)
        val btn = root.findViewById<View>(R.id.btnPublicar) as Button
        val cargar = root.findViewById<View>(R.id.btnCargarImagen) as Button
        var nomMun :String = ""
        var mun = (root.findViewById<View>(R.id.spinner) as Spinner)

        mun.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
                val prueba = ""
            }
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                nomMun = parent?.getItemAtPosition(position) as String;
                //Toast.makeText(applicationContext,"$prueba2",Toast.LENGTH_LONG).show()
            }
        }
        cargar.setOnClickListener{

        }

        btn.setOnClickListener{
            var numero = true
            if(TextUtils.isEmpty(act.text)){
                Toast.makeText(activity,"Llena el campo de actividad",Toast.LENGTH_LONG).show()
            }else if(TextUtils.isEmpty(dire.text)){
                Toast.makeText(activity,"Llena el campo de dirección",Toast.LENGTH_LONG).show()
            }else if (TextUtils.isEmpty(desc.text)){
                Toast.makeText(activity,"Llena el campo de descripción",Toast.LENGTH_LONG).show()
            }else if(costo.text.toString() == ""){
                Toast.makeText(activity,"Llena el campo de costo",Toast.LENGTH_LONG).show()
            }else if(nomMun.equals("Seleccione")){
                Toast.makeText(activity,"Seleccione un Municipio",Toast.LENGTH_LONG).show()
            }else if((TextUtils.isEmpty(dire.text))&&(TextUtils.isEmpty(act.text))&&(TextUtils.isEmpty(desc.text))){
                Toast.makeText(activity,"Falta algún dato",Toast.LENGTH_LONG).show()
            }else{
                val costo2 = costo.text.toString().toInt()
                numero = costo.text.matches("-?\\d+(\\.\\d+)?".toRegex())
                if (numero){
                    database.child(act.text.toString()).setValue(Publicaciones(act.text.toString(),nomMun,dire.text.toString(),desc.text.toString(),costo2))
                    act.setText("")
                    dire.setText("")
                    desc.setText("")
                    costo.setText("")
                    Toast.makeText(activity,"Publicacion exitosa!",Toast.LENGTH_LONG).show()
                }
                else{
                    Toast.makeText(activity,"Tiene que ser un numero el (Costo)",Toast.LENGTH_LONG).show()
                }
            }
        }
        return root
    }
    private fun cargarFotoImagen(){
        var i = Intent()
        i.setType("image/*")
        i.setAction(Intent.ACTION_GET_CONTENT)
        startActivityForResult(Intent.createChooser(i,"Elegir Imagen"),111)
    }
    /*
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode==111&& resultCode== Activity.RESULT_OK&&data != null){
            filePath = data.data!!
            //var bitmap = MediaStore.Images.Media.getBitmap(contentR)
        }
    }

     */
}