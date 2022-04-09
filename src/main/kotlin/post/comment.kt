package post

import attachments.*

data class Comment(
    val id: Int = 0,
    val postId: Int = 0,
    val fromId: Int = 0,
    val date: Int = 0,
    val text: String,
    val replyToUser: Int? = null,
    val replyToComment: Int? = null,
    val attachments: Array<Attachment>? = null
)
