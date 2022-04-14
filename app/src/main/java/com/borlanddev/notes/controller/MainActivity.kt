package com.borlanddev.notes.controller

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
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


        val toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        setupActionBarWithNavController(navController)

        appBarConfiguration = AppBarConfiguration(navController.graph)

        navController.addOnDestinationChangedListener { _, destination, _ ->

             if (destination.id == R.id.splashFragment) supportActionBar?.hide()
             if (destination.id == R.id.noteListFragment) supportActionBar?.apply {
                 show()
                 setDisplayHomeAsUpEnabled(false)
             }


             /* Проверять первый ли это запуск приложения .
              Показывать анимацию и вызвать функцию прослушки сети. Если запуск не первый, показывать
              анимацию под тулбаром и также слушать сеть.
              */

              //R.id.DetailsFragment ->  продолжать показывать анимацию

            }
        }



    }








