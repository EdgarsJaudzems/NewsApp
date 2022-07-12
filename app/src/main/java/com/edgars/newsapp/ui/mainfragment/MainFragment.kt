package com.edgars.newsapp.ui.mainfragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.edgars.newsapp.data.network.ScreenState
import com.edgars.newsapp.data.adapter.RecyclerViewAdapter
import com.edgars.newsapp.data.vo.Article
import com.edgars.newsapp.databinding.FragmentMainBinding
import com.google.android.material.snackbar.Snackbar

class MainFragment : Fragment() {

    private val viewModel: MainFragmentViewModel by activityViewModels()
    private lateinit var binding: FragmentMainBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater, container, false)

        // Allows Data Binding to Observe LiveData with the lifecycle of this Fragment
        binding.lifecycleOwner = this

        // Giving the binding access to the MainViewModel
        binding.viewModel = viewModel

        // Load observables
        loadObservables()

        return binding.root
    }

    private fun loadObservables() {
        viewModel.articles.observe(viewLifecycleOwner) {
            processArticleResponse(it)
        }
    }

    private fun processArticleResponse(state: ScreenState<List<Article>>) {
        when(state) {
            is ScreenState.Loading -> {
                binding.progressBar.visibility = View.VISIBLE
            }
            is ScreenState.Success -> {
                binding.progressBar.visibility = View.GONE

                if(state.data != null) {
                    val adapter = RecyclerViewAdapter(state.data)
                    binding.newsRecyclerView?.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                    binding.newsRecyclerView?.adapter = adapter
                }
            }
            is ScreenState.Error -> {
                binding.progressBar.visibility = View.GONE
                val view = binding.progressBar.rootView
                Snackbar.make(view, state.message!!, Snackbar.LENGTH_LONG).show()
            }
        }
    }
}