package com.borlanddev.notes.controller

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.borlanddev.notes.R

class DetailsFragment: Fragment() {







    // аналог setContentView, настраивает и возвращает готовую верстку экрана
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_details, container, false)

        return view

    }


}