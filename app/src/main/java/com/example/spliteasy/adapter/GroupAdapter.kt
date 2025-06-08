package com.example.spliteasy.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.spliteasy.R
import com.example.spliteasy.data.model.Group


class GroupAdapter(
    private val groups: List<Group>,
    private val onClick: (Group) -> Unit
): Adapter<GroupAdapter.GroupViewHolder>() {

    // GroupViewHolder extends RecyclerViewHolder class
    // It holds references to views so that binder can easily set the values
    inner class GroupViewHolder(itemView: View): ViewHolder(itemView) {
        val groupName: TextView = itemView.findViewById(R.id.groupName)
        val groupDescription: TextView = itemView.findViewById(R.id.groupDescription)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GroupViewHolder {
        val inflatedItemView = LayoutInflater.from(parent.context).inflate(R.layout.item_group, parent, false)
        return GroupViewHolder(inflatedItemView) // this instance is created for each item and passed to binder
    }

    override fun onBindViewHolder(holder: GroupViewHolder, position: Int) {
        val group = groups[position]
        holder.groupName.text = group.name
        holder.groupDescription.text = group.description

        holder.itemView.setOnClickListener {
            onClick(group)
        }
    }

    override fun getItemCount(): Int {
        return groups.size
    }

}