package sealedAttachments

data class File(
    val id: Int = 0,
    val ownerId: Int = 0,
    val title: String = "title",
    val description: String?,
    val size: Long,
    val type: Int
)