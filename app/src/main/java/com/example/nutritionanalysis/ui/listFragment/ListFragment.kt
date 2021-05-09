package com.example.nutritionanalysis.ui.listFragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nutritionanalysis.R
import com.example.nutritionanalysis.data.model.NutritionResponse
import com.example.nutritionanalysis.databinding.FragmentListBinding
import com.example.nutritionanalysis.ui.adapter.IngredientsAdapter
import com.example.nutritionanalysis.ui.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListFragment : Fragment(R.layout.fragment_list) {

    private lateinit var binding: FragmentListBinding
    private val viewmodel by activityViewModels<MainViewModel>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentListBinding.bind(view)
        val nutritionResponse: NutritionResponse = requireArguments().getParcelable("ingredients")!!


        binding.apply {
            list.layoutManager = LinearLayoutManager(context)
            list.addItemDecoration(DividerItemDecoration(list.context, DividerItemDecoration.VERTICAL))
            val ingredientsAdapter = IngredientsAdapter(nutritionResponse.ingredients, viewmodel)
            list.adapter = ingredientsAdapter


            showTotalBttn.setOnClickListener {
                val bundle = Bundle()
                bundle.putParcelable("ingredients",nutritionResponse)
                Navigation.findNavController(view).navigate(R.id.action_listFragment_to_totalAnalysisFragment, bundle)
            }
        }
    }
}