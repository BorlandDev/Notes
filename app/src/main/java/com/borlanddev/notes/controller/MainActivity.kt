package com.borlanddev.notes.controller

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.window.SplashScreen
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.borlanddev.notes.R

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private lateinit var appBarConfiguration : AppBarConfiguration



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navController = (supportFragmentManager.findFragmentById(R.id.navHostFragment)
                as NavHostFragment).navController

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        setupActionBarWithNavController(navController)

        appBarConfiguration = AppBarConfiguration(navController.graph)

        navController.addOnDestinationChangedListener { _, destination, _ ->

            when (destination.id) {

                R.id.splashFragment -> supportActionBar?.hide()
              /*R.id.ListFragment -> Проверять первый ли это запуск приложения .
              Показывать анимацию и вызвать функцию прослушки сети. Если запуск не первый, показывать
              анимацию под тулбаром и также слушать сеть.
              */

              //R.id.DetailsFragment ->  продолжать показывать анимацию

                else -> supportActionBar?.show()
            }
        }


    }




}
