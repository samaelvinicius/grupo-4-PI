package com.projetointegrador.grupo04.data.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class PageInfoModel (
    @SerializedName("total_results")
    val total: Int,
    @SerializedName("total_pages")
    val paginas: Int
)
