package com.viverecollection.vinterface.data

import com.viverecollection.jetinferface.data.BaseOption
/**
 * Created by Annas Surdyanto on 30/05/22.
 *
 */
data class OptionSample(
    val uniqueId: Int,
    val title: String,
    val subtitle: String,
    val image: String
) : BaseOption(uniqueId.toString(), title, subtitle, image) {
    companion object {
        val init = OptionSample(0, "", "", "")

        fun optionSampleList(): List<OptionSample> {
            val list = mutableListOf<OptionSample>()
            for (i in 1..10) {
                list.add(OptionSample(i, "Sample $i", "Description $i", ""))
            }
            return list
        }
    }
}

fun List<OptionSample>.filterItems(key: String): List<OptionSample> {
    return this.filter {
        it.name.lowercase().contains(key.lowercase()) || it.description.lowercase()
            .contains(key.lowercase())
    }
}

fun List<OptionSample>.deleteItem(item: OptionSample, onFinish: (List<OptionSample>) -> Unit) {
    val list = this.toMutableList()
    val index = list.indexOf(item)
    list.removeAt(index = index)
    onFinish(list)
}