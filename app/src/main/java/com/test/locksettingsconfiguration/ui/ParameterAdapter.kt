package com.test.locksettingsconfiguration.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.test.locksettingsconfiguration.R
import com.test.locksettingsconfiguration.model.ParameterModel

class ParameterAdapter(
    private val context: Context,
    private val callback: (ParameterModel) -> Unit
) :
    ListAdapter<ParameterModel, ParameterAdapter.ViewHolder>(DataModelDiffCallback()) {

    var originalList: List<ParameterModel> = ArrayList()

    init {
        originalList = ArrayList()
    }

    fun filter(query: String?) {
        val filteredList = ArrayList<ParameterModel>()
        if (!query.isNullOrEmpty()) {
            for (item in originalList) {
                if (item.parameterName?.contains(query, ignoreCase = true) == true) {
                    filteredList.add(item)
                }
            }
            submitList(filteredList)
        } else {
            submitList(originalList)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.parameter_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.parameterName.text = item.parameterName
        holder.primaryValue.text = item.dataModel.default
        holder.secondaryValue.text = item.dataModel.default
        holder.parameterItem.setOnClickListener {
            callback.invoke(item)
        }

    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val parameterName: TextView = itemView.findViewById(R.id.tv_parameter_name)
        var primaryValue: TextView = itemView.findViewById(R.id.tv_primary_value)
        var secondaryValue: TextView = itemView.findViewById(R.id.tv_secondary_value)
        var parameterItem: LinearLayout = itemView.findViewById(R.id.parameter_item_view)
    }

    class DataModelDiffCallback : DiffUtil.ItemCallback<ParameterModel>() {
        override fun areItemsTheSame(oldItem: ParameterModel, newItem: ParameterModel): Boolean {
            return oldItem.parameterName == newItem.parameterName
        }

        override fun areContentsTheSame(oldItem: ParameterModel, newItem: ParameterModel): Boolean {
            return oldItem == newItem
        }
    }
}

