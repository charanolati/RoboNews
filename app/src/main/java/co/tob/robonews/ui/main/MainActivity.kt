package co.tob.robonews.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import co.tob.robonews.MyApplication
import co.tob.robonews.R
import co.tob.robonews.adapter.MainAdapter
import co.tob.robonews.db.DatabaseService
import co.tob.robonews.di.component.DaggerActivityComponent
import co.tob.robonews.di.module.ActivityModule
import co.tob.robonews.model.Article
import co.tob.robonews.ui.BookMarkActivity
import co.tob.robonews.ui.NewsDetailActivity
import co.tob.robonews.ui.SearchActivity
import co.tob.robonews.ui.base.BaseActivity
import co.tob.robonews.viewmodels.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import javax.inject.Inject

class MainActivity : BaseActivity() {

    @Inject
    lateinit var viewModel: MainViewModel

    private lateinit var adapter: MainAdapter
    private var layoutManager: RecyclerView.LayoutManager? = null
    private var articles: List<Article> = ArrayList()

    @Inject
    lateinit var databaseService: DatabaseService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setSupportActionBar(toolbar)

        getDependencies()
        layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        recyclerView.itemAnimator = DefaultItemAnimator()
        recyclerView.isNestedScrollingEnabled = false
        observeLiveData()
    }

    private fun getDependencies() {
        DaggerActivityComponent
            .builder()
            .applicationComponent((application as MyApplication).applicationComponent)
            .activityModule(ActivityModule(this))
            .build()
            .inject(this)
    }

    override fun provideLayout() = R.layout.activity_main

    private fun observeLiveData() {

        viewModel.newsFailedLiveData.observe(this, {
            Toast.makeText(this, "failed", Toast.LENGTH_LONG).show()
        })

        viewModel.newsLiveData.observe(this, {
            articles = it!!
            adapter = MainAdapter(it!!, this@MainActivity)
            recyclerView.adapter = adapter
            adapter.notifyDataSetChanged()

            adapter.setOnItemClickListener(object : MainAdapter.OnItemClickListener {
                override fun onItemClick(view: View?, position: Int) {
                    val intent = Intent(this@MainActivity, NewsDetailActivity::class.java)
                    intent.putExtra("articleID", articles[position].articleId)
                    intent.putExtra("url", articles[position].url)
                    intent.putExtra("img", articles[position].urlToImage)
                    intent.putExtra("title", articles[position].title)
                    intent.putExtra("date", articles[position].publishedAt)
                    intent.putExtra("source", articles[position].title)
                    intent.putExtra("author", articles[position].author)
                    startActivity(intent)
                }
            })
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_search -> {
                startActivity(Intent(this@MainActivity, BookMarkActivity::class.java))
            }
            R.id.action_favorite -> {
                startActivity(Intent(this@MainActivity, SearchActivity::class.java))
            }
        }
        return super.onOptionsItemSelected(item)
    }

}
