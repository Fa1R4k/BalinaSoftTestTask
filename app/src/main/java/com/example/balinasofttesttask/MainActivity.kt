package com.example.balinasofttesttask

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.balinasofttesttask.databinding.ActivityMainBinding
import com.example.balinasofttesttask.di.viewModel.ViewModelFactory
import com.example.balinasofttesttask.ui.auth.AuthActivity
import com.example.balinasofttesttask.ui.base_view_model.TokenViewModel
import com.example.balinasofttesttask.ui.camera.CameraActivity
import com.example.balinasofttesttask.ui.map.MapViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView
import javax.inject.Inject


class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var factory: ViewModelFactory
    private val tokenViewModel: TokenViewModel by viewModels { factory }
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        (applicationContext as DaggerApp).appComponent.inject(this)
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (tokenViewModel.isUserAuth()) {
            startActivity(Intent(this@MainActivity, AuthActivity::class.java))
        } else {
            setSupportActionBar(binding.appBarMain.toolbar)


            binding.appBarMain.fab.setOnClickListener {
                navigateToCamera()
            }
            val drawerLayout: DrawerLayout = binding.drawerLayout
            val navView: NavigationView = binding.navView
            val navController = findNavController(R.id.nav_host_fragment_content_main)

            navController.addOnDestinationChangedListener { _, dest, _ ->
                when (dest.id) {
                    R.id.commentsFragment -> findViewById<FloatingActionButton>(R.id.fab).isVisible =
                        false

                    else -> findViewById<FloatingActionButton>(R.id.fab).isVisible = true
                }
            }

            appBarConfiguration = AppBarConfiguration(
                setOf(
                    R.id.nav_photos, R.id.nav_map
                ), drawerLayout
            )
            setupActionBarWithNavController(navController, appBarConfiguration)
            navView.setupWithNavController(navController)
        }


    }

    private fun navigateToCamera() {
        val intent = Intent(this, CameraActivity::class.java)
        startActivity(intent)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}