package com.projetointegrador.grupo04

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar: Toolbar = findViewById(R.id.main_toolbar)
        setSupportActionBar(toolbar)

        navController = findNavController(R.id.nav_host_fragment)

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val drawerLayout: DrawerLayout = findViewById(R.id.main_drawer_layout)
        val navView: NavigationView = findViewById(R.id.mainNavigationView)
        appBarConfiguration = AppBarConfiguration(setOf(
            R.id.navigation_series_movies,R.id.navigation_network, R.id.navigation_people, R.id.navigation_explore),drawerLayout) //Pass the drawer layout id from activity xml

        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        visibilityNavElements(navController) //If you want to hide drawer or bottom navigation configure that in this function

        setMainWindowFlags()

    }

    private fun visibilityNavElements(navController: NavController) {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.navigation_detail_movie -> hideBothNavigation()
                else -> showBothNavigation()
            }
        }
    }

     private fun hideBothNavigation() { //Hide both drawer and bottom navigation bar
        findViewById<BottomNavigationView>(R.id.mainBottomNavigationView)?.visibility = View.GONE
        findViewById<DrawerLayout>(R.id.main_drawer_layout)?.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED) //To lock navigation drawer so that it don't respond to swipe gesture
        supportActionBar?.hide()
    }

    private fun showBothNavigation() {
        findViewById<BottomNavigationView>(R.id.mainBottomNavigationView)?.visibility = View.VISIBLE
        findViewById<NavigationView>(R.id.mainNavigationView)?.visibility = View.VISIBLE
        findViewById<DrawerLayout>(R.id.main_drawer_layout)?.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
        setupNavControl() //To configure navController with drawer and bottom navigation
        supportActionBar?.show()
    }

    private fun setupNavControl() {
        findViewById<NavigationView>(R.id.mainNavigationView)?.setupWithNavController(navController) //Setup Drawer navigation with navController
        findViewById<BottomNavigationView>(R.id.mainBottomNavigationView)?.setupWithNavController(navController) //Setup Bottom navigation with navController
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    override fun onBackPressed() {
        when { //If drawer layout is open close that on back pressed
            findViewById<DrawerLayout>(R.id.main_drawer_layout).isDrawerOpen(GravityCompat.START) -> {
                findViewById<DrawerLayout>(R.id.main_drawer_layout).closeDrawer(GravityCompat.START)
            }
            else -> {
                super.onBackPressed() //If drawer is already in closed condition then go back
            }
        }
    }

    fun setMainWindowFlags() {
        val window: Window = window
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
    }

}