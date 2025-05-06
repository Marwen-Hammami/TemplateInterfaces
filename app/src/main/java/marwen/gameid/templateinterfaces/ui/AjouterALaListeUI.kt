package marwen.gameid.templateinterfaces.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import marwen.gameid.templateinterfaces.viewmodel.MainViewModel

@Composable
fun AjouterALaListeUI(viewModel: MainViewModel) {
    var texteSaisi by remember { mutableStateOf("") }
    var erreur by remember { mutableStateOf("") }

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)) {

        OutlinedTextField(
            value = texteSaisi,
            onValueChange = {
                texteSaisi = it
                erreur = ""
            },
            label = { Text("Entrez un élément") },
            modifier = Modifier.fillMaxWidth(),
            isError = erreur.isNotEmpty()
        )

        if (erreur.isNotEmpty()) {
            Text(text = erreur, color = Color.Red)
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = {
                if (texteSaisi.isBlank() || texteSaisi.length < 10) {
                    erreur = "L’élément doit contenir au moins 10 caractères"
                } else {
                    viewModel.ajouterElement(texteSaisi)
                    texteSaisi = ""
                    erreur = ""
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Ajouter")
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {
            items(viewModel.listeElements) { element ->
                Text(text = element, modifier = Modifier.padding(4.dp))
            }
        }
    }
}
