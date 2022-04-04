package sealedAttachments

data class Video(
    val id: Int = 0,
    val ownerId: Int = 0,
    val title: String = "title",
    val description: String?,
    val duration: Int = 0
)