package com.example.catalogue.component.detail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import androidx.transition.TransitionInflater
import com.example.catalogue.R
import com.example.catalogue.data.EventObserver
import com.example.catalogue.databinding.FragmentDetailBinding
import com.example.catalogue.util.ViewModelFactory
import dagger.android.support.DaggerFragment
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class DetailFragment : DaggerFragment(), Toolbar.OnMenuItemClickListener {

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
            toolbar.inflateMenu(R.menu.menu_details)
            toolbar.setOnMenuItemClickListener(this@DetailFragment)
            toolbar.setupWithNavController(
                findNavController(),
                AppBarConfiguration(setOf(R.id.navigation_list_fragment))
            )
        }.root

        postponeEnterTransition(200L, TimeUnit.MILLISECONDS)
        sharedElementEnterTransition =
            TransitionInflater.from(context).inflateTransition(R.transition.view_shared_enter)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.errorData.observe(viewLifecycleOwner, EventObserver {
            findNavController().navigate(DetailFragmentDirections.toInfoDialog().apply {
                argButtonConfirmation = it.title
                argMessage = it.message
                argButtonConfirmation = it.buttonRes
            })
        })

        viewModel.businessPhoneCallLiveData.observe(viewLifecycleOwner, EventObserver {
            startActivity(Intent(Intent.ACTION_DIAL, Uri.parse("tel:${it}")))
        })

        viewModel.businessWebLiveData.observe(viewLifecycleOwner, EventObserver {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(it)))
        })
    }

    override fun onMenuItemClick(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.item_call -> {
                viewModel.callBusinessPhone()
                true
            }
            R.id.item_open_in_web -> {
                viewModel.openInWeb()
                true
            }
            else -> false
        }
    }

}