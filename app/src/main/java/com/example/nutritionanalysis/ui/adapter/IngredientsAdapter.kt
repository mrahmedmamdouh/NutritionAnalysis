package com.example.nutritionanalysis.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.nutritionanalysis.data.model.Ingredient
import com.example.nutritionanalysis.data.model.Parsed
import com.example.nutritionanalysis.databinding.AddHeaderToListBinding
import com.example.nutritionanalysis.databinding.ItemListBinding
import com.example.nutritionanalysis.ui.viewmodel.MainViewModel

class IngredientsAdapter(
    private val ingredients: ArrayList<Ingredient>,
    private val viewModel: MainViewModel
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val TYPE_HEADER = 0
    private val TYPE_ITEM = 1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        when (viewType) {
            TYPE_ITEM -> {
                val binding =
                    ItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                return VH(binding)
            }
            TYPE_HEADER -> {
                val binding =
                    AddHeaderToListBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                return HeaderVH(binding)
            }
        }
        return VH(null)
    }

    override fun getItemCount() = ingredients.size + 1

    override fun getItemViewType(position: Int): Int {
        if (position == 0) {
            return TYPE_HEADER
        }
        return TYPE_ITEM
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is VH) {
            val itemViewHolder: VH = holder
            val currentItem = ingredients[position - 1].parsed
            itemViewHolder.bind(currentItem[0])

        } else if (holder is HeaderVH) {
            val headerViewHolder: HeaderVH = holder
            headerViewHolder.bind()
        }
    }

    inner class VH(private var binding: ItemListBinding?) :
        RecyclerView.ViewHolder(binding!!.root) {
        fun bind(item: Parsed) {
            binding?.apply {
                ingr = item
                viewmodel = viewModel
            }
        }
    }

    inner class HeaderVH(binding: AddHeaderToListBinding?) :
        RecyclerView.ViewHolder(binding!!.root) {
        fun bind() {
        }
    }
}