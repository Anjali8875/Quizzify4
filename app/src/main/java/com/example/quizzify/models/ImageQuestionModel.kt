package com.example.quizzify.models

data class ImageQuestionModel (
    val enterQuestion:String?=null,
    val simage1:String?=null,
    val simage2:String?=null,
    val simage3:String?=null,
    val simage4:String?=null,
    val correctimgans:Int,
    val options:List<String>?=null
)

