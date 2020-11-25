package com.projetointegrador.grupo04.data.model

import androidx.annotation.Keep

@Keep
data class ResponseModel<T> (
    val info: PageInfoModel,
    val results: List<T>
)