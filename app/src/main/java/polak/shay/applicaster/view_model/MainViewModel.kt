package polak.shay.applicaster.view_model

import android.text.Editable
import android.text.TextWatcher
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import polak.shay.applicaster.data.Data
import polak.shay.applicaster.data.PostType
import polak.shay.applicaster.model.Entry
import polak.shay.applicaster.model.Post
import java.util.*

interface MainViewModelApi
{
    fun getLivePostData() : LiveData<List<Entry>>

    fun load()

    fun filter(filter : String)
}

class MainViewModel : Observer , ViewModel(),MainViewModelApi, TextWatcher
{
    private var loading = 0
    private val mFilteredPosts by lazy{ mutableListOf<Entry>()}
    private val mAllPosts by lazy{ mutableListOf<Entry>()}
    private var mPostData : MutableLiveData<List<Entry>> = MutableLiveData()

    override fun load()
    {
        mPostData.value = mutableListOf()
        loading+=2
        Data.getInstance().loadPost(PostType.ALL, this)
    }

    override fun update(o: Observable?, arg: Any?) {
        loading--
        if(loading == 0) { o?.deleteObserver(this)}
        val data : List<Entry> = (arg as Post).entry!!
        mAllPosts.addAll(data)
        mPostData.postValue(mAllPosts)
    }

    override fun filter(filter : String){
        mFilteredPosts.clear()
        mFilteredPosts.addAll(mAllPosts.filter { entry -> entry.title.contains(filter) })
        mPostData.postValue(mFilteredPosts)
    }

    override fun getLivePostData() : LiveData<List<Entry>> {
        return mPostData
    }

    override fun afterTextChanged(s: Editable?) {
        filter(s.toString())
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

    }
}
