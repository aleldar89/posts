package attachments

data class Audio(
    val id: Int = 0,
    val ownerId: Int = 0,
    val title: String = "title",
    val albumId: Int?,
    val duration: Int = 0
)
