package com.example.mypokemons.ui.fragments

import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.models.model.BasePokemonModel
import com.example.mypokemons.ui.adapters.MainRecycleViewAdapter

abstract class BasePokemonCardsFragment(
    fragmentId: Int
) : Fragment(fragmentId) {

    protected fun setRecyclerViewLogic(
        binding: BaseBindings,
        liveData: MutableLiveData<List<BasePokemonModel>>
    ) {
        val rvAdapter = MainRecycleViewAdapter(parentFragmentManager)

        binding.recyclerView.run {
            layoutManager = GridLayoutManager(this.context, 2)
            adapter = rvAdapter
        }

        liveData.observe(this as LifecycleOwner) {

            binding.pbWait.visibility = View.INVISIBLE

            if (it == null || it.isEmpty())
                binding.tvNotify.visibility = View.VISIBLE
            else {
                binding.tvNotify.visibility = View.INVISIBLE

                rvAdapter.refreshAdapter(it)
            }
        }
    }

    data class BaseBindings(
        val recyclerView: RecyclerView,
        val pbWait: ProgressBar,
        val tvNotify: TextView,
    ) {}
}