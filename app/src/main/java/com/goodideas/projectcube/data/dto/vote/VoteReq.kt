package com.goodideas.projectcube.data.dto.vote


enum class VoteType(val typeName: String) {
    POST("Post"),
    COMMENT("Comment")
}

enum class VoteState(val stateName: String) {
    LIKE("like"),
    DISLIKE("dislike")
}
