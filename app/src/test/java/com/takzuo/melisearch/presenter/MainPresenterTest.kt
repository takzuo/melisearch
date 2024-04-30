import com.takzuo.melisearch.contract.MainContract
import com.takzuo.melisearch.model.RemoteResult
import com.takzuo.melisearch.model.Result
import com.takzuo.melisearch.model.ResultTest
import com.takzuo.melisearch.presenter.MainPresenter
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class MainPresenterTest {

    @Mock
    private lateinit var mockView: MainContract.View

    private lateinit var presenter: MainPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = MainPresenter(mockView)
    }

    @Test
    suspend fun searchProducts_success()  = runBlocking {
        // Mock de los datos de productos
        val products = RemoteResult(
            results = listOf(
                Result(title = "producto 1", price = 10000.0, thumbnail = "https://th.bing.com/th/id/OIP.wiJ3pFH0M0ayqz-1nOrfPAHaGE?rs=1&pid=ImgDetMain"),
                Result(title = "producto 2", price = 20000.0, thumbnail = "https://th.bing.com/th/id/OIP.wiJ3pFH0M0ayqz-1nOrfPAHaGE?rs=1&pid=ImgDetMain"),
                Result(title = "producto 3", price = 30000.0, thumbnail = "https://th.bing.com/th/id/OIP.wiJ3pFH0M0ayqz-1nOrfPAHaGE?rs=1&pid=ImgDetMain")
            )
        )

        // Mock de la llamada a la API exitosa
        `when`(presenter.service.listProductsMeli("query")).thenReturn(products)

        // Llama al método que quieres probar
        presenter.searchProducts("query")

        // Verifica que la vista muestre los productos correctamente
        // Aquí necesitas tener un método en la vista para verificar si se muestran los productos
        // Puedes usar Mockito para verificar si se llama a métodos específicos de la vista
         Mockito.verify(mockView).showProducts(products.results)
    }

    @Test
    suspend fun searchProducts_failure() {
        // Mock de la llamada a la API que falla
        `when`(presenter.service.listProductsMeli("query")).thenThrow(Exception("Error"))

        // Llama al método que quieres probar
        presenter.searchProducts("query")

        // Verifica que la vista muestre un mensaje de error correctamente

         Mockito.verify(mockView).showError("Error al buscar productos: Error")
    }
}
