package polak.shay.applicaster.model

import android.widget.Filter
import android.widget.Filterable

open class Post {

    open val author: Author? = null
    open val entry: List<Entry>? = null
    open val id: String? = null
    open val link: Link? = null
    open val subtitle : String? = null
    open val title: String? = null
    open val type: Type? = null
    open val updated: String? = null


}
