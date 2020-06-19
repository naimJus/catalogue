package com.example.catalogue.component.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.catalogue.R
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_detail.*

class DetailFragment : DaggerFragment() {

    private val navArgs: DetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        inflater.inflate(R.layout.fragment_list, container, false)

}