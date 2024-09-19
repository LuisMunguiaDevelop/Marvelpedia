package org.plux.marvelpedia.features.hero_list

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.plux.marvelpedia.features.hero_list.data.use_cases.get_hero_list.GetHeroListUC
import org.plux.marvelpedia.features.hero_list.model.Hero
import org.plux.marvelpedia.features.hero_list.model.toDomain
import org.plux.marvelpedia.network.ApiResponse

class HeroListViewModel(
    private val getHeroListUC: GetHeroListUC
) : ViewModel() {
    var state by mutableStateOf(HeroListState())
        private set

    init {
        getList()
    }

    private fun getList() = viewModelScope.launch(Dispatchers.IO) {
        getHeroListUC().collectLatest { response ->
            when(response){
                is ApiResponse.Error -> {
                    setLoading(false)
                }
                is ApiResponse.Loading -> {
                    setLoading(true)
                }
                is ApiResponse.Success -> {
                    val heros: List<Hero> = response.data.data.results.map { it.toDomain() }

                    state = state.copy( heroList = heros)
                    setLoading(false)
                }
            }
        }
    }

    private fun setLoading(loading: Boolean){
        state = state.copy(isLoading = loading)
    }

}