package com.example.lpiem.pokecard.presentation.ui.fragments

import android.content.Context
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.lpiem.pokecard.R
import com.example.lpiem.pokecard.base.BaseFragment
import com.example.lpiem.pokecard.presentation.presenter.ProfileFragmentPresenter
import com.example.lpiem.pokecard.presentation.presenter.ProfileView
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_profile.*
import javax.inject.Inject


class ProfileFragment : BaseFragment<ProfileFragmentPresenter>(), ProfileView {
    override val layoutId: Int = com.example.lpiem.pokecard.R.layout.fragment_profile
    @Inject
    override lateinit var presenter: ProfileFragmentPresenter

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        presenter.attach(this)

        val test = getURLForResource(R.drawable.com_facebook_profile_picture_blank_portrait)
        main.setBackgroundColor(ContextCompat.getColor(this.context!!, com.example.lpiem.pokecard.R.color.yellowPokemon))
        profileLayout.setBackgroundColor(Color.WHITE)
        Glide.with(context)
            .load(test)
            .apply(RequestOptions.circleCropTransform())
            .into(profilePicture)

    }

    fun getURLForResource(resourceId: Int): String {
        return Uri.parse("android.resource://" + R::class.java!!.getPackage().getName() + "/" + resourceId).toString()
    }
}