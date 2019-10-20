package polak.shay.applicaster.model

data class LinkPosts(
    override val author: Author?,
    override val entry: List<Entry>?,
    override val id: String?,
    override val link: Link?,
    override val subtitle: String?,
    override val title: String?,
    override val type: Type?,
    override val updated: String?
) : Post()