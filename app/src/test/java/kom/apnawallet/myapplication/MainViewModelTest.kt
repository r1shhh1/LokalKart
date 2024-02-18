package kom.apnawallet.myapplication

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import junit.framework.TestCase.assertNull
import kom.apnawallet.myapplication.model.Product
import kom.apnawallet.myapplication.repository.ProductRepositoryInterface
import kom.apnawallet.myapplication.util.LiveDataTestUtil
import kom.apnawallet.myapplication.viewModel.MainViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import java.io.IOException

@RunWith(JUnit4::class)
class MainViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var productRepository: ProductRepositoryInterface

    private lateinit var mainViewModel: MainViewModel

    @Before
    fun setUp() {
        // Initialize Mockito annotations and the ViewModel
        MockitoAnnotations.openMocks(this)
        mainViewModel = MainViewModel(productRepository)

        // Set the main coroutine dispatcher to a test dispatcher
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @Test
    fun `fetchProducts success`() = runBlockingTest {
        val mockProductList = listOf(
            Product(
                id = 23,
                title = "Orange Essence Food Flavour",
                description = "Specifications of Orange Essence Food Flavour For Cakes and Baking Food Item",
                price = 14,
                discountPercentage = 8.04F,
                rating = 4.85F,
                stock = 26,
                brand = "Baking Food Items",
                category = "groceries",
                thumbnail = "https://cdn.dummyjson.com/product-images/23/thumbnail.jpg",
                images = listOf(
                    "https://cdn.dummyjson.com/product-images/23/1.jpg",
                    "https://cdn.dummyjson.com/product-images/23/2.jpg",
                    "https://cdn.dummyjson.com/product-images/23/3.jpg",
                    "https://cdn.dummyjson.com/product-images/23/4.jpg",
                    "https://cdn.dummyjson.com/product-images/23/thumbnail.jpg"
                )
            ),
            Product(
                id = 24,
                title = "cereals muesli fruit nuts",
                description = "original fauji cereal muesli 250gm box pack original fauji cereals muesli fruit nuts flakes breakfast cereal break fast faujicereals cerels cerel foji fouji",
                price = 46,
                discountPercentage = 16.8F,
                rating = 4.94F,
                stock = 113,
                brand = "fauji",
                category = "groceries",
                thumbnail = "https://cdn.dummyjson.com/product-images/24/thumbnail.jpg",
                images = listOf(
                    "https://cdn.dummyjson.com/product-images/24/1.jpg",
                    "https://cdn.dummyjson.com/product-images/24/2.jpg",
                    "https://cdn.dummyjson.com/product-images/24/3.jpg",
                    "https://cdn.dummyjson.com/product-images/24/4.jpg",
                    "https://cdn.dummyjson.com/product-images/24/thumbnail.jpg"
                )
            )
        )

        `when`(productRepository.fetchProducts()).thenReturn(mockProductList)

        mainViewModel.fetchProducts()

        assertEquals(false, LiveDataTestUtil.getValue(mainViewModel.isLoading))
        assertEquals(mockProductList, LiveDataTestUtil.getValue(mainViewModel.products))
        assertNotNull(LiveDataTestUtil.getValue(mainViewModel.lastRefreshTime))
    }

    @Test
    fun `fetchProducts failure`() = runBlockingTest {
        // Mock exception to be thrown by the repository
        val mockException = IOException("Network error")
        `when`(productRepository.fetchProducts()).thenAnswer { throw mockException }

        mainViewModel.fetchProducts()

        assertEquals(false, LiveDataTestUtil.getValue(mainViewModel.isLoading))
        assertNull(LiveDataTestUtil.getValue(mainViewModel.products))
        assertNull(LiveDataTestUtil.getValue(mainViewModel.lastRefreshTime))
        // Handle error, log, or notify user (add assertions based on your actual implementation)
    }
}
