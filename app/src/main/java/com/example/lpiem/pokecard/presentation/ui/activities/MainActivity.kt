package com.example.lpiem.pokecard.presentation.ui.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import com.example.lpiem.pokecard.R
import com.example.lpiem.pokecard.presentation.presenter.MainActivityPresenter
import com.example.lpiem.pokecard.presentation.ui.fragments.MainFragment
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import kotlinx.android.synthetic.main.fragment_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(), HasSupportFragmentInjector {

    @Inject
    lateinit var injector: DispatchingAndroidInjector<Fragment>

    override fun supportFragmentInjector(): AndroidInjector<Fragment> = injector

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_container)
        //this.configureBottomView();
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.activityContainer, MainFragment())
                .commit()
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

