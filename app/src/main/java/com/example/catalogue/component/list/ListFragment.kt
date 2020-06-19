package com.example.catalogue.component.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import com.example.catalogue.component.list.ListFragmentDirections.toDetailFragment
import com.example.catalogue.data.EventObserver
import com.example.catalogue.databinding.FragmentListBinding
import com.example.catalogue.util.ViewModelFactory
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class ListFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private val viewModel: ListViewModel by viewModels { viewModelFactory }
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = FragmentListBinding.inflate(inflater, container, false).apply {
        viewModel = this@ListFragment.viewModel
        lifecycleOwner = viewLifecycleOwner
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.subscribe()

        viewModel.navigateToDetailData.observe(viewLifecycleOwner, EventObserver {
            val fragmentNavigatorExtras = FragmentNavigatorExtras(it)
            findNavController().navigate(toDetailFragment(it.second), fragmentNavigatorExtras)
        })
    }
}