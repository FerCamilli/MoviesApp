package com.example.moviesapp.ui.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviesapp.R
import com.example.moviesapp.databinding.FragmentMoviesBinding
import com.example.moviesapp.domain.model.Movie
import com.example.moviesapp.ui.adapter.MoviesAdapter
import com.example.moviesapp.ui.util.LoadingDialogFragment
import com.example.moviesapp.ui.viewmodel.MoviesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MoviesFragment : Fragment() {

    private var _binding: FragmentMoviesBinding? = null
    private val binding get() = _binding!!

    private val movieList: ArrayList<Movie> = arrayListOf()

    private val viewModel: MoviesViewModel by viewModels()

    private val moviesAdapter by lazy {
        MoviesAdapter(requireContext(), movieList)
    }

    private lateinit var loadingDialogFragment: LoadingDialogFragment

    companion object {
        const val INITIAL_POSITION = 0
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentMoviesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        setupObservers()
        setupClickListeners()
        viewModel.getMoviesLists()
    }

    private fun initViews() {
        viewModel.setLoadingAsTrue()
        binding.moviesFragmentRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = moviesAdapter
        }
    }

    private fun setupObservers() {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.isLoading.collect {
                when (it) {
                    true -> {
                        loadingDialogFragment = LoadingDialogFragment()
                        loadingDialogFragment.show(requireActivity().supportFragmentManager, LoadingDialogFragment.TAG)
                    }
                    false -> loadingDialogFragment.dismiss()
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.isSuccessfulApiCall.collect { isSuccessfulStateFlow ->
                isSuccessfulStateFlow?.let { isSuccessful ->
                    when (isSuccessful) {
                        true -> viewModel.popularMoviesList.value?.let { updateAdapterList(it) }
                        false -> showFailedStateUi()
                    }
                }
            }
        }
    }

    private fun showFailedStateUi() {
        Toast.makeText(requireContext(), "Failed call", Toast.LENGTH_SHORT).show()
    }

    private fun setupClickListeners() {
        with(binding) {
            chipPopular.setOnClickListener(clickListener)
            chipTopRated.setOnClickListener(clickListener)
            chipRecommended.setOnClickListener(clickListener)
        }
    }

    private val clickListener = View.OnClickListener { view ->
        when (view.id) {
            R.id.chipPopular -> viewModel.popularMoviesList.value?.let { updateAdapterList(it) }
            R.id.chipTopRated -> viewModel.topRatedMoviesList.value?.let { updateAdapterList(it) }
            R.id.chipRecommended -> viewModel.recommendedMoviesList.value?.let { updateAdapterList(it) }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun updateAdapterList(newMovieList: List<Movie>) {
        movieList.clear()
        movieList.addAll(newMovieList)
        moviesAdapter.notifyDataSetChanged()
        binding.moviesFragmentRecyclerView.scrollToPosition(INITIAL_POSITION)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
