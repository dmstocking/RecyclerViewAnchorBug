package com.vocera.recyclerviewbug

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.disposables.Disposables
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    val adapter = MainAdapter()

    var disposable: Disposable = Disposables.empty()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val recyclerView = findViewById<RecyclerView>(R.id.list)
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView.adapter = adapter
    }

    override fun onResume() {
        super.onResume()
        adapter.updateModel(listOf(
                MainModel(0, 1),
                MainModel(1, 2),
                MainModel(2, 3),
                MainModel(3, 4),
                MainModel(4, 5),
                MainModel(5, 6),
                MainModel(6, 7)
        ))

        disposable.dispose()
        val disposable = Observable.interval(5, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    adapter.updateModel(listOf(
                            // Move the top item down due to moving the bottom items above it
                            MainModel(5, 6),
                            MainModel(6, 7),
                            MainModel(0, 1),
                            MainModel(1, 2),
                            MainModel(2, 3),
                            MainModel(3, 4),
                            MainModel(4, 5)
                    ))
                }
    }

    override fun onPause() {
        super.onPause()
        disposable.dispose()
    }
}

