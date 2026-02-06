package sc.android.currency_converter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import sc.android.currency_converter.ui.theme.Currency_ConverterTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Currency_ConverterTheme {
                Scaffold(
                    modifier = Modifier
                        .systemBarsPadding()
                        .fillMaxSize()
                ) { innerPadding ->
                    CurrencyConvertor(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun CurrencyConvertor(modifier : Modifier){

    //units
    var iUnit by remember { mutableStateOf("Select") }
    var oUnit by remember { mutableStateOf("Select") }

    //values
    var iValue by remember { mutableStateOf("") }
    var oValue by remember { mutableStateOf("") }

    //dropdown status
    var iExpanded by remember { mutableStateOf(false) }
    var oExpanded by remember { mutableStateOf(false) }

    //conversion factors
    var iconversionFactor by remember { mutableStateOf<Double?>(null) }
    var oconversionFactor by remember { mutableStateOf<Double?>(null) }

    //conversion logic
    fun convertUnit() {

        //input value checking
        if (iValue.isBlank()) {
            oValue = ""
            return
        }

        //values if null return
        val input = iValue.toDoubleOrNull() ?: return
        val inRate = iconversionFactor ?: return
        val outRate = oconversionFactor ?: return

        //conversion formula
        val result = input * (outRate / inRate)

        //for three decimal places
        oValue = String.format("%.3f", result)
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center)

    {
        Text(
            "Currency Converter",
            fontSize = 35.sp,
            fontFamily = FontFamily.Serif,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF0521AD)
        )

        Spacer(Modifier.height(24.dp))

        OutlinedTextField(
            value = iValue,
            onValueChange =
                {
                    iValue = it
                    convertUnit()
                },
            label = {
                Text("Enter Value")
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number
            )
        )

        Spacer(Modifier.height(16.dp))

        Row {

            //input button box
            Box {
                //input button
                Button(
                    onClick = {
                        iExpanded = true
                    },
                    border = BorderStroke(2.dp, Color(0xFF10217E)),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF5B6BBB)
                    )
                ) {
                    Text(iUnit)
                    Icon(Icons.Outlined.ArrowDropDown, contentDescription = null)
                }

                //input dropdown
                DropdownMenu(expanded = iExpanded, onDismissRequest = { iExpanded = false }
                ) {
                    DropdownMenuItem(
                        text = { Text("INR") },
                        onClick = {
                            iUnit = "INR"
                            iExpanded = false
                            iconversionFactor = 1.00
                            convertUnit()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("USD") },
                        onClick = {
                            iUnit = "USD"
                            iExpanded = false
                            iconversionFactor = 0.0110
                            convertUnit()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("JPY") },
                        onClick = {
                            iUnit = "JPY"
                            iExpanded = false
                            iconversionFactor = 1.74
                            convertUnit()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("KRW") },
                        onClick = {
                            iUnit = "KRW"
                            iExpanded = false
                            iconversionFactor = 16.0
                            convertUnit()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("RUB") },
                        onClick = {
                            iUnit = "RUB"
                            iExpanded = false
                            iconversionFactor = 0.87
                            convertUnit()
                        }
                    )
                }
            }

            Spacer(Modifier.width(16.dp))

            //output button box
            Box {
                //output button
                Button(
                    onClick = {
                        oExpanded = true
                    },
                    border = BorderStroke(2.dp, Color(0xFF10217E)),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF5B6BBB)
                    )
                ) {
                    Text(oUnit)
                    Icon(Icons.Outlined.ArrowDropDown, contentDescription = null)
                }

                //output dropdown
                DropdownMenu(expanded = oExpanded, onDismissRequest = { oExpanded = false }
                ) {
                    DropdownMenuItem(
                        text = { Text("INR") },
                        onClick = {
                            oUnit = "INR"
                            oExpanded = false
                            oconversionFactor = 1.00
                            convertUnit()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("USD") },
                        onClick = {
                            oUnit = "USD"
                            oExpanded = false
                            oconversionFactor = 0.0110
                            convertUnit()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("JPY") },
                        onClick = {
                            oUnit = "JPY"
                            oExpanded = false
                            oconversionFactor = 1.74
                            convertUnit()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("KRW") },
                        onClick = {
                            oUnit = "KRW"
                            oExpanded = false
                            oconversionFactor = 16.0
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("RUB") },
                        onClick = {
                            oUnit = "RUB"
                            oExpanded = false
                            oconversionFactor = 0.87
                            convertUnit()
                        }
                    )
                }
            }
        }

        Spacer(Modifier.height(24.dp))

        //only displays the output if the output is not empty (which is set to empty if the input is empty) and the conversion factors are not null, i.e. they have been selected from the dropdown menus
        if (oValue.isNotEmpty() && iconversionFactor != null && oconversionFactor != null) {
            Text(
                "$iValue $iUnit  = $oValue $oUnit",
                fontSize = 20.sp,
                fontFamily = FontFamily.Monospace,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF2038A4)
            )
        }
    }
}