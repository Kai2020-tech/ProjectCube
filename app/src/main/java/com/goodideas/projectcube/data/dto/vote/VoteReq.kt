package com.goodideas.projectcube.data.dto.vote


enum class VoteType(val typeName: String) {
    Post("Post"),
    Comment("Comment")
}

enum class VoteState(val stateName: String) {
    like("like"),
    dislike("dislike")
}
