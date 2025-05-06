package marwen.gameid.templateinterfaces

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.activity.viewModels


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel: MainViewModel by viewModels()
        setContent {
            AjouterALaListeUI(viewModel)
        }
    }
}

@Composable
fun AjouterALaListeUI(viewModel: MainViewModel) {
    var texteSaisi by remember { mutableStateOf("") }
    var erreur by remember { mutableStateOf("") }

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)) {

        // Champ de saisie
        OutlinedTextField(
            value = texteSaisi,
            onValueChange = {
                texteSaisi = it
                erreur = "" // Réinitialiser le message d'erreur dès que l'utilisateur tape
            },
            label = { Text("Entrez un élément") },
            modifier = Modifier.fillMaxWidth(),
            isError = erreur.isNotEmpty()
        )

        if (erreur.isNotEmpty()) {
            Text(text = erreur, color = Color.Red)
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Bouton Ajouter
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

        // Affichage de la liste
        LazyColumn {
            items(viewModel.listeElements) { element ->
                Text(text = element, modifier = Modifier.padding(4.dp))
            }
        }
    }
}