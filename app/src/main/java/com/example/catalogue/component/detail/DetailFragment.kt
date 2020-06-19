package com.example.catalogue.component.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.transition.TransitionInflater
import com.example.catalogue.R
import com.example.catalogue.data.EventObserver
import com.example.catalogue.databinding.FragmentDetailBinding
import com.example.catalogue.util.ViewModelFactory
import dagger.android.support.DaggerFragment
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class DetailFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val navArgs: DetailFragmentArgs by navArgs()
    private val viewModel: DetailsViewModel by viewModels { viewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.subscribe(navArgs.argBusiness)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = FragmentDetailBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = this@DetailFragment.viewLifecycleOwner
            viewModel = this@DetailFragment.viewModel
        }.root
        postponeEnterTransition(200L, TimeUnit.MILLISECONDS)
        sharedElementEnterTransition =
            TransitionInflater.from(context).inflateTransition(R.transition.view_shared_enter)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.errorData.observe(viewLifecycleOwner, EventObserver {
            findNavController().navigate(DetailFragmentDirections.toInfoDialog())
        })
    }
}