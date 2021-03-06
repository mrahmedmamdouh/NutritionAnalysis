package com.example.nutritionanalysis

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.nutritionanalysis.data.model.NutritionResponse
import com.example.nutritionanalysis.data.model.RequestPayload
import com.example.nutritionanalysis.data.repository.MainRepository
import com.example.nutritionanalysis.response.ApiInterface
import com.example.nutritionanalysis.ui.viewmodel.MainViewModel
import com.example.nutritionanalysis.utils.Resource
import com.google.gson.Gson
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.timeout
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnit


@RunWith(JUnit4::class)
class MainViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    var coroutineRule = CoroutineTestRule()

    @Rule
    @JvmField
    val rule = MockitoJUnit.rule()!!

    @Mock
    lateinit var api: ApiInterface
    private lateinit var mainRepository: MainRepository
    private lateinit var mainViewModel: MainViewModel
    private lateinit var nutritionObserver: Observer<Resource<NutritionResponse>>

    @ExperimentalCoroutinesApi
    private val testDispatcher = TestCoroutineDispatcher()

    @ExperimentalCoroutinesApi
    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        Dispatchers.setMain(testDispatcher)
        mainRepository = MainRepository(api)
        mainViewModel = MainViewModel(
            mainRepository,
            testDispatcher
        )
        nutritionObserver = mock()
    }

    @ExperimentalCoroutinesApi
    @After
    fun after() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    @ExperimentalCoroutinesApi
    @Test
    fun testApi() = testDispatcher.runBlockingTest {
        val requestList: ArrayList<String> = ArrayList()
        requestList.add("1 cup of tea")
        val nutrition = RequestPayload(requestList)

        val response =
            "{\"uri\":\"http://www.edamam.com/ontologies/edamam.owl#recipe_dd6ff1309b5e4f27aafe236a3fc5ad34\",\"yield\":2.0,\"calories\":2,\"totalWeight\":237.0,\"dietLabels\":[\"LOW_FAT\",\"LOW_SODIUM\"],\"healthLabels\":[\"FAT_FREE\",\"LOW_FAT_ABS\",\"SUGAR_CONSCIOUS\",\"LOW_SUGAR\",\"LOW_POTASSIUM\",\"KIDNEY_FRIENDLY\",\"VEGAN\",\"VEGETARIAN\",\"PESCATARIAN\",\"PALEO\",\"SPECIFIC_CARBS\",\"DAIRY_FREE\",\"GLUTEN_FREE\",\"WHEAT_FREE\",\"EGG_FREE\",\"MILK_FREE\",\"PEANUT_FREE\",\"TREE_NUT_FREE\",\"SOY_FREE\",\"FISH_FREE\",\"SHELLFISH_FREE\",\"PORK_FREE\",\"RED_MEAT_FREE\",\"CRUSTACEAN_FREE\",\"CELERY_FREE\",\"MUSTARD_FREE\",\"SESAME_FREE\",\"LUPINE_FREE\",\"MOLLUSK_FREE\",\"ALCOHOL_FREE\",\"NO_OIL_ADDED\",\"NO_SUGAR_ADDED\",\"SULPHITE_FREE\",\"KOSHER\"],\"cautions\":[\"SULFITES\"],\"totalNutrients\":{\"ENERC_KCAL\":{\"label\":\"Energy\",\"quantity\":2.37,\"unit\":\"kcal\"},\"FAT\":{\"label\":\"Fat\",\"quantity\":0.0,\"unit\":\"g\"},\"FASAT\":{\"label\":\"Saturated\",\"quantity\":0.00474,\"unit\":\"g\"},\"FAMS\":{\"label\":\"Monounsaturated\",\"quantity\":0.00237,\"unit\":\"g\"},\"FAPU\":{\"label\":\"Polyunsaturated\",\"quantity\":0.00948,\"unit\":\"g\"},\"CHOCDF\":{\"label\":\"Carbs\",\"quantity\":0.711,\"unit\":\"g\"},\"FIBTG\":{\"label\":\"Fiber\",\"quantity\":0.0,\"unit\":\"g\"},\"SUGAR\":{\"label\":\"Sugars\",\"quantity\":0.0,\"unit\":\"g\"},\"PROCNT\":{\"label\":\"Protein\",\"quantity\":0.0,\"unit\":\"g\"},\"CHOLE\":{\"label\":\"Cholesterol\",\"quantity\":0.0,\"unit\":\"mg\"},\"NA\":{\"label\":\"Sodium\",\"quantity\":7.11,\"unit\":\"mg\"},\"CA\":{\"label\":\"Calcium\",\"quantity\":0.0,\"unit\":\"mg\"},\"MG\":{\"label\":\"Magnesium\",\"quantity\":7.11,\"unit\":\"mg\"},\"K\":{\"label\":\"Potassium\",\"quantity\":87.69,\"unit\":\"mg\"},\"FE\":{\"label\":\"Iron\",\"quantity\":0.047400000000000005,\"unit\":\"mg\"},\"ZN\":{\"label\":\"Zinc\",\"quantity\":0.047400000000000005,\"unit\":\"mg\"},\"P\":{\"label\":\"Phosphorus\",\"quantity\":2.37,\"unit\":\"mg\"},\"VITA_RAE\":{\"label\":\"Vitamin A\",\"quantity\":0.0,\"unit\":\"??g\"},\"VITC\":{\"label\":\"Vitamin C\",\"quantity\":0.0,\"unit\":\"mg\"},\"THIA\":{\"label\":\"Thiamin (B1)\",\"quantity\":0.0,\"unit\":\"mg\"},\"RIBF\":{\"label\":\"Riboflavin (B2)\",\"quantity\":0.03318,\"unit\":\"mg\"},\"NIA\":{\"label\":\"Niacin (B3)\",\"quantity\":0.0,\"unit\":\"mg\"},\"VITB6A\":{\"label\":\"Vitamin B6\",\"quantity\":0.0,\"unit\":\"mg\"},\"FOLDFE\":{\"label\":\"Folate equivalent (total)\",\"quantity\":11.850000000000001,\"unit\":\"??g\"},\"FOLFD\":{\"label\":\"Folate (food)\",\"quantity\":11.850000000000001,\"unit\":\"??g\"},\"FOLAC\":{\"label\":\"Folic acid\",\"quantity\":0.0,\"unit\":\"??g\"},\"VITB12\":{\"label\":\"Vitamin B12\",\"quantity\":0.0,\"unit\":\"??g\"},\"VITD\":{\"label\":\"Vitamin D\",\"quantity\":0.0,\"unit\":\"??g\"},\"TOCPHA\":{\"label\":\"Vitamin E\",\"quantity\":0.0,\"unit\":\"mg\"},\"VITK1\":{\"label\":\"Vitamin K\",\"quantity\":0.0,\"unit\":\"??g\"},\"WATER\":{\"label\":\"Water\",\"quantity\":236.28900000000002,\"unit\":\"g\"}},\"totalDaily\":{\"ENERC_KCAL\":{\"label\":\"Energy\",\"quantity\":0.1185,\"unit\":\"%\"},\"FAT\":{\"label\":\"Fat\",\"quantity\":0.0,\"unit\":\"%\"},\"FASAT\":{\"label\":\"Saturated\",\"quantity\":0.023700000000000002,\"unit\":\"%\"},\"CHOCDF\":{\"label\":\"Carbs\",\"quantity\":0.237,\"unit\":\"%\"},\"FIBTG\":{\"label\":\"Fiber\",\"quantity\":0.0,\"unit\":\"%\"},\"PROCNT\":{\"label\":\"Protein\",\"quantity\":0.0,\"unit\":\"%\"},\"CHOLE\":{\"label\":\"Cholesterol\",\"quantity\":0.0,\"unit\":\"%\"},\"NA\":{\"label\":\"Sodium\",\"quantity\":0.29625,\"unit\":\"%\"},\"CA\":{\"label\":\"Calcium\",\"quantity\":0.0,\"unit\":\"%\"},\"MG\":{\"label\":\"Magnesium\",\"quantity\":1.6928571428571428,\"unit\":\"%\"},\"K\":{\"label\":\"Potassium\",\"quantity\":1.8657446808510638,\"unit\":\"%\"},\"FE\":{\"label\":\"Iron\",\"quantity\":0.26333333333333336,\"unit\":\"%\"},\"ZN\":{\"label\":\"Zinc\",\"quantity\":0.4309090909090909,\"unit\":\"%\"},\"P\":{\"label\":\"Phosphorus\",\"quantity\":0.3385714285714286,\"unit\":\"%\"},\"VITA_RAE\":{\"label\":\"Vitamin A\",\"quantity\":0.0,\"unit\":\"%\"},\"VITC\":{\"label\":\"Vitamin C\",\"quantity\":0.0,\"unit\":\"%\"},\"THIA\":{\"label\":\"Thiamin (B1)\",\"quantity\":0.0,\"unit\":\"%\"},\"RIBF\":{\"label\":\"Riboflavin (B2)\",\"quantity\":2.5523076923076924,\"unit\":\"%\"},\"NIA\":{\"label\":\"Niacin (B3)\",\"quantity\":0.0,\"unit\":\"%\"},\"VITB6A\":{\"label\":\"Vitamin B6\",\"quantity\":0.0,\"unit\":\"%\"},\"FOLDFE\":{\"label\":\"Folate equivalent (total)\",\"quantity\":2.9625000000000004,\"unit\":\"%\"},\"VITB12\":{\"label\":\"Vitamin B12\",\"quantity\":0.0,\"unit\":\"%\"},\"VITD\":{\"label\":\"Vitamin D\",\"quantity\":0.0,\"unit\":\"%\"},\"TOCPHA\":{\"label\":\"Vitamin E\",\"quantity\":0.0,\"unit\":\"%\"},\"VITK1\":{\"label\":\"Vitamin K\",\"quantity\":0.0,\"unit\":\"%\"}},\"ingredients\":[{\"text\":\"1 cup of tea\",\"parsed\":[{\"quantity\":1.0,\"measure\":\"cup\",\"foodMatch\":\"tea\",\"food\":\"black tea\",\"foodId\":\"food_b8aoe5qbgar9fbbcvdj8ea7ekske\",\"weight\":237.0,\"retainedWeight\":237.0,\"nutrients\":{\"RIBF\":{\"label\":\"Riboflavin\",\"quantity\":0.03318,\"unit\":\"mg\"},\"VITD\":{\"label\":\"Vitamin D\",\"quantity\":0.0,\"unit\":\"IU\"},\"THIA\":{\"label\":\"Thiamin\",\"quantity\":0.0,\"unit\":\"mg\"},\"FAPU\":{\"label\":\"Fatty acids, total polyunsaturated\",\"quantity\":0.00948,\"unit\":\"g\"},\"NIA\":{\"label\":\"Niacin\",\"quantity\":0.0,\"unit\":\"mg\"},\"ENERC_KCAL\":{\"label\":\"Energy\",\"quantity\":2.37,\"unit\":\"kcal\"},\"FASAT\":{\"label\":\"Fatty acids, total saturated\",\"quantity\":0.00474,\"unit\":\"g\"},\"VITA_RAE\":{\"label\":\"Vitamin A, RAE\",\"quantity\":0.0,\"unit\":\"??g\"},\"VITC\":{\"label\":\"Vitamin C, total ascorbic acid\",\"quantity\":0.0,\"unit\":\"mg\"},\"PROCNT\":{\"label\":\"Protein\",\"quantity\":0.0,\"unit\":\"g\"},\"TOCPHA\":{\"label\":\"Vitamin E (alpha-tocopherol)\",\"quantity\":0.0,\"unit\":\"mg\"},\"CHOLE\":{\"label\":\"Cholesterol\",\"quantity\":0.0,\"unit\":\"mg\"},\"FAMS\":{\"label\":\"Fatty acids, total monounsaturated\",\"quantity\":0.00237,\"unit\":\"g\"},\"CHOCDF\":{\"label\":\"Carbohydrate, by difference\",\"quantity\":0.711,\"unit\":\"g\"},\"FAT\":{\"label\":\"Total lipid (fat)\",\"quantity\":0.0,\"unit\":\"g\"},\"VITB6A\":{\"label\":\"Vitamin B-6\",\"quantity\":0.0,\"unit\":\"mg\"},\"VITB12\":{\"label\":\"Vitamin B-12\",\"quantity\":0.0,\"unit\":\"??g\"},\"FIBTG\":{\"label\":\"Fiber, total dietary\",\"quantity\":0.0,\"unit\":\"g\"},\"WATER\":{\"label\":\"Water\",\"quantity\":236.28900000000002,\"unit\":\"g\"},\"K\":{\"label\":\"Potassium, K\",\"quantity\":87.69,\"unit\":\"mg\"},\"P\":{\"label\":\"Phosphorus, P\",\"quantity\":2.37,\"unit\":\"mg\"},\"NA\":{\"label\":\"Sodium, Na\",\"quantity\":7.11,\"unit\":\"mg\"},\"ZN\":{\"label\":\"Zinc, Zn\",\"quantity\":0.047400000000000005,\"unit\":\"mg\"},\"SUGAR\":{\"label\":\"Sugars, total\",\"quantity\":0.0,\"unit\":\"g\"},\"CA\":{\"label\":\"Calcium, Ca\",\"quantity\":0.0,\"unit\":\"mg\"},\"MG\":{\"label\":\"Magnesium, Mg\",\"quantity\":7.11,\"unit\":\"mg\"},\"FE\":{\"label\":\"Iron, Fe\",\"quantity\":0.047400000000000005,\"unit\":\"mg\"},\"VITK1\":{\"label\":\"Vitamin K (phylloquinone)\",\"quantity\":0.0,\"unit\":\"??g\"},\"FOLFD\":{\"label\":\"Folate, food\",\"quantity\":11.85,\"unit\":\"??g\"},\"FOLAC\":{\"label\":\"Folic acid\",\"quantity\":0.0,\"unit\":\"??g\"},\"FOLDFE\":{\"label\":\"Folate, DFE\",\"quantity\":11.85,\"unit\":\"??g\"}},\"measureURI\":\"http://www.edamam.com/ontologies/edamam.owl#Measure_cup\",\"status\":\"OK\"}]}]}"
        val gson = Gson()
        val nutritionResponse = gson.fromJson(response, NutritionResponse::class.java)


        Mockito.`when`(api.analyzeNutrition(BuildConfig.APP_ID, BuildConfig.APP_KEY, nutrition))
            .thenReturn(nutritionResponse)

        mainViewModel.repositoriesLiveData.observeForever(nutritionObserver)
        mainViewModel.fetchProducts(nutrition)
        verify(nutritionObserver, timeout(50)).onChanged(Resource.Success(nutritionResponse))
    }

}