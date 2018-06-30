package me.portfolio.aungkhanthtoo.unsplash.activities

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.arch.paging.PagedList
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.squareup.picasso.OkHttp3Downloader
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import me.portfolio.aungkhanthtoo.unsplash.R
import me.portfolio.aungkhanthtoo.unsplash.R.id.rvPhotos
import me.portfolio.aungkhanthtoo.unsplash.R.id.toolbar
import me.portfolio.aungkhanthtoo.unsplash.adapters.PhotoPagedListAdapter
import me.portfolio.aungkhanthtoo.unsplash.data.vos.DataLoadState
import me.portfolio.aungkhanthtoo.unsplash.data.vos.Photo
import me.portfolio.aungkhanthtoo.unsplash.viewmodels.PhotoListViewModel

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val adapter = PhotoPagedListAdapter()
        rvPhotos.apply {
            layoutManager = StaggeredGridLayoutManager(resources.getInteger(R.integer.grid_span), StaggeredGridLayoutManager.VERTICAL)
            hasFixedSize()
            this.adapter = adapter
        }

        ViewModelProviders.of(this)
                .get(PhotoListViewModel::class.java)
                .also {
            it.photos.observe(this, Observer {
                adapter.submitList(it)
            })
            it.loadState.observe(this, Observer {
                if (it == DataLoadState.FAILURE)
                    Snackbar.make(rvPhotos, "Can't Connect To Network.", Snackbar.LENGTH_LONG).show()
            })
        }

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    companion object {
        const val TAG = "MainActivity"
    }
}
