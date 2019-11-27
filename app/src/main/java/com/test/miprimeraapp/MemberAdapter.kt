package com.test.miprimeraapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.member_item_layout.view.*
import org.w3c.dom.Text

class MemberAdapter(var listMembers:List<MemberModel>) :RecyclerView.Adapter<MemberViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemberViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.member_item_layout,
            parent, false)
        return MemberViewHolder(view)
    }

    override fun onBindViewHolder(holder: MemberViewHolder, position: Int) {
        val memberModel = listMembers[position]
        holder.name.text = memberModel.nombre
        holder.surname.text = memberModel.apellido
        Glide.with(holder.photo.context).load(memberModel.photo).into(holder.photo)
    }

    override fun getItemId(position: Int): Long {
        return  listMembers[position].id
    }

    override fun getItemCount(): Int = listMembers.size


}

class MemberViewHolder(view:View): RecyclerView.ViewHolder(view) {
   val name = view.name
   val surname = view.surname
    val photo = view.photoImage
}

