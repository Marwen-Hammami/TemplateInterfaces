package marwen.gameid.templateinterfaces

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import marwen.gameid.templateinterfaces.ui.AjouterALaListeUI
import marwen.gameid.templateinterfaces.viewmodel.MainViewModel


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel: MainViewModel by viewModels()
        setContent {
            AjouterALaListeUI(viewModel)
        }
    }
}