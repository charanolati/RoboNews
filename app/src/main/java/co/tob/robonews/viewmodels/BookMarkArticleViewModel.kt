package com.test.newsapp.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import co.tob.robonews.db.DatabaseService
import co.tob.robonews.model.Article
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class BookMarkArticleViewModel @Inject constructor(
    private val databaseService: DatabaseService,
    private val compositeDisposable: CompositeDisposable
) : ViewModel() {

    private val _bookmarkArticlesLiveData = MutableLiveData<List<Article>>()
    val bookmarkArticlesLiveData: LiveData<List<Article>>
        get() = _bookmarkArticlesLiveData

    init {
        getBookMarkedArticles()
    }

    fun getBookMarkedArticles() {
        compositeDisposable.add(
            databaseService.getArticleDao().getBookMarkedArticles(false)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({
                    _bookmarkArticlesLiveData.value = it
                }, {
                    Log.e("value inserted", "article.title")
                })
        )
    }

}
