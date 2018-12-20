package com.devtee.awesomecalculator.feature.dialogpicker

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.devtee.awesomecalculator.R
import com.devtee.awesomecalculator.databinding.ItemDialogListItemBinding

class DialogPickerListAdapter(private val onItemSelectedListener: (PickerItem) -> Unit) :
    RecyclerView.Adapter<DialogPickerListAdapter.ItemPickerViewHolder>() {

    var items: List<PickerItem> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    init {
        setHasStableIds(true)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ItemPickerViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemPickerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_dialog_list_item, parent, false)
        return ItemPickerViewHolder(view, onItemSelectedListener)
    }

    inner class ItemPickerViewHolder(
        itemView: View,
        onItemSelectedListener: (PickerItem) -> Unit
    ) : RecyclerView.ViewHolder(itemView) {

        val binding = DataBindingUtil.bind<ItemDialogListItemBinding>(itemView)!!

        init {
            binding.onClickListener = View.OnClickListener { onItemSelectedListener.invoke(binding.item!!) }
        }

        fun bind(item: PickerItem) {
            binding.item = item
        }
    }
}