package net.cclounge.cvapp.cv.presenter

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import net.cclounge.cvapp.cv.CvModel
import net.cclounge.cvapp.cv.model.Cv
import net.cclounge.cvapp.cv.view.CvScreenState
import net.cclounge.cvapp.cv.view.CvScreenViewModel
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule

class CvPresenterImplTest {

    @get:Rule
    val rule : MockitoRule = MockitoJUnit.rule()

    @Mock
    lateinit var model: CvModel
    @Mock
    lateinit var converter: CvScreenConverter

    lateinit var presenter: CvPresenterImpl

    @Before
    fun setup() {
        presenter = CvPresenterImpl(model, converter, Schedulers.trampoline())
    }

    @Test
    fun `If no errors occur, the loading view model, and the converted cv view model are sent to the view`() {
        val mockCvScreenViewModel = mock<CvScreenViewModel>()
        val mockCv = mock<Cv>()
        whenever(model.getCv()).thenReturn(Single.just(mockCv))
        whenever(converter.convert(mockCv)).thenReturn(mockCvScreenViewModel)
        val observable = presenter.loadData()
        observable.test().assertSubscribed()
            .assertValues(CvScreenViewModel(null, CvScreenState.LOADING), mockCvScreenViewModel)
            .assertComplete()
            .assertNoErrors()
    }

    @Test
    fun `If an error occurs, the loading view model, and the error view model are sent to the view`() {
        whenever(model.getCv()).thenReturn(Single.error(Exception("test exception")))
        val observable = presenter.loadData()
        observable.test().assertSubscribed()
            .assertValues(CvScreenViewModel(null, CvScreenState.LOADING), CvScreenViewModel(null, CvScreenState.ERROR))
            .assertComplete()
            .assertNoErrors()
    }
}
