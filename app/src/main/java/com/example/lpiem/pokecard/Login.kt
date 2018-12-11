package com.example.lpiem.pokecard

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
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
import kotlinx.android.synthetic.main.activity_login.*
import android.widget.TextView
import com.example.lpiem.pokecard.presentation.ui.activities.MainActivity
import java.util.regex.Pattern


class Login : AppCompatActivity(), View.OnClickListener {


    private lateinit var callbackManager: CallbackManager
    private lateinit var mGoogleSignInClient: GoogleSignInClient
    private lateinit var emailField: EditText
    private lateinit var passwordField: EditText
    val RC_SIGN_IN: Int = 1
    val TAG: String= "TAGGoogle"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


        //Facebook

        callbackManager = CallbackManager.Factory.create()




        login_button.setReadPermissions("email")


        // Callback registration
        login_button.registerCallback(callbackManager, object : FacebookCallback<LoginResult> {
            override fun onSuccess(loginResult: LoginResult) {
                Toast.makeText(this@Login, "Connected to your Facebook account with success", LENGTH_SHORT).show()
            }

            override fun onCancel() {
                Toast.makeText(this@Login, "Connection canceled", LENGTH_SHORT).show()
            }

            override fun onError(exception: FacebookException) {
                Toast.makeText(this@Login, "Connection to your Facebook account failed", LENGTH_SHORT).show()
                Toast.makeText(this@Login,exception.message, LENGTH_SHORT).show()
            }
        })

        val accessToken = AccessToken.getCurrentAccessToken()
        val isLoggedIn = accessToken != null && !accessToken.isExpired

        // Google

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build()

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso)

        val signInButton = findViewById<SignInButton>(R.id.sign_in_button)
        signInButton.setSize(SignInButton.SIZE_STANDARD)
        signInButton.setOnClickListener(this)

        // Other
        val registerButton = findViewById<Button>(R.id.registerBtn)
        val loginButtonEmail = findViewById<Button>(R.id.loginBtn)
        emailField = findViewById(R.id.email)
        passwordField = findViewById(R.id.password)

        registerButton.setOnClickListener(this)
        loginButtonEmail.setOnClickListener(this)



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
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }

    }

    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {

            val account = completedTask.getResult(ApiException::class.java)

            // Signed in successfully, show authenticated UI.
            Toast.makeText(this@Login, "Connected to your google account with success", LENGTH_SHORT).show()

            updateUI(account)
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        } catch (e: ApiException) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.d(TAG, "signInResult:failed code=" + e.statusCode)
            //updateUI(null)
        }

    }

    private fun updateUI(account: GoogleSignInAccount?) {
        val c = findViewById<SignInButton>(R.id.sign_in_button)
        val textView = c.getChildAt(0) as TextView
        textView.text = "Connecté en tant que "+account?.displayName
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.sign_in_button -> signInGoogle()
            R.id.registerBtn -> register()
            R.id.loginBtn -> login()
        }

    }

    private fun signInGoogle() {
        val signInIntent = mGoogleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    private fun register(){
        val intent = Intent(this, Register::class.java)
        startActivity(intent)
    }

    private fun login(){
        // emailfield or password field is empty
        if(emailField.text.isEmpty() || passwordField.text.isEmpty() ) {
            Toast.makeText(this@Login, "Email or password is missing", LENGTH_SHORT).show()
        }
        // the email is not valid
        else if (!(emailField.text.isEmpty()) && !(isEmailValid(emailField.text.toString()))){
            Toast.makeText(this,"Please enter a valid email address", LENGTH_SHORT).show()
        } else {
            // MUST check if email and password exists and are good
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
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
