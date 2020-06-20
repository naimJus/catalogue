package com.example.catalogue.component.list

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.appcompat.widget.SearchView
import androidx.core.view.get
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import com.example.catalogue.R
import com.example.catalogue.component.list.ListFragmentDirections.toDetailFragment
import com.example.catalogue.component.list.ListFragmentDirections.toInfoDialog
import com.example.catalogue.data.EventObserver
import com.example.catalogue.databinding.FragmentListBinding
import com.example.catalogue.util.ViewModelFactory
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_list.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class ListFragment : DaggerFragment(), SearchView.OnQueryTextListener {

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

            (toolbar.menu.findItem(R.id.item_search).actionView as SearchView).run {
                this.setOnQueryTextListener(this@ListFragment)
                this.setIconifiedByDefault(false)
                this.setOnCloseListener {
                    this@ListFragment.viewModel.query(null)
                    false
                }
            }
            toolbar.setOnMenuItemClickListener {
                if (it.itemId == R.id.item_search) {
                    this@ListFragment.viewModel.searchActionViewState()
                    it.expandActionView()
                    true
                } else
                    false
            }
        }.root

        postponeEnterTransition(200L, TimeUnit.MILLISECONDS)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.navigateToDetailData.observe(viewLifecycleOwner, EventObserver {
            //collapse search view
            collapseSearchView()
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

        viewModel.searchExpandedLiveData.observe(viewLifecycleOwner, EventObserver {
            activity?.onBackPressedDispatcher?.addCallback(
                owner = viewLifecycleOwner,
                onBackPressed = {
                    isEnabled = false
                    if (toolbar.menu[0].isActionViewExpanded) {
                        collapseSearchView()
                    } else {
                        activity?.onBackPressedDispatcher?.onBackPressed()
                    }
                })
        })

    }

    private fun collapseSearchView() {
        toolbar.menu.getItem(0).collapseActionView()
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        viewModel.query(query)
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        return false
    }
}