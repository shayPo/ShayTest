package polak.shay.applicaster.model

data class Entry(
    val author: Author,
    val content: Content,
    val id: String,
    val link: Link,
    val media_group: List<MediaGroup>,
    val screen_type: Any,
    val summary: String,
    val title: String,
    val type: Type,
    val ui_tag: Any,
    val updated: String
)