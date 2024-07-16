package com.akexorcist.uitest.issue.scrollto.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.akexorcist.uitest.scrollto.databinding.ItemSecondarySectionItemBinding

class SecondaryAdapter(
    private val onClickListener: (String) -> Unit,
) : RecyclerView.Adapter<SecondaryItemViewHolder>() {
    private var items: List<String> = listOf()

    fun setItems(items: List<String>) {
        this.items = items
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        SecondaryItemViewHolder(ItemSecondarySectionItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: SecondaryItemViewHolder, position: Int) {
        items.getOrNull(position)?.let {
            holder.bind(
                item = it,
                onClickListener = onClickListener,
            )
        }
    }
}

class SecondaryItemViewHolder(
    private val binding: ItemSecondarySectionItemBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(
        item: String,
        onClickListener: (String) -> Unit,
    ) {
        binding.buttonItem.apply {
            text = item
            setOnClickListener { onClickListener(item) }
        }
    }
}
