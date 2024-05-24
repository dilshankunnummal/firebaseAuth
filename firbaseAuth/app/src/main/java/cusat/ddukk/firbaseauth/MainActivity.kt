package cusat.ddukk.firbaseauth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var btnLogin = findViewById<Button>(R.id.btnLogin)
        var btnSignup = findViewById<TextView>(R.id.btnSignup)
        var etEmail = findViewById<EditText>(R.id.etEmail)
        var etPassword = findViewById<EditText>(R.id.etPassword)
        var firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()


        btnLogin.setOnClickListener(View.OnClickListener {
            var getEmail = etEmail.text.toString()
            var getPassword = etPassword.text.toString()

            if (getEmail.isNotEmpty() && getPassword.isNotEmpty()) {
                firebaseAuth.signInWithEmailAndPassword(getEmail, getPassword)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            // Sign in success, update UI with the signed-in user's information
                            Toast.makeText(applicationContext, "welcome", Toast.LENGTH_SHORT).show()
                            val homeIntent = Intent(applicationContext, HomeActivity::class.java)
                            startActivity(homeIntent)

                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(applicationContext, "login failed", Toast.LENGTH_SHORT)
                                .show()
                        }
                    }
            }

        })


//        btnLogin.setOnClickListener(View.OnClickListener {
//            Toast.makeText(applicationContext, "welcome", Toast.LENGTH_SHORT).show()
//            val homeIntent = Intent(applicationContext, HomeActivity :: class.java)
//            startActivity(homeIntent)
//        })

        btnSignup.setOnClickListener(View.OnClickListener {
            val registerIntent = Intent(applicationContext, RegisterActivity::class.java)
            startActivity(registerIntent)
        })
    }
}

