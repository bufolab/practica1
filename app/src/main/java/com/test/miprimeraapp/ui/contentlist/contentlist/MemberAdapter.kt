package com.test.miprimeraapp.ui.contentlist.contentlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.test.miprimeraapp.R
import com.test.miprimeraapp.model.PostModel
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.member_item_layout.view.*
import kotlin.system.measureNanoTime

class MemberEfficientAdapter(val eliminarListener:(id:Long)->Unit) : RecyclerView.Adapter<MemberEfficientAdapter.MemberModelViewholder>() {

    var data: List<PostModel> = emptyList()
        set(newList) {
            val calculateDiff = DiffUtil.calculateDiff(
                MemberModelDiffCallback(
                    field,
                    newList
                )
            )
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
        fun bind(item: PostModel) = with(itemView) {
            title.text = item.title
            body.text = item.body
            eliminarButon.setOnClickListener {
                eliminarListener(item.id)
            }
        }
    }
}

class MemberModelDiffCallback(val oldList: List<PostModel>, val newList: List<PostModel>) :
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