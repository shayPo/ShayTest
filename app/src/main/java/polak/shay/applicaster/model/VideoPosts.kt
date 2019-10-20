package polak.shay.applicaster.model

data class VideoPosts(
    override val author: Author?,
    val content: Content,
    override val entry: List<Entry>?,
    override val id: String?,
    override val link: Link?,
    val media_group: List<MediaGroup>,
    val screen_type: Any,
    val summary: String,
    override val title: String?,
    override val type: Type?,
    val ui_tag: Any,
    override val updated: String?
) : Post()