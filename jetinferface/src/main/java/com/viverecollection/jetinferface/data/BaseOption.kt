package com.viverecollection.jetinferface.data

open class BaseOption (
    val id: String,
    val name: String,
    val description: String,
    val imageUrl: String
){
    companion object{
        val init = BaseOption("","","","")
    }
}



fun List<BaseOption>.deleteItem(item: BaseOption, onFinish: (List<BaseOption>) -> Unit) {
    val list = this.toMutableList()
    val index = list.indexOf(item)
    list.removeAt(index = index)
    onFinish(list)
}