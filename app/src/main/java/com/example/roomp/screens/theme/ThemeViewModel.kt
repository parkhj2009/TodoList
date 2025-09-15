import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.roomp.data.AppDatabase
import com.example.roomp.data.ThemeEntity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ThemeViewModel(appDatabase: AppDatabase) : ViewModel() {
    private val dao = appDatabase.themedao()
    private val _baseColor = MutableStateFlow(Color(0xFF4F9F9C)) // 기본값
    val baseColor: StateFlow<Color> = _baseColor

    init {
        viewModelScope.launch {
            dao.getTheme()?.let { entity ->
                _baseColor.value = Color(entity.baseColor)
            }
        }
    }

    fun saveTheme(color: Color) {
        _baseColor.value = color
        viewModelScope.launch {
            dao.insertTheme(ThemeEntity(id = 0, baseColor = color.toArgb()))
        }
    }
}

