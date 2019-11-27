package com.test.miprimeraapp.data

import com.test.miprimeraapp.model.MemberModel

object DataRepository {

    var myList = listOf(
        MemberModel(1, "Maria", "Gomez"),
        MemberModel(2, "Joan", "Sala"),
        MemberModel(3, "Teresa", "Sala Gomez"),
        MemberModel(4, "Ramon", "Bou Marti"),
        MemberModel(5, "Pere", "Bou Sala"),
        MemberModel(6, "Laura", "Bou Sala")
    )

    fun getData():List<MemberModel>{
        return myList
    }

    fun removeItem(itemId:Long):List<MemberModel>{
        myList =  myList.filter { it.id != itemId }
        return myList
    }
}