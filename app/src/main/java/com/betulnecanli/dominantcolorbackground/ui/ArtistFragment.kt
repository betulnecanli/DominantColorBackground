package com.betulnecanli.dominantcolorbackground.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.betulnecanli.dominantcolorbackground.R
import com.betulnecanli.dominantcolorbackground.adapter.ViewPagerAdapter
import com.betulnecanli.dominantcolorbackground.databinding.FragmentArtistBinding
import com.betulnecanli.dominantcolorbackground.util.Status
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ArtistFragment : Fragment(R.layout.fragment_artist) {

    private lateinit var binding : FragmentArtistBinding
    private lateinit var madapter : ViewPagerAdapter
    private val viewModel : ArtistViewModel by viewModels()



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_artist, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentArtistBinding.bind(view)

        binding.viewPager2.clipToPadding = false
        binding.viewPager2.setPadding(0, 0, 0, 0)
        madapter = ViewPagerAdapter()
        binding.viewPager2.adapter = madapter

        viewModel.list.observe(viewLifecycleOwner, Observer {
            when(it.status){
                Status.SUCCESS -> {
                    binding.progressBar.visibility = View.GONE
                    it.data.let {result ->

                        if (result != null) {
                            madapter.submitList(result)
                        }
                        else{
                            Snackbar.make(view, "Status = false",Snackbar.LENGTH_SHORT).show()
                        }

                    }
                }
                Status.LOADING -> {
                                binding.progressBar.visibility = View.VISIBLE
                }
                Status.ERROR -> {
                    binding.progressBar.visibility = View.GONE
                    Snackbar.make(view, "Something went wrong",Snackbar.LENGTH_SHORT).show()

                }

            }
        })
    }

}