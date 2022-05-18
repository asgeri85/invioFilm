package com.example.inviofilm.ui.home

import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.inviofilm.base.BaseFragment
import com.example.inviofilm.databinding.FragmentHomeBinding
import com.example.inviofilm.model.FilmResponseModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>(
    FragmentHomeBinding::inflate
) {

    private var film: FilmResponseModel? = null
    override val viewModel: HomeViewModel by viewModels<HomeViewModel>()

    override fun createFinished() {
        searchFilm()

        binding.cardFilm.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToDetailFragment(
                film!!))
        }

    }

    override fun observeEvents() {
        with(viewModel) {
            filmData.observe(viewLifecycleOwner) {
                it?.let {
                    binding.cardFilm.visibility = View.VISIBLE
                    binding.film = it
                    film = it
                }
            }

            loading.observe(viewLifecycleOwner) {
                with(binding) {
                    if (it) {
                        loadingView.visibility = View.VISIBLE
                    } else {
                        loadingView.visibility = View.GONE
                    }
                }
            }

            error.observe(viewLifecycleOwner) {
                with(binding) {
                    if (it) {
                        cardFilm.visibility = View.GONE
                        imageViewError.visibility = View.VISIBLE
                        Toast.makeText(context, "Film bulunamadı", Toast.LENGTH_LONG).show()
                    } else {
                        cardFilm.visibility = View.VISIBLE
                        imageViewError.visibility = View.INVISIBLE
                    }
                }

            }
        }
    }

    private fun searchFilm() {
        binding.searchView.setOnQueryTextListener(object :
            android.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                val filmName = p0.toString().trim()
                viewModel.getFilm(filmName)
                return true
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                return true
            }

        })
    }

}