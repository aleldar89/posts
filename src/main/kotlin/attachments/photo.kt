package attachments

data class Photo(
    val id: Int = 0,
    val albumId: Int = 0,
    val ownerId: Int = 0,
    val text: String?,
    val height: Int = 0,
    val width: Int = 0
)
