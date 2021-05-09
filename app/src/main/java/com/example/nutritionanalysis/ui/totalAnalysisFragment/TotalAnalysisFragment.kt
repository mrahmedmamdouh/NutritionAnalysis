package com.example.nutritionanalysis.ui.totalAnalysisFragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.nutritionanalysis.R
import com.example.nutritionanalysis.data.model.NutritionResponse
import com.example.nutritionanalysis.databinding.FragmentTotalAnalysisBinding
import com.example.nutritionanalysis.ui.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TotalAnalysisFragment : Fragment(R.layout.fragment_total_analysis) {

    private lateinit var binding: FragmentTotalAnalysisBinding
    private val viewModel by activityViewModels<MainViewModel>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentTotalAnalysisBinding.bind(view)
        val nutritionResponse: NutritionResponse = requireArguments().getParcelable("ingredients")!!


        binding.apply {
            viewmodel = viewModel
            nutrition = nutritionResponse
        }
    }
}