package com.example.lpiem.pokecard.presentation.ui.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import com.example.lpiem.pokecard.R
import com.example.lpiem.pokecard.base.BaseFragment
import com.example.lpiem.pokecard.data.entity.SignupUser
import com.example.lpiem.pokecard.presentation.presenter.RegisterFragmentPresenter
import com.example.lpiem.pokecard.presentation.presenter.RegisterView
import com.example.lpiem.pokecard.presentation.ui.activities.LoginActivity
import com.example.lpiem.pokecard.presentation.ui.activities.MainActivity
import com.example.lpiem.pokecard.utils.EmailValidator
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_register.*
import javax.inject.Inject


class RegisterFragment : BaseFragment<RegisterFragmentPresenter>(), RegisterView {
    override fun displayLoader() {
        //
    }

    override fun hideLoader() {
        //
    }

    override val layoutId: Int = com.example.lpiem.pokecard.R.layout.fragment_register

    @Inject
    override lateinit var presenter: RegisterFragmentPresenter

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        presenter.attach(this)
        passwordField.setOnEditorActionListener { _, actionId, event ->
            if (event != null && event.keyCode == KeyEvent.KEYCODE_ENTER || actionId == EditorInfo.IME_ACTION_DONE) {
                //do what you want on the press of 'done'
                registerButton.performClick()
            }
            false
        }

        registerButton.setOnClickListener {
            register()
        }

        alreadyHaveAccount.setOnClickListener {
            goToLoginActivity()
        }
    }


    private fun register(){
        if(emailField.text!!.isEmpty() || passwordField.text!!.isEmpty() || pseudoField.text!!.isEmpty()) {
            Toast.makeText(context, getString(R.string.fillAllFields), Toast.LENGTH_SHORT).show()
        }
        else if(!(emailField.text!!.isEmpty()) && !(EmailValidator().isEmailValid(emailField.text.toString()))) {
            Toast.makeText(context,getString(R.string.emailNotValid), Toast.LENGTH_SHORT).show()
        }
        else {
            val signupUser = SignupUser(emailField.text.toString(), passwordField.text.toString(), pseudoField.text.toString())
            presenter.signup(signupUser)
        }
    }

    private fun goToLoginActivity(){
        val intent = Intent(context, LoginActivity::class.java)
        startActivity(intent)
        activity?.finish()
    }

    override fun showError(errorMessage: String) {
        Toast.makeText(context,errorMessage,Toast.LENGTH_SHORT).show()
    }

    override fun goToMain() {
        val intent = Intent(context, MainActivity::class.java)
        startActivity(intent)
        activity?.finish()
    }

}