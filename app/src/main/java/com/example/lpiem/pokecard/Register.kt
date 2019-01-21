package com.example.lpiem.pokecard

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.lpiem.pokecard.presentation.ui.activities.MainActivity
import java.util.regex.Pattern

class Register : AppCompatActivity(), View.OnClickListener {


    lateinit var registerButton: Button
    lateinit var alreadyHaveAccountButton: Button
    lateinit var emailField: EditText
    lateinit var pseudoField: EditText
    lateinit var passwordField: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_register)


        registerButton.findViewById<Button>(R.id.registerButton2)
        alreadyHaveAccountButton.findViewById<Button>(R.id.alreadyHaveAccount)
        emailField.findViewById<EditText>(R.id.emailField)
        pseudoField.findViewById<EditText>(R.id.pseudoField)
        passwordField.findViewById<EditText>(R.id.passwordField)

        registerButton.setOnClickListener(this)
        registerButton.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.registerButton2 -> register()
            R.id.alreadyHaveAccount -> goToLoginActivity()
        }
    }

    private fun register(){
        if(emailField.text.isEmpty() || passwordField.text.isEmpty() || pseudoField.text.isEmpty()) {
            Toast.makeText(this, "Veuillez remplir tous les champs",Toast.LENGTH_SHORT).show()
        }
        else if(!(emailField.text.isEmpty()) && !(isEmailValid(emailField.text.toString()))) {
            Toast.makeText(this,"Veuillez entrer un email valide",Toast.LENGTH_SHORT).show()
        }
        else {
            //MUST Check if email not already exists + and pseudo not already exists
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    private fun goToLoginActivity(){
        val intent = Intent(this, Login::class.java)
        startActivity(intent)

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
