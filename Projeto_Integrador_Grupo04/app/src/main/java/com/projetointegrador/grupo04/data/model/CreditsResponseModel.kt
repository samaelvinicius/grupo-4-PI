package com.projetointegrador.grupo04.data.model

import androidx.annotation.Keep

@Keep
data class CreditsResponseModel<T> (
    val id: Int,
    val cast: List<T>,
    val crew: List<T>
)