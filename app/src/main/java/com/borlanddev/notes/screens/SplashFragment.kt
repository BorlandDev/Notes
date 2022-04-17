package com.borlanddev.notes.screens

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import com.borlanddev.notes.R
import com.borlanddev.notes.controller.NoteListFragment

class SplashFragment: Fragment(R.layout.fragment_splash) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        view.postDelayed( {

            // возвращает хост 
            findNavController().navigate(

                R.id.action_splashFragment_to_noteListFragment,
                bundleOf(NoteListFragment.splashCreate to "Created Splash Screen"),
                navOptions {
                    anim {
                        enter = androidx.navigation.ui.R.anim.nav_default_enter_anim
                        popEnter = androidx.navigation.ui.R.anim.nav_default_pop_enter_anim
                        popExit = androidx.navigation.ui.R.anim.nav_default_pop_exit_anim
                        exit = androidx.navigation.ui.R.anim.nav_default_exit_anim
                    }

                    // Кнопка назад не возвращает к Splash Screen
                    launchSingleTop = true

                    popUpTo(R.id.nav_graph_application) {
                        inclusive = true
                        }
                    }
                )
            }, 2000)
        }



}