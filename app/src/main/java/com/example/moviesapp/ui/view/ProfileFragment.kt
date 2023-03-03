package com.example.moviesapp.ui.view

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.example.moviesapp.databinding.FragmentProfileBinding
import com.example.moviesapp.di.util.MOVIE_API_IMAGES_BASE_URL
import com.example.moviesapp.di.util.MOVIE_API_IMAGES_W185_SIZE_PATH
import com.example.moviesapp.domain.model.MoviePerson
import com.example.moviesapp.domain.util.ApiResponse
import com.example.moviesapp.ui.viewmodel.ProfileViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ProfileViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObservers()
    }

    private fun setupObservers() {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.getMostPopularMoviePerson().collect {
                when (it) {
                    is ApiResponse.Success -> showModelInUI(it.data.first())
                    is ApiResponse.Failure -> {
                        Toast.makeText(requireContext(), "Failed call: ${it.error}", Toast.LENGTH_SHORT).show()
                        println(it.error)
                    }
                }
            }
        }
    }

    private suspend fun showModelInUI(moviePerson: MoviePerson) {
        val fullUri = Uri.parse(MOVIE_API_IMAGES_BASE_URL + MOVIE_API_IMAGES_W185_SIZE_PATH + moviePerson.pictureUrlPath)

        CoroutineScope(Dispatchers.Main).launch {
            Glide.with(requireContext())
                .load(fullUri)
                .into(binding.profileImageView)
        }.join()

        with(binding) {
            profileNameTextView.text = moviePerson.name
            roleTextView.text = moviePerson.role
            popularityTextView.text = moviePerson.popularity.toString()
            bestKnownForNameTextView.text = moviePerson.bestKnownFor
            bestKnownForDescriptionTextView.text = moviePerson.bestKnownForOverview
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
