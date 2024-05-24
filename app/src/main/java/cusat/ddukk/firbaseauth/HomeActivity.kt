package cusat.ddukk.firbaseauth

import cusat.ddukk.firbaseauth.ui.RecycleActivity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        var btnSave = findViewById<TextView>(R.id.btnSave)
        var etEmail = findViewById<EditText>(R.id.etEmail)
        var etName = findViewById<EditText>(R.id.etName)
        var etNumber = findViewById<EditText>(R.id.etNumber)
        var etAge = findViewById<EditText>(R.id.etAge)
        var btnRead = findViewById<Button>(R.id.btnCreate)
        var btnRecycle = findViewById<Button>(R.id.btnRecycle)
//        var btnUpdate = findViewById<Button>(R.id.btnUpdate)
//        var btnDelete = findViewById<Button>(R.id.btnDelete)

        val db = Firebase.firestore

        btnSave.setOnClickListener {
            val getName = etName.text.toString().trim()
            val getNumber = etNumber.text.toString().trim()
            val getAge = etAge.text.toString().trim()
            val getEmail = etEmail.text.toString().trim()

            val user = hashMapOf<String, Any>(
                "name" to getName,
                "number" to getNumber,
                "age" to getAge,
                "email" to getEmail
            )
            db.collection("users").document("details").set(user).addOnSuccessListener {
                Toast.makeText(applicationContext, "success", Toast.LENGTH_SHORT).show()
                var i = Intent(applicationContext, ReadActivity::class.java)
                startActivity(i)
            }.addOnFailureListener {
                Toast.makeText(applicationContext, "failed", Toast.LENGTH_SHORT).show()

            }
        }

        btnRead.setOnClickListener(View.OnClickListener {
            var i = Intent(applicationContext, ReadActivity::class.java)
            startActivity(i)
        })

        btnRecycle.setOnClickListener(View.OnClickListener {
            var i = Intent(applicationContext, RecycleActivity::class.java)
            startActivity(i)
        })
    }
}