package com.vocera.recyclerviewbug

import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup

class MainAdapter: RecyclerView.Adapter<MainViewHolder>() {

    private var model: List<MainModel> = emptyList()

    fun updateModel(newModel: List<MainModel>) {
        val oldModel = this.model
        this.model = newModel
        DiffUtil.calculateDiff(object : DiffUtil.Callback() {
            override fun getOldListSize(): Int = oldModel.size

            override fun getNewListSize(): Int = newModel.size

            override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                return oldModel[oldItemPosition].id == newModel[newItemPosition].id
            }

            override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                return oldModel[oldItemPosition] == newModel[newItemPosition]
            }

        }).dispatchUpdatesTo(this)
    }

    override fun getItemCount(): Int = model.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.activity_main_list_item, parent, false)
        return MainViewHolder(view)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.textView.text = "Item ${model[position].number}"
    }
}