package com.example.pagingexample.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.pagingexample.network.CharacterData
import com.example.pagingexample.network.RetroInstance
import com.example.pagingexample.network.RetroService
import com.example.pagingexample.paged.CharacterPagingSource
import kotlinx.coroutines.flow.Flow

class MainActivityViewModel: ViewModel() {

    lateinit var retroService: RetroService

    init {
        retroService = RetroInstance.getRetroInstance().create(RetroService::class.java)

    }

    fun getListData(): Flow<PagingData<CharacterData>>{
        return Pager(config = PagingConfig(pageSize = 20, maxSize = 200),
        pagingSourceFactory = {CharacterPagingSource(retroService)}).flow.cachedIn((viewModelScope))
    }
}