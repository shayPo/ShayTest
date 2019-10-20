package polak.shay.applicaster

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.TextWatcher
import android.util.Log
import android.view.Menu
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import polak.shay.applicaster.view_model.MainViewModel
import polak.shay.applicaster.view_model.MainViewModelApi
import java.io.File
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import polak.shay.applicaster.VideoActivity.Companion.URL_DATA


class MainActivity : AppCompatActivity(), View.OnClickListener {

    private val mViewModel: MainViewModelApi by lazy { ViewModelProvider.NewInstanceFactory().create(MainViewModel::class.java) }
    private val mAdapter by lazy { PostAdapter(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        postList.layoutManager = LinearLayoutManager(applicationContext)
        postList.adapter = mAdapter
        search_bar.addTextChangedListener(mViewModel as TextWatcher)
        start()
    }

    private fun start() {
        mViewModel.getLivePostData().observe(this, Observer { data ->
            mAdapter.updateData(data)
        })
        mViewModel.load()
    }

    override fun onClick(v: View?) {
        if (v?.findViewById<ImageView>(R.id.play_button)?.visibility == View.GONE) {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(v?.tag.toString())))
        } else {
            val viewIntent = Intent(this, VideoActivity::class.java)
            viewIntent.putExtra(URL_DATA, v?.tag.toString())
            startActivity(viewIntent)
        }
    }
}
