package com.example.domain.models.models

interface Mapper<SRC, DST> {
    fun cast(src: SRC): DST
}