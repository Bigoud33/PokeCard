package com.example.lpiem.pokecard.presentation.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.lpiem.pokecard.R
import com.example.lpiem.pokecard.presentation.ui.fragments.PokedexFragment
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import kotlinx.android.synthetic.main.activity_container_with_bottomnavigation.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(), HasSupportFragmentInjector {

    @Inject
    lateinit var injector: DispatchingAndroidInjector<Fragment>

    override fun supportFragmentInjector(): AndroidInjector<Fragment> = injector

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_container_with_bottomnavigation)
        //this.configureBottomView();
        var activeFragment = R.id.action_pokedex
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.activityContainer, PokedexFragment())
                .commit()
        }
        activity_main_bottom_navigation.setOnNavigationItemSelectedListener {
            if (it.itemId != activeFragment) {
                when (it.itemId) {
                    R.id.action_pokedex -> {
                        supportFragmentManager
                            .beginTransaction()
                            .replace(R.id.activityContainer, PokedexFragment())
                            .commit()
                        activeFragment = R.id.action_pokedex
                    }
                    R.id.action_pokeshop -> {
                        supportFragmentManager
                            .beginTransaction()
                            .replace(R.id.activityContainer, PokeshopFragment())
                            .commit()
                        activeFragment = R.id.action_pokeshop
                    }
                    R.id.action_fights -> {
                        supportFragmentManager
                            .beginTransaction()
                            .replace(R.id.activityContainer, FightFragment())
                            .commit()
                        activeFragment = R.id.action_fights
                    }
                    R.id.action_friends -> {
                        supportFragmentManager
                            .beginTransaction()
                            .replace(R.id.activityContainer, FriendsFragment())
                            .commit()
                        activeFragment = R.id.action_friends
                    }
                    R.id.action_profile -> {
                        supportFragmentManager
                            .beginTransaction()
                            .replace(R.id.activityContainer, ProfileFragment())
                            .commit()
                        activeFragment = R.id.action_profile
                    }
                }
            }
            true
        }
    }

    /*private fun configureBottomView(){
        activity_main_bottom_navigation.setOnNavigationItemSelectedListener(item -> updateMainFragment(item.getItemId()));
    }

    private Boolean updateMainFragment(Integer integer){
        switch (integer) {
            case R.id.action_android:
            this.mainFragment.updateDesignWhenUserClickedBottomView(MainFragment.REQUEST_ANDROID);
            break;
            case R.id.action_logo:
            this.mainFragment.updateDesignWhenUserClickedBottomView(MainFragment.REQUEST_LOGO);
            break;
            case R.id.action_landscape:
            this.mainFragment.updateDesignWhenUserClickedBottomView(MainFragment.REQUEST_LANDSCAPE);
            break;
        }
        return true;
    }*/
}

