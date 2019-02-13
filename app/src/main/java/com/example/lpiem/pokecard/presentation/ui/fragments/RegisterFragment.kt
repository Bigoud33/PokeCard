package com.example.lpiem.pokecard.presentation.ui.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.example.lpiem.pokecard.R
import com.example.lpiem.pokecard.base.BaseFragment
import com.example.lpiem.pokecard.presentation.presenter.RegisterFragmentPresenter
import com.example.lpiem.pokecard.presentation.presenter.RegisterView
import com.example.lpiem.pokecard.presentation.ui.activities.LoginActivity
import com.example.lpiem.pokecard.presentation.ui.activities.MainActivity
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_register.*
import java.util.regex.Pattern
import javax.inject.Inject

class RegisterFragment : BaseFragment<RegisterFragmentPresenter>(), RegisterView {
    override val layoutId: Int = R.layout.fragment_register

    @Inject
    override lateinit var presenter: RegisterFragmentPresenter

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        presenter.attach(this)

        registerButton.setOnClickListener {
            register()
        }

        alreadyHaveAccount.setOnClickListener {
            goToLoginActivity()
        }
    }


    private fun register(){
        if(emailField.text!!.isEmpty() || passwordField.text!!.isEmpty() || pseudoField.text!!.isEmpty()) {
            Toast.makeText(context, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show()
        }
        else if(!(emailField.text!!.isEmpty()) && !(isEmailValid(emailField.text.toString()))) {
            Toast.makeText(context,"Veuillez entrer un email valide", Toast.LENGTH_SHORT).show()
        }
        else {
            //MUST Check if email not already exists + and pseudo not already exists
            val intent = Intent(context, MainActivity::class.java)
            startActivity(intent)
        }
    }

    private fun goToLoginActivity(){
        val intent = Intent(context, LoginActivity::class.java)
        startActivity(intent)
        activity!!.finish()

    }

    fun isEmailValid(email: String): Boolean {
        return Pattern.compile(
            "^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]|[\\w-]{2,}))@"
                    + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                    + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                    + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                    + "[0-9]{1,2}|25[0-5]|2[0-4][0-9]))|"
                    + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$"
        ).matcher(email).matches()
    }
}