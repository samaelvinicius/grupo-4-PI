package com.projetointegrador.grupo04

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.view.Menu
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
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

const val PERMISSIONS_REQUEST_CAMERA = 200

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var navController: NavController
    private lateinit var imgFotoUsuario: ImageView


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

    //SOBRESCREVO A FUNCAO CREATEOPTIONSMENU PARA CONSEGUIR EXECUTAR ACOES EM CERTOS ELEMENTOS DO MENU (EXEMPLO: CAMERA)
    override fun onCreateOptionsMenu(menu: Menu): Boolean
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.activity_main_drawer, menu)
        imgFotoUsuario = findViewById<ImageView>(R.id.imgFoto)
        imgFotoUsuario.setOnClickListener { capturarFoto() }

        return true
    }

    //SOBRESCREVO ONACTIVITYRESULT PARA SABER QUANDO USUARIO TIROU A FOTO (ESTA ROTINA É ASSINCRONA!)
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?)
    {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == PERMISSIONS_REQUEST_CAMERA && data != null)
        {
            imgFotoUsuario.setImageBitmap(data.extras?.get("data") as Bitmap)
            Toast.makeText(this, "ENTROU NO IF", Toast.LENGTH_LONG).show()
        }
        Toast.makeText(this, "SAINDO DA FUNCAO", Toast.LENGTH_LONG).show()
    }

    //SOBRESCREVO ONREQUESTPERMISSIONSRESULT PARA SABER SE USUARIO ME DEU ACESSO A CAMERA
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray)
    {
        when(requestCode)
        {
            PERMISSIONS_REQUEST_CAMERA -> if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) capturarFoto()
        }
    }

    //E TODAS AS FUNCOES ACIMA APENAS PARA DISPARAR A CAMERA DO CELULAR
    private fun capturarFoto()
    {
        Toast.makeText(this, "VAI TENTAR", Toast.LENGTH_LONG).show()

        if(ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)== PackageManager.PERMISSION_GRANTED)
        {
            val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(cameraIntent, PERMISSIONS_REQUEST_CAMERA)
        }
        else
        {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA), 200)
        }
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