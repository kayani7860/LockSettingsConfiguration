package com.test.locksettingsconfiguration.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import android.widget.TextView
import androidx.navigation.findNavController
import com.test.locksettingsconfiguration.R

data class DataModel(
    val parameterName: String,
    val primaryValue: String,
    val secondaryValue: String
)

class ParameterAdapter(context: Context, private var dataList: List<DataModel>) :
    ArrayAdapter<DataModel>(context, R.layout.parameter_item, dataList) {

    private var filteredDataList: List<DataModel> = dataList

    override fun getView(position: Int, view: View?, parent: ViewGroup): View {
        var convertView = view
        val viewHolder: ViewHolder

        if (convertView == null) {
            convertView =
                LayoutInflater.from(context).inflate(R.layout.parameter_item, parent, false)
            viewHolder = ViewHolder()
            viewHolder.parameterName = convertView.findViewById(R.id.tv_parameter_name)
            viewHolder.primaryValue = convertView.findViewById(R.id.tv_primary_value)
            viewHolder.secondaryValue = convertView.findViewById(R.id.tv_secondary_value)
            viewHolder.parameterItem = convertView.findViewById(R.id.parameter_item_view)

            convertView.tag = viewHolder
        } else {
            viewHolder = convertView.tag as ViewHolder
        }


        val currentItem = filteredDataList[position]
        viewHolder.parameterName?.text = currentItem.parameterName
        viewHolder.primaryValue?.text = currentItem.primaryValue
        viewHolder.secondaryValue?.text = currentItem.secondaryValue


        viewHolder.parameterItem?.setOnClickListener {
            view?.findNavController()?.navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
        return convertView!!
    }

    override fun getCount(): Int {
        return filteredDataList.size
    }

    override fun getItem(position: Int): DataModel {
        return filteredDataList[position]
    }

    fun filter(query: String) {
        filteredDataList = if (query.isEmpty()) {
            dataList
        } else {
            dataList.filter {
                it.parameterName.contains(query, true) ||
                        it.primaryValue.contains(query, true) ||
                        it.secondaryValue.contains(query, true)
            }
        }
        notifyDataSetChanged()
    }


    private class ViewHolder {
        var parameterName: TextView? = null
        var primaryValue: TextView? = null
        var secondaryValue: TextView? = null
        var parameterItem: LinearLayout? = null
    }
}
