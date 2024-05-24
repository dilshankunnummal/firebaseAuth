package cusat.ddukk.firbaseauth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        var btnRegister = findViewById<TextView>(R.id.btnUpdate)
        var etEmail = findViewById<EditText>(R.id.etEmail)
        var etPassword = findViewById<EditText>(R.id.etPassword)
        var etConfirmPassword = findViewById<EditText>(R.id.etConfirmPassword)
        var firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()




        btnRegister.setOnClickListener(View.OnClickListener {

            var getEmail = etEmail.text.toString()
            var getPassword = etPassword.text.toString()
            var getConfirmPassword = etConfirmPassword.text.toString()
            if (getEmail.isNotEmpty() && getPassword.isNotEmpty() && getConfirmPassword.isNotEmpty()) {
                if (getPassword == getConfirmPassword) {
                    firebaseAuth.createUserWithEmailAndPassword(getEmail, getPassword)
                        .addOnCompleteListener {  //adddoncompletelister used to complete a TASK here is store detail in firebase
                            if (it.isSuccessful) {
                                Toast.makeText(
                                    applicationContext,
                                    "Login Successful",
                                    Toast.LENGTH_SHORT
                                ).show()
                                val homeIntent =
                                    Intent(applicationContext, HomeActivity::class.java)
                                startActivity(homeIntent)
                            } else {
                                Toast.makeText(applicationContext, "Error", Toast.LENGTH_SHORT)
                                    .show()
                            }
                        }
                } else {
                    Toast.makeText(applicationContext, "password mismatch", Toast.LENGTH_SHORT)
                        .show()
                }
            } else {
                Toast.makeText(applicationContext, "field is empty", Toast.LENGTH_SHORT).show()
            }
        })
    }
}