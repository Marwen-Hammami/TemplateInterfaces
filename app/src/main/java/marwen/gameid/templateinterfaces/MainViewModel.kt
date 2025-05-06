package marwen.gameid.templateinterfaces

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    val listeElements = mutableStateListOf<String>()

    fun ajouterElement(element: String) {
        listeElements.add(element)
    }
}
