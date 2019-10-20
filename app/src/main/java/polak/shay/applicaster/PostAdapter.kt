package polak.shay.applicaster

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.post_item.view.*
import polak.shay.applicaster.model.Entry
import java.lang.ref.WeakReference

class PostAdapter(private val mClickListener: View.OnClickListener) : RecyclerView.Adapter<PostAdapter.Holder>() {

    private val mData by lazy { mutableListOf<Entry>() }

    fun updateData(data: List<Entry>) {
        mData.clear()
        mData.addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(
            mClickListener, LayoutInflater.from(parent.context)
                .inflate(R.layout.post_item, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.setup(mData[position])
    }

    class Holder(clickListener: View.OnClickListener, itemView: View) : RecyclerView.ViewHolder(itemView) {

        private var mView: View? = null
        private lateinit var mClickListener: WeakReference<View.OnClickListener>

        init {
            mView = itemView
            mClickListener = WeakReference(clickListener)
        }

        fun setup(data: Entry) {
            mView?.setOnClickListener(mClickListener.get())
            mView?.tag = data.link.href
            if (data.type.value == mView?.resources?.getString(R.string.video_type)) {
                mView?.play_button?.visibility = View.VISIBLE
                mView?.tag = data.content.src
            } else {
                mView?.tag = data.link.href
                mView?.play_button?.visibility = View.GONE
            }
            mView?.post_title?.text = data.title
            mView?.post_summery?.text = data.summary
            if (!data.media_group.isNullOrEmpty()) {
                val url = data.media_group[0].media_item[0].src
                if (url.isNotBlank()) {
                    Picasso.get().load(url).into(mView?.post_image)
                }
            }
        }
    }
}