package com.example.recaudacion.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
/*===========*/




/*Recibo: Recibo que el Propietario recibe */
@Serializable
data class MantenimientoRecibo(
    @SerialName("idMantRecibo")   val idMantRecibo: Int,
    @SerialName("estadoRecibo")   val estadoRecibo: String,
    @SerialName("importe") val   importe: Double
)
