package com.example.catalogue.component.list

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import com.example.catalogue.component.list.ListFragmentDirections.toDetailFragment
import com.example.catalogue.component.list.ListFragmentDirections.toInfoDialog
import com.example.catalogue.data.EventObserver
import com.example.catalogue.databinding.FragmentListBinding
import com.example.catalogue.util.ViewModelFactory
import dagger.android.support.DaggerFragment
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class ListFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private val viewModel: ListViewModel by viewModels { viewModelFactory }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        viewModel.subscribe()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = FragmentListBinding.inflate(inflater, container, false).apply {
            viewModel = this@ListFragment.viewModel
            lifecycleOwner = viewLifecycleOwner
        }.root

        postponeEnterTransition(200L, TimeUnit.MILLISECONDS)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.navigateToDetailData.observe(viewLifecycleOwner, EventObserver {
            //create shared element transition extra
            val fragmentNavigatorExtras = FragmentNavigatorExtras(it.first to it.second.id)
            // navigate to details fragment
            findNavController().navigate(toDetailFragment(it.second), fragmentNavigatorExtras)
        })

        viewModel.errorData.observe(viewLifecycleOwner, EventObserver {

            findNavController().navigate(toInfoDialog().apply {
                argButtonConfirmation = it.title
                argMessage = it.message
                argButtonConfirmation = it.buttonRes
            })
        })
    }
}