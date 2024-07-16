package com.akexorcist.uitest.issue.scrollto.data

sealed class SampleData {
    data class Item(
        val label: String
    ) : SampleData()

    data class Details(
        val title: String,
        val items: List<String>
    ) : SampleData()
}
