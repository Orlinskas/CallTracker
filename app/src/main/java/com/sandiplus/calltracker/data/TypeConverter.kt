package com.sandiplus.calltracker.data

import com.google.gson.Gson

class TypeConverter {

    private val gson = Gson()

//    @TypeConverter
//    fun realizationToJson(products: List<Realization>?): String? {
//        return if (products == null || products.isEmpty()) {
//            null
//        } else {
//            gson.toJson(products)
//        }
//    }
//
//    @TypeConverter
//    fun jsonToRealization(json: String?): List<Realization> {
//        return if (json.isNullOrEmpty()) {
//            listOf()
//        } else {
//            val type = object : TypeToken<List<Realization>>() {}.type
//            gson.fromJson(json, type)
//        }
//    }
}
