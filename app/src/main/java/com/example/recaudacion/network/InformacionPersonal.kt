package com.example.recaudacion.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.math.BigDecimal

@Serializable
data class InformacionPersonal(
    @SerialName("idCuenta") val idCuenta: Integer,
    @SerialName("nombre") val nombre: String,
    @SerialName("banco") val banco: String,
    @SerialName("moneda") val moneda: String,
    @SerialName("nroCuenta") val nroCuenta: Long
)
