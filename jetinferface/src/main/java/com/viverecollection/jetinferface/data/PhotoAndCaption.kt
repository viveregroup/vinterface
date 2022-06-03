package com.viverecollection.jetinferface.data

data class PhotoAndCaption(
    val id: Int,
    val file: FileInput,
    val caption: String
) {
    companion object {
        val init = PhotoAndCaption(0, FileInput.init, "")
    }
}