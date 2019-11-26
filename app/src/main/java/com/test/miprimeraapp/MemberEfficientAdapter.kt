package com.test.miprimeraapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.member_item_layout.view.*

class MemberEfficientAdapter(val eliminarElementoListener: (id:Long) -> Unit) : RecyclerView.Adapter<MemberEfficientAdapter.MemberModelViewholder>() {

    var data: List<MemberModel> = emptyList()
        set(newList) {
            val calculateDiff = DiffUtil.calculateDiff(MemberModelDiffCallback(field, newList))
            calculateDiff.dispatchUpdatesTo(this)
            field = newList
        }

    override fun getItemCount(): Int = data.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemberModelViewholder {
        return MemberModelViewholder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.member_item_layout, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MemberModelViewholder, position: Int) {
        holder.bind(data[position])
    }

    inner class MemberModelViewholder(override val containerView: View) :
        RecyclerView.ViewHolder(containerView),
        LayoutContainer {
        fun bind(item: MemberModel) = with(itemView) {
            name.text = item.nombre
           surname.text = item.nombre
            Glide.with(context).load(item.photo).into(photoImage)
            eliminarButon.setOnClickListener {
                eliminarElementoListener(item.id)
            }
        }
    }
}

class MemberModelDiffCallback(val oldList: List<MemberModel>, val newList: List<MemberModel>) :
    DiffUtil.Callback() {
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val old = oldList[oldItemPosition]
        val new = newList[newItemPosition]

        return old.id == new.id
    }

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }
}