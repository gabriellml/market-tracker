import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.ListAdapter
import br.com.alura.markettracker.model.GetFunctions
import br.com.alura.markettracker.model.Pair
import br.com.alura.markettracker.model.dao.CurrencyDao
import br.com.alura.markettracker.ui.adapter.CurrencyViewerAdapter
import kotlinx.coroutines.launch
import okhttp3.internal.notifyAll
import java.util.Timer
import java.util.TimerTask

class CurrencyViewModel : ViewModel() {
    private lateinit var functions: GetFunctions
    internal lateinit var dao: CurrencyDao
    private val _currentDate = MutableLiveData<String>()
    val currentDate: LiveData<String> get() = _currentDate
    private val _pairList = MutableLiveData<List<Pair>>()
    val pairList: LiveData<List<Pair>> get() = _pairList
    private val _searchQuery = MutableLiveData<String>()
    val searchQuery: LiveData<String> get() = _searchQuery
    val timer = Timer()

    fun init() {
        functions = GetFunctions()
        dao = CurrencyDao()
        timer.scheduleAtFixedRate(object : TimerTask() {
            override fun run() {
                _currentDate.postValue(functions.updateDate())
            }
        }, 0, 1000)
    }

    fun fetchData(type: String) {
        viewModelScope.launch {
            functions.callingAPI(dao, type)                 // fazendo a requisição dos dados e criando as listas
            val dataList = dao.getPairList(type)
            _pairList.postValue(dataList)
        }
    }

    fun performSearch(query : String, type : String) {
        viewModelScope.launch {
            val searchResults = dao.searchPairsByParity(query, type)
            _pairList.postValue(searchResults)
        }
    }

    override fun onCleared() {
        super.onCleared()
        timer.cancel()
    }
}
