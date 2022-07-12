package com.edgars.newsapp.ui.detailsfragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import coil.load
import coil.transform.RoundedCornersTransformation
import com.edgars.newsapp.databinding.FragmentDetailsBinding

class DetailsFragment : Fragment() {

    companion object {
        const val TITLE = "title"
        const val DESCRIPTION = "description"
        const val IMAGE_URL = "image_url"
        const val SOURCE = "source"
        const val DATE = "date"
        const val URL = "url"
    }

//    private val viewModel: DetailsViewModel by viewModels()
    private lateinit var binding: FragmentDetailsBinding

    private lateinit var title: String
    private lateinit var description: String
    private lateinit var imageUrl: String
    private lateinit var source: String
    private lateinit var date: String
    private lateinit var url: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            title = it.getString(TITLE).toString()
            description = it.getString(DESCRIPTION).toString()
            imageUrl = it.getString(IMAGE_URL).toString()
            source = it.getString(SOURCE).toString()
            date = it.getString(DATE).toString().take(10)
            url = it.getString(URL).toString()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailsBinding.inflate(inflater,container,false)

        bindData()

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.detailsButton.setOnClickListener {
            it.findNavController().navigate(
                DetailsFragmentDirections.actionDetailsFragmentToDetailsWebViewFragment(
                    url = url
                )
            )
        }
    }

    private fun bindData() {
        binding.detailsTitle.text = title
        binding.detailsDescription.text = description
        binding.detailsImage.load(imageUrl) {
            transformations(RoundedCornersTransformation(10f))
            crossfade(true)
            crossfade(500)
        }
        binding.detailsSource.text = source
        binding.detailsDate.text = date
    }
}