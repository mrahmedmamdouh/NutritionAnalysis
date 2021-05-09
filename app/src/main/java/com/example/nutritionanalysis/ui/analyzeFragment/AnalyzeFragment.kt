package com.example.nutritionanalysis.ui.analyzeFragment

import android.annotation.SuppressLint
import android.content.res.ColorStateList
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import com.example.nutritionanalysis.R
import com.example.nutritionanalysis.data.model.RequestPayload
import com.example.nutritionanalysis.databinding.FragmentAnalyzeBinding
import com.example.nutritionanalysis.ui.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*

@AndroidEntryPoint
class AnalyzeFragment : Fragment(R.layout.fragment_analyze) {

    private lateinit var binding: FragmentAnalyzeBinding
    private val viewmodel by activityViewModels<MainViewModel>()

    private val job = SupervisorJob()
    private val ioScope = CoroutineScope(Dispatchers.IO + job)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAnalyzeBinding.bind(view)

        binding.apply {

            ingredientText.addTextChangedListener(object : TextWatcher {
                @SuppressLint("ResourceAsColor")
                override fun afterTextChanged(p0: Editable?) {
                    if (!p0.isNullOrEmpty()) {
                        analyzeBttn.backgroundTintList = ColorStateList.valueOf(
                            resources.getColor(
                                android.R.color.holo_green_light,
                                resources.newTheme()
                            )
                        )
                        analyzeBttn.isEnabled = true
                    } else {
                        analyzeBttn.backgroundTintList = ColorStateList.valueOf(
                            resources.getColor(
                                android.R.color.darker_gray,
                                resources.newTheme()
                            )
                        )
                        analyzeBttn.isEnabled = false
                    }
                }

                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            })

            analyzeBttn.setOnClickListener {

                errorMessage.text = ""
                val ingredients: ArrayList<String> = ArrayList()
                val items: String = ingredientText.text.toString()
                val subStrings =
                    items.split("\n").toTypedArray()
                for (s in subStrings) {
                    ingredients.add(s)
                }
                val requestPayload = RequestPayload(ingredients)
                viewmodel.fetchProducts(requestPayload)
                progressBar.visibility = View.VISIBLE
            }
            viewmodel.repositoriesLiveData.observe(viewLifecycleOwner, Observer {
                if (it.data != null) {
                    progressBar.visibility = View.INVISIBLE
                    ingredientText.text?.clear()
                    val bundle = Bundle()
                    bundle.putParcelable("ingredients", it.data)
                    Navigation.findNavController(view)
                        .navigate(R.id.action_analyzeFragment_to_listFragment, bundle)
                } else if (it.error != null) {
                    if (it.error.javaClass.name == "java.net.UnknownHostException") {
                        errorMessage.text = getString(R.string.error_message_not_connected)
                    } else {
                        errorMessage.text = it.error.message
                    }
                    progressBar.visibility = View.INVISIBLE
                }
            })
        }

    }

}