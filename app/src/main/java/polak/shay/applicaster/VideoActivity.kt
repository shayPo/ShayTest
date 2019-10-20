package polak.shay.applicaster

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.net.Uri
import android.widget.MediaController
import kotlinx.android.synthetic.main.activity_video.*
import java.net.URL


class VideoActivity : AppCompatActivity()
{



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video)
        val mc = MediaController(this)
        myVideoView.setMediaController(mc)
        val urlStream = intent.extras.getString(URL_DATA)
        runOnUiThread { myVideoView.setVideoURI(Uri.parse(urlStream)) }
    }

    companion object{
        val URL_DATA = "url_data"
    }
}