package com.example.moviesapp.ui.util

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.moviesapp.databinding.DialogLoadingBinding

class LoadingDialogFragment : DialogFragment() {

    private var _binding: DialogLoadingBinding? = null
    private val binding get() = _binding!!

    companion object {
        const val TAG = "LOADING_DIALOG"
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = DialogLoadingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this.isCancelable = false
    }
}
