package com.test.miprimeraapp.data

import com.test.miprimeraapp.data.remote.RemoteDataSource
import com.test.miprimeraapp.model.MemberModel

object MembersRepository {

    val remoteDataSource = RemoteDataSource()

   suspend fun getAMember():MemberModel{
        //aqui deberiamos checkear una base de datos local o cache
        //pero a modo ilustrativo solo accedemos al repositorio remoto
        return remoteDataSource.getAMember()
    }

    suspend fun getMembers(amount:Int =10):List<MemberModel>{
        return remoteDataSource.getMembers(amount)
    }
}