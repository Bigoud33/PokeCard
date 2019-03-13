package com.example.lpiem.pokecard.presentation.ui.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.lpiem.pokecard.R
import com.example.lpiem.pokecard.base.BaseFragment
import com.example.lpiem.pokecard.presentation.presenter.LoginFragmentPresenter
import com.example.lpiem.pokecard.presentation.presenter.LoginView
import com.example.lpiem.pokecard.presentation.ui.activities.MainActivity
import com.example.lpiem.pokecard.presentation.ui.activities.RegisterActivity
import com.example.lpiem.pokecard.utils.EmailValidator
import com.facebook.AccessToken
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginResult
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.SignInButton
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_login.*
import java.util.regex.Pattern
import javax.inject.Inject

class LoginFragment: BaseFragment<LoginFragmentPresenter>(), LoginView {

    private lateinit var callbackManager: CallbackManager
    private lateinit var mGoogleSignInClient: GoogleSignInClient
    val RC_SIGN_IN: Int = 1
    val TAG: String= "TAGGoogle"

    override val layoutId: Int = R.layout.fragment_login

    @Inject
    override lateinit var presenter: LoginFragmentPresenter

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        presenter.attach(this)
        callbackManager = CallbackManager.Factory.create()




        login_button.setReadPermissions("email")


        // Callback registration
        login_button.registerCallback(callbackManager, object : FacebookCallback<LoginResult> {
            override fun onSuccess(loginResult: LoginResult) {
                Toast.makeText(context, getString(R.string.facebookConnectionOK), Toast.LENGTH_SHORT).show()
            }

            override fun onCancel() {
                Toast.makeText(context, getString(R.string.facebookConnectionCanceled), Toast.LENGTH_SHORT).show()
            }

            override fun onError(exception: FacebookException) {
                Toast.makeText(context, getString(R.string.facebookConnectionNOK), Toast.LENGTH_SHORT).show()
                Toast.makeText(context,exception.message, Toast.LENGTH_SHORT).show()
            }
        })

        val accessToken = AccessToken.getCurrentAccessToken()
        val isLoggedIn = accessToken != null && !accessToken.isExpired

        // Google

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()

        mGoogleSignInClient = GoogleSignIn.getClient(this.activity!!,gso)

        sign_in_button.setSize(SignInButton.SIZE_STANDARD)

        // Oher



        registerBtn.setOnClickListener {
            register()
        }
        loginBtn.setOnClickListener {
            login()
        }
        sign_in_button.setOnClickListener {
            signInGoogle()
        }



    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleSignInResult(task)
        } else {
            callbackManager.onActivityResult(requestCode, resultCode, data)
            super.onActivityResult(requestCode, resultCode, data)
            val intent = Intent(context, MainActivity::class.java)
            startActivity(intent)
        }

    }

    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {

            val account = completedTask.getResult(ApiException::class.java)

            // Signed in successfully, show authenticated UI.
            Toast.makeText(context, getString(R.string.googleConnectionOK), Toast.LENGTH_SHORT).show()

            updateUI(account)
            val intent = Intent(context, MainActivity::class.java)
            startActivity(intent)
        } catch (e: ApiException) {
            Toast.makeText(context, getString(R.string.googleConnectionNOK), Toast.LENGTH_SHORT).show()
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.d(TAG, "signInResult:failed code=" + e.statusCode)
            //updateUI(null)
        }

    }

    private fun updateUI(account: GoogleSignInAccount?) {

        val textView = sign_in_button.getChildAt(0) as TextView
        textView.text = "Connecté en tant que "+account?.displayName
    }


    private fun signInGoogle() {
        val signInIntent = mGoogleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    private fun register(){
        val intent = Intent(context, RegisterActivity::class.java)
        startActivity(intent)
        activity!!.finish()
    }

    private fun login(){
        // emailfield or password field is empty
        if(email.text!!.isEmpty() || password.text!!.isEmpty() ) {
            Toast.makeText(context, getString(R.string.emailOrPasswordMissing), Toast.LENGTH_SHORT).show()
        }
        // the email is not valid
        else if (!(email.text!!.isEmpty()) && !(EmailValidator().isEmailValid(email.text.toString()))){
            Toast.makeText(context,getString(R.string.emailNotValid), Toast.LENGTH_SHORT).show()
        } else {
            // MUST check if email and password exists and are good
            val intent = Intent(context, MainActivity::class.java)
            startActivity(intent)
            activity!!.finish()
        }
    }

}