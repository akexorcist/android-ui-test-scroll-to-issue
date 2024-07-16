package com.akexorcist.uitest.issue.scrollto.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.akexorcist.uitest.issue.scrollto.data.SampleData
import com.akexorcist.uitest.scrollto.databinding.ItemPrimarySectionItemBinding
import com.akexorcist.uitest.scrollto.databinding.ItemPrimarySectionRecyclerViewBinding

class PrimaryAdapter(
    private val onDetailButtonClickListener: (String) -> Unit,
    private val onItemButtonClickListener: (String) -> Unit,
) : RecyclerView.Adapter<ViewHolder>() {
    companion object {
        private const val TYPE_ITEM = 0
        private const val TYPE_DETAILS = 1
    }

    private var sampleData: List<SampleData> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return when (viewType) {
            TYPE_ITEM -> PrimaryItemViewHolder(
                binding = ItemPrimarySectionItemBinding.inflate(LayoutInflater.from(parent.context), parent, false),
                onClickListener = onItemButtonClickListener,
            )

            TYPE_DETAILS -> PrimaryRecyclerViewViewHolder(
                binding = ItemPrimarySectionRecyclerViewBinding.inflate(LayoutInflater.from(parent.context), parent, false),
                onClickListener = onDetailButtonClickListener,
            )

            else -> throw IllegalArgumentException("Unknown view type: $viewType")
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (sampleData[position]) {
            is SampleData.Item -> TYPE_ITEM
            is SampleData.Details -> TYPE_DETAILS
        }
    }

    override fun getItemCount(): Int = sampleData.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val type = sampleData.getOrNull(position)
        when {
            holder is PrimaryItemViewHolder && type is SampleData.Item ->
                holder.bind(label = type.label)

            holder is PrimaryRecyclerViewViewHolder && type is SampleData.Details ->
                holder.bind(title = type.title, items = type.items)
        }
    }

    fun setTypes(sampleData: List<SampleData>) {
        this.sampleData = sampleData
        notifyDataSetChanged()
    }
}

class PrimaryItemViewHolder(
    private val binding: ItemPrimarySectionItemBinding,
    private val onClickListener: (String) -> Unit,
) : ViewHolder(binding.root) {
    fun bind(label: String) {
        binding.textViewLabel.text = label
        binding.layoutItemContainer.setOnClickListener { onClickListener(label) }
    }
}

class PrimaryRecyclerViewViewHolder(
    private val binding: ItemPrimarySectionRecyclerViewBinding,
    private val onClickListener: (String) -> Unit,
) : ViewHolder(binding.root) {

    private val secondaryAdapter: SecondaryAdapter by lazy {
        SecondaryAdapter(
            onClickListener = onClickListener
        )
    }

    fun bind(title: String, items: List<String>) {
        binding.textViewTitle.text = title
        binding.recyclerViewSecondary.apply {
            layoutManager = LinearLayoutManager(binding.root.context, LinearLayoutManager.HORIZONTAL, false)
            adapter = secondaryAdapter
        }
        secondaryAdapter.setItems(items)
    }
}
