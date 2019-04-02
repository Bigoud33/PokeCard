package com.example.lpiem.pokecard.presentation.ui.fragments

import android.content.Context
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.lpiem.pokecard.R
import com.example.lpiem.pokecard.base.BaseFragment
import com.example.lpiem.pokecard.data.entity.User
import com.example.lpiem.pokecard.presentation.presenter.ProfileFragmentPresenter
import com.example.lpiem.pokecard.presentation.presenter.ProfileView
import com.example.lpiem.pokecard.presentation.ui.picasso.CircleTransform
import com.squareup.picasso.Picasso
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_profile.*
import javax.inject.Inject


class ProfileFragment : BaseFragment<ProfileFragmentPresenter>(), ProfileView {
    override fun showError(errorMessage: String) {
        Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
    }

    override fun showProfile(user: User) {
        userPseudo.text = user.pseudo
        userEmail.text = user.email
        userId.text = user.id
//        Glide.with(context)
//            .load(user.picture)
//            .apply(RequestOptions.circleCropTransform())
//            .into(profilePicture)
        Picasso.get()
            .load(user.picture)
            .transform(CircleTransform())
            .into(profilePicture)
    }

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

        main.setBackgroundColor(ContextCompat.getColor(this.context!!, com.example.lpiem.pokecard.R.color.yellowPokemon))
        profileLayout.setBackgroundColor(Color.WHITE)



    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val sharedPreferences = context?.getSharedPreferences("pokecard", Context.MODE_PRIVATE)
        val userId = sharedPreferences?.getString("user-id", null)
        presenter.getProfile(userId!!)
    }


}