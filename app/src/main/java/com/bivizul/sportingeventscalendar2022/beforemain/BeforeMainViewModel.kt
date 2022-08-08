package com.bivizul.sportingeventscalendar2022.beforemain

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bivizul.sportingeventscalendar2022.data.Repository
import com.bivizul.sportingeventscalendar2022.model.Langag
import com.bivizul.sportingeventscalendar2022.model.Posil
import com.bivizul.sportingeventscalendar2022.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BeforeMainViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    private val _posil = MutableStateFlow<Resource<Posil>>(Resource.Success(Posil("")))
    val posil: StateFlow<Resource<Posil>> = _posil.asStateFlow()

    fun getPosil(langag: Langag) {
        viewModelScope.launch(Dispatchers.IO) {
            _posil.emit(Resource.Loading())
            val response = repository.getPosil(langag)
            if (response.isSuccessful) {
                response.body()?.let {
                    _posil.emit(Resource.Success(it))
                }
            } else {
                _posil.emit(Resource.Error(response.message()))
            }
        }
    }
}