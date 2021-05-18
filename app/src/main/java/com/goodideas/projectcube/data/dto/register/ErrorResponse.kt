package com.goodideas.projectcube.data.dto.register
import com.google.gson.annotations.SerializedName

data class ErrorResponse(
    @SerializedName("errors")
    val errors: Errors = Errors(),
    @SerializedName("message")
    val message: String = "" // The given data was invalid.
) {
    data class Errors(
        @SerializedName("email")
        val email: List<String> = listOf()
    )
}