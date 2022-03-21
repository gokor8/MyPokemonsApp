package com.example.mypokemons.data.database.room.converters

import androidx.room.TypeConverter
import com.example.mypokemons.data.database.room.models.Images

class ImagesConverter {
    @TypeConverter
    fun fromImages(images: Images): String {
        return "${images.small},${images.large}"
    }

    @TypeConverter
    fun toImages(data: String): Images {
        return Images(data.substringBefore(','), data.substringAfter(','))
    }
}