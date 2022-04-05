package attachments

abstract class Attachment (open val type: String)

data class AudioAttachment (
    override val type: String = "audio",
    val audio: Audio
): Attachment(type)

data class FileAttachment (
    override val type: String = "file",
    val file: File
): Attachment(type)

data class LinkAttachment (
    override val type: String = "link",
    val link: Link
): Attachment(type)

data class PhotoAttachment (
    override val type: String = "photo",
    val photo: Photo
): Attachment(type)

data class VideoAttachment (
    override val type: String = "video",
    val video: Video
): Attachment(type)