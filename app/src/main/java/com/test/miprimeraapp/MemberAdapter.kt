package com.test.miprimeraapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView
import org.w3c.dom.Text

class MemberAdapter(val context:Context,val listMembers:List<MemberModel>) :BaseAdapter() {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var view = convertView
        if (view == null) {
            val mInflater = LayoutInflater.from(context)
             view = mInflater.inflate(
                R.layout.member_item_layout,
                parent, false
            )
        }

        val item = getItem(position) as MemberModel
        val nameView =  view?.findViewById<TextView>(R.id.name)
        val surnameView =  view?.findViewById<TextView>(R.id.surname)
        nameView?.text = item.nombre
        surnameView?.text = item.apellido
        return view!!
    }

    override fun getItem(position: Int): Any {
       return  listMembers[position]
    }

    override fun getItemId(position: Int): Long {
        return  listMembers[position].id
    }

    override fun getCount(): Int = listMembers.size
}