package com.example.moviesapp.ui.view

import android.Manifest.permission.READ_EXTERNAL_STORAGE
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager.PERMISSION_GRANTED
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.moviesapp.R
import com.example.moviesapp.databinding.FragmentImagesBinding
import com.example.moviesapp.ui.viewmodel.ImagesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ImagesFragment : Fragment() {

    private var _binding: FragmentImagesBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ImagesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentImagesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObservers()
        setupClickListeners()
    }

    private fun setupObservers() {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.isSuccessfulApiCall.collect { isSuccessfulStateFlow ->
                isSuccessfulStateFlow?.let { isSuccessful ->
                    when (isSuccessful) {
                        true -> Toast.makeText(requireContext(), "Successfully uploaded image", Toast.LENGTH_SHORT).show()
                        false -> Toast.makeText(requireContext(), "Failed yo upload image: ${viewModel.apiCallResultMessage.value}", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    private fun setupClickListeners() {
        with(binding) {
            imageUploadButton.setOnClickListener(clickListener)
        }
    }

    private val clickListener = View.OnClickListener { view ->
        when (view.id) {
            R.id.imageUploadButton -> {
                viewModel.resetIsApiSuccessfulCall()
                openGallery()
            }
        }
    }

    private fun openGallery() {
        if (ContextCompat.checkSelfPermission(requireContext(), READ_EXTERNAL_STORAGE) != PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(requireActivity(), arrayOf(READ_EXTERNAL_STORAGE), 1)
        } else {
            val galleryIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startForGalleryResultLauncher.launch(galleryIntent)
        }
    }

    private var startForGalleryResultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val resultData: Intent? = result.data
                resultData?.data?.let { viewModel.uploadImage(it) }
            }
        }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
