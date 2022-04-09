package post

import attachments.*

data class Post(
    val id: Int = 0,
    val ownerId: Int = 0,
    val fromId: Int = 0,
    val createdBy: Int? = null,
    val date: Int = 0,
    val text: String? = null,
    val replyOwnerId: Int? = null,
    val replyPostId: Int? = null,
    val friendsOnly: Boolean = false,
    val postType: String? = null,
    val attachments: Array<Attachment>? = null,
    val signerId: Int? = 0,
    val canPin: Boolean = false,
    val markedAsAds: Boolean = false,
    val isFavorite: Boolean = false,
    val postponedId: Int? = 0
)