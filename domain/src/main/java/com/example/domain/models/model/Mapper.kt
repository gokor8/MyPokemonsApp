package com.example.domain.models.model

interface Mapper<SRC, DST> {
    fun cast(src: SRC): DST
}