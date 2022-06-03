package com.viverecollection.jetinferface.data

/**
 * Created by Annas Surdyanto on 24/05/22.
 *
 */
data class FileInput(
    val fileName: String,
    val path: String,
    val mimeType: String,
    val base64: String
) {
    val isImage: Boolean
        get() = mimeType == "application/jpg" || mimeType == "image/jpeg" || mimeType == "image/png"
    private val isPdf: Boolean get() = mimeType == "application/pdf"
    private val isWord: Boolean
        get() = mimeType == "application/msword"
                || mimeType == "application/vnd.openxmlformats-officedocument.wordprocessingml.document"

    private val isPpt: Boolean
        get() = mimeType == "application/vnd.ms-powerpoint"
                || mimeType == "application/vnd.openxmlformats-officedocument.presentationml.presentation"
    val isImageValid get() = isImage && base64.isNotEmpty()
    companion object{
        val init = FileInput("","","","")
    }
}

fun FileInput.asPhotoRequestBody(): String{
    val path = this.path
    return if (path.contains("http://") || path.contains("https://")) path
    else this.base64
}