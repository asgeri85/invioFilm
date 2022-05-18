package com.example.inviofilm.ui.detail

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.inviofilm.base.BaseFragment
import com.example.inviofilm.databinding.FragmentDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : BaseFragment<FragmentDetailBinding, DetailViewModel>(
    FragmentDetailBinding::inflate
) {

    private val args: DetailFragmentArgs by navArgs()
    override val viewModel: DetailViewModel by viewModels<DetailViewModel>()

    override fun createFinished() {
        binding.film = args.film
    }

    override fun observeEvents() {

    }

}