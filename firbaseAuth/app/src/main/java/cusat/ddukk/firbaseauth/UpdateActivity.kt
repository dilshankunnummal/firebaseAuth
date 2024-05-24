package cusat.ddukk.firbaseauth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

class UpdateActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update)

        val db = Firebase.firestore

        var tvName = findViewById<TextView>(R.id.etName)
        var tvEmail = findViewById<TextView>(R.id.etEmail)
        var tvAge = findViewById<TextView>(R.id.etAge)
        var tvNumber = findViewById<TextView>(R.id.etNumber)
        var btnUpdate = findViewById<Button>(R.id.btnUpdate)

//        val name = tvName.text.toString().trim()
//        val email = tvEmail.text.toString().trim()
//        val age = tvAge.text.toString().trim()
//        val number = tvNumber.text.toString().trim()
//
//        tvName.text = name
//        tvEmail.text = email
//        tvAge.text = age
//        tvNumber.text = number

        db.collection("users").document("details").get().addOnSuccessListener {
            if (it != null) {
                val name = it.data?.get("name").toString()
                val email = it.data?.get("email").toString()
                val age = it.data?.get("age").toString()
                val number = it.data?.get("number").toString()

                tvName.text = name
                tvEmail.text = email
                tvAge.text = age
                tvNumber.text = number
            }
        }

        btnUpdate.setOnClickListener {
            val sName = tvName.text.toString().trim()
            val sEmail = tvEmail.text.toString().trim()
            val sAge = tvAge.text.toString().trim()
            val sNumber = tvNumber.text.toString().trim()

            val user = hashMapOf<String, Any>(
                "name" to sName,
                "email" to sEmail,
                "age" to sAge,
                "number" to sNumber
            )
            db.collection("users").document("details").update(user)
            var i = Intent(applicationContext, ReadActivity::class.java)
            startActivity(i)
        }
    }
}