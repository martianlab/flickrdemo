package com.martianlab.flickrdemo.remote.mapper

interface EntityMapper<in M, out E> {

    fun mapFromRemote(type: M): E

}