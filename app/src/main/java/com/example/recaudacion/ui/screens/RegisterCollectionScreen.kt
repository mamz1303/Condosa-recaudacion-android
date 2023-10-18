package com.example.recaudacion.ui.screens

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.recaudacion.R
import com.example.recaudacion.navigation.AppScreens



@Composable
fun RegisterCollection1PageScreen( registerViewModel: RegisterViewModel, navController: NavController) {
    var currentIndex by remember { mutableStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Header(navController = navController)
        Spacer(modifier = Modifier.height(20.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // Botón "Anterior"
            if (currentIndex > 0) {
                /*Button(
                    onClick = {
                        currentIndex--
                    }
                ) {
                    Text("Anterior")
                }*/
                IconButton(
                    onClick = { currentIndex-- }
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Close",
                        tint = Color.Black,
                        modifier = Modifier.size(70.dp)
                    )
                }
            }
        }

        when (currentIndex) {
            0 -> FormNroDocumento(registerViewModel ,navController, currentIndex) {
                if (currentIndex < 3) {
                    currentIndex++
                }
            }
            1 -> FormRuc(registerViewModel ,navController, currentIndex) {
                if (currentIndex < 3) {
                    currentIndex++
                }
            }
            2 -> FormReceipt(navController, currentIndex) {
                if (currentIndex < 3) {
                    currentIndex++
                }
            }
            3 -> FormCollection(navController ,currentIndex) {
                if (currentIndex < 3) {
                    currentIndex++
                }
            }
            else -> FormNroDocumento(registerViewModel ,navController, currentIndex) {
                if (currentIndex < 3) {
                    currentIndex++
                }
            }
        }
    }
}


@Composable
fun Header(navController: NavController) {
    var showDialog by remember { mutableStateOf(false) }

    // Función para mostrar la alerta
    fun showAlertDialog() {
        showDialog = true
    }

    // Función para manejar la acción del botón en la alerta
    fun handleConfirm() {
        // Aquí puedes ejecutar la acción de confirmación
        // Por ejemplo, cerrar la pantalla, eliminar algo, etc.
        navController.navigate(route = AppScreens.MainMenuScreen.route)
        showDialog = false
    }

    // Contenido de la alerta
    val alertDialog = if (showDialog) {
        AlertDialog(
            onDismissRequest = {
                // Manejar la acción de cerrar la alerta (opcional)
                showDialog = false
            },
            title = { Text(text = "Confirmación") },
            text = { Text(text = "¿Estás seguro de regresar a la interfaz principal?") },
            confirmButton = {
                Button(
                    onClick = {
                        handleConfirm()
                        showDialog = false
                    }
                ) {
                    Text(text = "Sí")
                }
            },
            dismissButton = {
                Button(
                    onClick = {
                        showDialog = false
                    }
                ) {
                    Text(text = "Cancelar")
                }
            }
        )
    } else null


    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween  // Cambiado a SpaceBetween
    ) {
        Image(
            painter = painterResource(R.drawable.logo),
            contentDescription = "logo",
            modifier = Modifier
                .size(width = 150.dp, height = 50.dp)
        )
        IconButton(
            onClick = { showAlertDialog() }
        ) {
            Icon(
                imageVector = Icons.Default.Clear,
                contentDescription = "Close",
                tint = Color.Black,
                modifier = Modifier.size(70.dp)
            )
        }
    }
}

@Composable
fun FormStatus(imageRes: Int, title: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp) ,
        horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally,

    ) {
        Spacer(modifier = Modifier.height(20.dp))
        Image(
            painter = painterResource(imageRes),
            contentDescription = "Barra de progreso"
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = title,
            fontWeight = FontWeight.Bold,
            style = TextStyle(fontSize = 30.sp),
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(20.dp))
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormNroDocumento(registerViewModel: RegisterViewModel, navController: NavController, currentIndex: Int, onNext: () -> Unit){
    FormStatus(imageRes = R.drawable.barradeprogreso1, title = "Información personal")
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally
    ) {

        /*
        val nroDocumento = remember { mutableStateOf(TextFieldValue()) }
        val nombre = remember { mutableStateOf(TextFieldValue()) }
        val banco = remember { mutableStateOf(TextFieldValue()) }
        val moneda = remember { mutableStateOf(TextFieldValue()) }
        val nroCuenta = remember { mutableStateOf(TextFieldValue()) }
         */

        val nroDocumento = remember { mutableStateOf(TextFieldValue()) }
        var nombre = registerViewModel.registerUiState.nombre
        var banco = registerViewModel.registerUiState.banco
        var moneda = registerViewModel.registerUiState.moneda
        var cuenta = registerViewModel.registerUiState.nroCuenta

        TextField(
            label = { Text(text = "Nro. Documento") },
            value = nroDocumento.value,
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            onValueChange = { nroDocumento.value = it },
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            modifier = Modifier
                .fillMaxWidth()
                .border(1.dp, Color.Black, RoundedCornerShape(8.dp))

        )
        Spacer(modifier = Modifier.height(25.dp))
        Button(
            onClick = {
                registerViewModel.getCuentaPorNroDocumento(nroDocumento.value)
            },
            modifier = Modifier.width(200.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF000080))
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search",
                    tint = Color.White,
                    modifier = Modifier.size(20.dp) // Tamaño del icono
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "Buscar")
            }
        }

        Spacer(modifier = Modifier.height(35.dp))
        TextField(
            label = { Text(text = "Nombre") },
            value = nombre,
            singleLine = true,
            enabled = false,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            onValueChange = { nombre = it },
            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            modifier = Modifier
                .fillMaxWidth()
                .border(1.dp, Color.Black, RoundedCornerShape(8.dp))

        )
        Spacer(modifier = Modifier.height(25.dp))

        TextField(
            label = { Text(text = "Banco") },
            value = banco,
            singleLine = true,
            enabled = false,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            onValueChange = { banco = it },
            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            modifier = Modifier
                .fillMaxWidth()
                .border(1.dp, Color.Black, RoundedCornerShape(8.dp))

        )
        Spacer(modifier = Modifier.height(25.dp))

        TextField(
            label = { Text(text = "Moneda") },
            value = moneda,
            singleLine = true,
            enabled = false,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            onValueChange = { moneda = it },
            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            modifier = Modifier
                .fillMaxWidth()
                .border(1.dp, Color.Black, RoundedCornerShape(8.dp))

        )
        Spacer(modifier = Modifier.height(25.dp))

        TextField(
            label = { Text(text = "Nro. Cuenta") },
            value = cuenta.toString(),
            singleLine = true,
            enabled = false,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            onValueChange = {  },
            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            modifier = Modifier
                .fillMaxWidth()
                .border(1.dp, Color.Black, RoundedCornerShape(8.dp))

        )
        Spacer(modifier = Modifier.height(25.dp))

        // Observa si hay un mensaje de error y muestra una alerta en consecuencia
        val errorMessage = registerViewModel.errorMessage
        if (errorMessage.value.isNotEmpty()) {
            mostrarAlerta("Error", errorMessage = errorMessage.value,registerViewModel)
        }


        Box(modifier = Modifier.padding(40.dp, 0.dp, 40.dp, 0.dp)) {
            Button(
                onClick = {
                    /*
                    if(registerViewModel.registerUiState.idCuenta != 0){
                        onNext()
                    }*/

                    onNext()
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent, contentColor = Color(0xFF000080)),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .width(100.dp)
                    .border(
                        width = 2.dp,
                        color = Color(0xFF000080),
                        shape = RoundedCornerShape(15.dp)
                    )
            ) {
                Text(text = "Siguiente")
            }
        }
    }
}

@Composable
fun mostrarAlerta(titulo: String ,errorMessage: String,registerViewModel: RegisterViewModel) {
    AlertDialog(
        onDismissRequest = { /* No hacemos nada al cerrar */ },
        title = { Text(text = titulo) },
        text = { Text(text = errorMessage) },
        confirmButton = {
            TextButton(onClick = {
                registerViewModel.clearErrorMessage() }) {
                Text("Aceptar")
            }
        }
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormRuc(registerViewModel: RegisterViewModel, navController: NavController, currentIndex: Int, onNext: () -> Unit){
    FormStatus(imageRes = R.drawable.barradeprogreso2, title = "Información del predio")
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally
    ) {

        val ruc = remember { mutableStateOf(TextFieldValue()) }
        var nombrePredio = registerViewModel.registerUiState.nombrePredio
        var direccion = registerViewModel.registerUiState.direccion
        var tipoDePredio = registerViewModel.registerUiState.tipoPredio
        var nroCuentaPredio = registerViewModel.registerUiState.nroCuentaPredio
        var tipoDeAutorizacion = registerViewModel.registerUiState.tipoDeAutorizacion
        var estado = registerViewModel.registerUiState.estado

        TextField(
            label = { Text(text = "RUC") },
            value = ruc.value,
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            onValueChange = { ruc.value = it },
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            modifier = Modifier
                .fillMaxWidth()
                .border(1.dp, Color.Black, RoundedCornerShape(8.dp))

        )
        Spacer(modifier = Modifier.height(25.dp))
        Button(
            onClick = {
                registerViewModel.getPredioPorRuc(ruc.value)
            },
            modifier = Modifier.width(200.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF000080))
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search",
                    tint = Color.White,
                    modifier = Modifier.size(20.dp) // Tamaño del icono
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "Buscar")
            }
        }

        Spacer(modifier = Modifier.height(35.dp))
        TextField(
            label = { Text(text = "Nombre") },
            value = nombrePredio,
            singleLine = true,
            enabled = false,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            onValueChange = { nombrePredio = it },
            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            modifier = Modifier
                .fillMaxWidth()
                .border(1.dp, Color.Black, RoundedCornerShape(8.dp))

        )
        Spacer(modifier = Modifier.height(25.dp))

        TextField(
            label = { Text(text = "Dirección") },
            value = direccion,
            singleLine = true,
            enabled = false,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            onValueChange = { direccion = it },
            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            modifier = Modifier
                .fillMaxWidth()
                .border(1.dp, Color.Black, RoundedCornerShape(8.dp))

        )
        Spacer(modifier = Modifier.height(25.dp))

        TextField(
            label = { Text(text = "Tipo de predio") },
            value = tipoDePredio,
            singleLine = true,
            enabled = false,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            onValueChange = { tipoDePredio = it },
            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            modifier = Modifier
                .fillMaxWidth()
                .border(1.dp, Color.Black, RoundedCornerShape(8.dp))

        )
        Spacer(modifier = Modifier.height(25.dp))

        TextField(
            label = { Text(text = "Nro. cuente predio") },
            value = nroCuentaPredio.toString(),
            singleLine = true,
            enabled = false,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            onValueChange = {  },
            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            modifier = Modifier
                .fillMaxWidth()
                .border(1.dp, Color.Black, RoundedCornerShape(8.dp))

        )
        Spacer(modifier = Modifier.height(25.dp))

        TextField(
            label = { Text(text = "Tipo de autorización") },
            value = tipoDeAutorizacion,
            singleLine = true,
            enabled = false,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            onValueChange = { tipoDeAutorizacion = it },
            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            modifier = Modifier
                .fillMaxWidth()
                .border(1.dp, Color.Black, RoundedCornerShape(8.dp))

        )
        Spacer(modifier = Modifier.height(25.dp))

        TextField(
            label = { Text(text = "Estado") },
            value = estado,
            singleLine = true,
            enabled = false,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            onValueChange = { estado = it },
            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            modifier = Modifier
                .fillMaxWidth()
                .border(1.dp, Color.Black, RoundedCornerShape(8.dp))

        )
        Spacer(modifier = Modifier.height(25.dp))

        // Observa si hay un mensaje de error y muestra una alerta en consecuencia
        val errorMessage = registerViewModel.errorMessage
        if (errorMessage.value.isNotEmpty()) {
            mostrarAlerta("Error", errorMessage = errorMessage.value,registerViewModel)
        }

        Box(modifier = Modifier.padding(40.dp, 0.dp, 40.dp, 0.dp)) {
            Button(
                onClick = {
                    onNext()
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent, contentColor = Color(0xFF000080)),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .width(100.dp)
                    .border(
                        width = 2.dp,
                        color = Color(0xFF000080),
                        shape = RoundedCornerShape(15.dp)
                    )
            ) {
                Text(text = "Siguiente")
            }
        }

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormReceipt(navController: NavController, currentIndex: Int, onNext: () -> Unit){
    FormStatus(imageRes = R.drawable.barradeprogreso3, title = "Información del recibo")
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally
    ) {

        val nroRecibo = remember { mutableStateOf(TextFieldValue()) }
        val estadoRecibo = remember { mutableStateOf(TextFieldValue()) }
        val importe = remember { mutableStateOf(TextFieldValue()) }

        TextField(
            label = { Text(text = "N° recibo") },
            value = nroRecibo.value,
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            onValueChange = { nroRecibo.value = it },
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            modifier = Modifier
                .fillMaxWidth()
                .border(1.dp, Color.Black, RoundedCornerShape(8.dp))

        )
        Spacer(modifier = Modifier.height(25.dp))
        Button(
            onClick = {
                // Acción al hacer clic en el botón
            },
            modifier = Modifier.width(200.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF000080))
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search",
                    tint = Color.White,
                    modifier = Modifier.size(20.dp) // Tamaño del icono
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "Buscar")
            }
        }

        Spacer(modifier = Modifier.height(35.dp))
        TextField(
            label = { Text(text = "Estado del recibo") },
            value = estadoRecibo.value,
            singleLine = true,
            enabled = false,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            onValueChange = { estadoRecibo.value = it },
            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            modifier = Modifier
                .fillMaxWidth()
                .border(1.dp, Color.Black, RoundedCornerShape(8.dp))

        )
        Spacer(modifier = Modifier.height(25.dp))

        TextField(
            label = { Text(text = "Importe") },
            value = importe.value,
            singleLine = true,
            enabled = false,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            onValueChange = { importe.value = it },
            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            modifier = Modifier
                .fillMaxWidth()
                .border(1.dp, Color.Black, RoundedCornerShape(8.dp))

        )
        Spacer(modifier = Modifier.height(25.dp))

        Box(modifier = Modifier.padding(40.dp, 0.dp, 40.dp, 0.dp)) {
            Button(
                onClick = {
                    onNext()
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent, contentColor = Color(0xFF000080)),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .width(100.dp)
                    .border(
                        width = 2.dp,
                        color = Color(0xFF000080),
                        shape = RoundedCornerShape(15.dp)
                    )
            ) {
                Text(text = "Siguiente")
            }
        }

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormCollection(navController: NavController, currentIndex: Int, onNext: () -> Unit) {
    FormStatus(imageRes = R.drawable.barradeprogreso4, title = "Información de la recaudación")
    val context = LocalContext.current.applicationContext

    Column(
        modifier = Modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally
    ) {

        val nroOperacion = remember { mutableStateOf(TextFieldValue()) }
        val fecha = remember { mutableStateOf(TextFieldValue()) }
        val tipoDeModa = remember { mutableStateOf(TextFieldValue()) }
        val importe = remember { mutableStateOf(TextFieldValue()) }
        val estado = remember { mutableStateOf(TextFieldValue()) }
        val observacion = remember { mutableStateOf(TextFieldValue()) }

        TextField(
            label = { Text(text = "Nro Operación") },
            value = nroOperacion.value,
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            onValueChange = { nroOperacion.value = it },
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            modifier = Modifier
                .fillMaxWidth()
                .border(1.dp, Color.Black, RoundedCornerShape(8.dp))

        )
        Spacer(modifier = Modifier.height(25.dp))

        TextField(
            label = { Text(text = "Fecha") },
            value = fecha.value,
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            onValueChange = { fecha.value = it },
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            modifier = Modifier
                .fillMaxWidth()
                .border(1.dp, Color.Black, RoundedCornerShape(8.dp))

        )
        Spacer(modifier = Modifier.height(25.dp))

        TextField(
            label = { Text(text = "Tipo de Moneda") },
            value = tipoDeModa.value,
            singleLine = true,
            enabled = false,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            onValueChange = { tipoDeModa.value = it },
            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            modifier = Modifier
                .fillMaxWidth()
                .border(1.dp, Color.Black, RoundedCornerShape(8.dp))

        )
        Spacer(modifier = Modifier.height(25.dp))

        TextField(
            label = { Text(text = "Importe") },
            value = importe.value,
            singleLine = true,
            enabled = false,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            onValueChange = { importe.value = it },
            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            modifier = Modifier
                .fillMaxWidth()
                .border(1.dp, Color.Black, RoundedCornerShape(8.dp))

        )
        Spacer(modifier = Modifier.height(25.dp))

        TextField(
            label = { Text(text = "Estado") },
            value = estado.value,
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            onValueChange = { estado.value = it },
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            modifier = Modifier
                .fillMaxWidth()
                .border(1.dp, Color.Black, RoundedCornerShape(8.dp))

        )
        Spacer(modifier = Modifier.height(25.dp))

        TextField(
            label = { Text(text = "Observación") },
            value = observacion.value,
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            onValueChange = { observacion.value = it },
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            modifier = Modifier
                .fillMaxWidth()
                .border(1.dp, Color.Black, RoundedCornerShape(8.dp))

        )
        Spacer(modifier = Modifier.height(25.dp))

        Box(modifier = Modifier.padding(40.dp, 0.dp, 40.dp, 0.dp)) {
            Button(
                onClick = {
                    Toast.makeText(context, "Recaudación registrada!", Toast.LENGTH_SHORT).show()
                    navController.navigate(route = AppScreens.MainMenuScreen.route)
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF000080)),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .width(100.dp)
            ) {
                Text(text = "Guardar")
            }
        }
        Spacer(modifier = Modifier.height(25.dp))
    }
}