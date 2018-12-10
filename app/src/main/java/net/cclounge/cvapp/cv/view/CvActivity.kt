package net.cclounge.cvapp.cv.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.android.AndroidInjection
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.subscribeBy
import kotlinx.android.synthetic.main.activity_cv.*
import net.cclounge.cvapp.R
import net.cclounge.cvapp.cv.presenter.CvPresenter
import javax.inject.Inject

class CvActivity : AppCompatActivity() {

    @Inject
    lateinit var presenter: CvPresenter

    @Inject
    lateinit var adapter: CvAdapter

    private var subscription: Disposable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cv)

        cv_recycler_view.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        cv_recycler_view.adapter = adapter

        // trigger data loading and rendering
        subscribe(presenter.loadData())
    }

    private fun subscribe(observable: Observable<CvScreenViewModel>) {
        // if there is already an ongoing operation, we discard it
        subscription?.dispose()

        subscription = observable
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onNext = { render(it) }
            )
    }

    private fun render(cvScreenViewModel: CvScreenViewModel) {
        cv_recycler_view.visibility = View.GONE
        loading_view.visibility = View.GONE
        error_view.visibility = View.GONE
        when(cvScreenViewModel.state) {
            CvScreenState.LOADING -> {
                loading_view.visibility = View.VISIBLE
            }
            CvScreenState.SUCCESS -> {
                cv_recycler_view.visibility = View.VISIBLE
                adapter.updateItems(cvScreenViewModel.cvItems ?: emptyList())
            }
            CvScreenState.ERROR -> {
                error_view.visibility = View.VISIBLE
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        subscription?.dispose()
    }

}
