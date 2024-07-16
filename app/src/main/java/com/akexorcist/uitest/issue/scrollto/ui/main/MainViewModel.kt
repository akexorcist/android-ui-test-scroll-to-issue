package com.akexorcist.uitest.issue.scrollto.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.akexorcist.uitest.issue.scrollto.data.SampleData
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val _sampleData = MutableLiveData<List<SampleData>>()
    val sampleData: LiveData<List<SampleData>> = _sampleData

    fun loadData() {
        viewModelScope.launch {
            // Fake delayed response from server
            delay(1000L)
            _sampleData.postValue(DefaultData.shuffled())
        }
    }
}

private val DefaultData = listOf(
    SampleData.Item("Item A"),
    SampleData.Details(
        title = "Menu",
        items = listOf(
            "Menu 1",
            "Menu 2",
            "Menu 3",
            "Menu 4",
            "Menu 5",
            "Menu 6",
            "Menu 7",
            "Menu 8",
        )
    ),
    SampleData.Item("Item B"),
    SampleData.Details(
        title = "Coupon",
        items = listOf(
            "Coupon 1",
            "Coupon 2",
            "Coupon 3",
            "Coupon 4",
        )
    ),
)
