package com.example.catalogue.component.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
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

}