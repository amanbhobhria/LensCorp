package com.example.lensassignment

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView

class HomeActivity : AppCompatActivity(),NavigationView.OnNavigationItemSelectedListener  {


    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navView: NavigationView
    private lateinit var bottomNav: BottomNavigationView
    private  lateinit var toggle: ActionBarDrawerToggle

    private lateinit var homeFragment: HomeFragment
    private lateinit var circularsFragment: CircularsFragment
    private lateinit var ditsFragment: DitsFragment
    private lateinit var eventsFragment: EventsFragment
    private lateinit var irbopfFragment: IrbopfFragment
    private lateinit var linksFragment: LinksFragment
    private lateinit var newsFragment: NewsFragment
    private lateinit var organizationFragment: OrganizationFragment
    private lateinit var seniorityFragment: SeniorityFragment
    private lateinit var irmsFragment: IrmsFragment


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)


        bottomNav = findViewById(R.id.bottomNav)
        drawerLayout = findViewById(R.id.drawerLayout)

        navView = findViewById(R.id.navView)


        val headerView = navView.getHeaderView(0)

        navView.setNavigationItemSelectedListener(this)


        toggle = ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)




        supportActionBar?.title = "IRPOF App"
        supportActionBar?.setIcon(R.drawable.baseline_search_24)


        homeFragment= HomeFragment()
        circularsFragment= CircularsFragment()
        ditsFragment= DitsFragment()
        eventsFragment= EventsFragment()
        irbopfFragment= IrbopfFragment()
        linksFragment= LinksFragment()
        newsFragment= NewsFragment()
        seniorityFragment= SeniorityFragment()
        organizationFragment= OrganizationFragment()
        irmsFragment= IrmsFragment()




        supportFragmentManager
            .beginTransaction()
            .replace(R.id.frame_layout, homeFragment, "homeFrag")
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .setReorderingAllowed(true)
            .commit()

        bottomNav.selectedItemId = R.id.bottomNavHome

        bottomNav.selectedItemId = R.id.bottomNavHome



        if (homeFragment.isVisible) {
            bottomNav.selectedItemId = R.id.bottomNavHome
        }



        bottomNav.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.bottomNavIrms -> {
                    supportFragmentManager.beginTransaction().apply {
                        replace(R.id.frame_layout, irmsFragment, "irmscFrag")
                        setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        setReorderingAllowed(true)
                        addToBackStack("irmsFrag")
                        commit()
                    }


                }


                R.id.bottomNavHome -> {
                    supportFragmentManager.beginTransaction().apply {
                        replace(R.id.frame_layout, homeFragment, "homeFrag")
                        setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        setReorderingAllowed(true)
                        addToBackStack("homeFrag")
                        commit()
                    }
                }
                R.id.bottomNavEvents -> {

                    supportFragmentManager.beginTransaction().apply {
                        replace(R.id.frame_layout, eventsFragment, "eventsItemFrag")
                        setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        setReorderingAllowed(true)
                        addToBackStack("eventsItemFrag")
                        commit()
                    }
                }

                R.id.bottomNavIrpobf -> {

                    supportFragmentManager.beginTransaction().apply {
                        replace(R.id.frame_layout, irbopfFragment, "irbopfFrag")
                        setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        setReorderingAllowed(true)
                        addToBackStack("irbopfFrag")
                        commit()
                    }
                }
            }
            true
        }







    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.actionbar_menu, menu)

        // Set up the SearchView if needed
        val searchItem = menu?.findItem(R.id.action_search)
        val searchView = searchItem?.actionView as? SearchView

        // Add any additional setup for the SearchView if necessary

        return true
    }



    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        bottomNav.selectedItemId = R.id.bottomNavHome
        when (item.itemId) {

            R.id.miItem1 -> {

                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.frame_layout, linksFragment, "linksFrag")
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .setReorderingAllowed(true)
                    .addToBackStack("linksFrag")
                    .commit()
            }
            R.id.miItem2 -> {

                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.frame_layout, organizationFragment, "organizationFrag")
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .setReorderingAllowed(true)
                    .addToBackStack("organizationFrag")
                    .commit()
            }
            R.id.miItem3 ->{

                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.frame_layout, ditsFragment, "ditsFragment")
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .setReorderingAllowed(true)
                    .addToBackStack("ditsFragment")
                    .commit()
            }
            R.id.miItem4 -> {

                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.frame_layout, irmsFragment, "irmsFragment")
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .setReorderingAllowed(true)
                    .addToBackStack("irmsFragment")
                    .commit()
            }
            R.id.miItem5 -> {

                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.frame_layout, eventsFragment, "eventsFragment")
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .setReorderingAllowed(true)
                    .addToBackStack("eventsFragment")
                    .commit()
            }
            R.id.miItem6 -> {

                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.frame_layout, seniorityFragment, "seniorityFragment")
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .setReorderingAllowed(true)
                    .addToBackStack("seniorityFragment")
                    .commit()
            }
            R.id.miItem7 -> {
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.frame_layout, circularsFragment, "circularsFragment")
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .setReorderingAllowed(true)
                    .addToBackStack("circularsFragment")
                    .commit()
            }
            R.id.miItem8 -> {
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.frame_layout, newsFragment, "newsFragment")
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .setReorderingAllowed(true)
                    .addToBackStack("newsFragment")
                    .commit()

            }

            R.id.miItem9 -> {
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.frame_layout, irbopfFragment, "irbopfFragment")
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .setReorderingAllowed(true)
                    .addToBackStack("irbopfFragment")
                    .commit()

            }






        }
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onBackPressed() {
        super.onBackPressed()
        if (homeFragment.isVisible) {
            if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                drawerLayout.closeDrawer(GravityCompat.START)
            } else {

            }
        }
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }






}