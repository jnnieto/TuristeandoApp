package com.example.turistiandoapp

class Actividades{
    var nomActividad : String? = null
    var municipio: String? = null
    var direccion : String? = null
    var descripcion : String? = null
    var costo : Int? = null
    var nombreUser : String? = null
    var telefono : Double? = null
    var imagen : String? = null

    constructor(nomActividad: String, municipio: String, direccion: String, descripcion: String, costo: Int, nombreUser: String, telefono: Double, imagen: String) {
        this.nomActividad = nomActividad
        this.municipio = municipio
        this.direccion = direccion
        this.descripcion = descripcion
        this.costo = costo
        this.nombreUser = nombreUser
        this.telefono = telefono
        this.imagen = imagen
    }

    constructor()

}