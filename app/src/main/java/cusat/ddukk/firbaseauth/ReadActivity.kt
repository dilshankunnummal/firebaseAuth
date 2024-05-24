package cusat.ddukk.firbaseauth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

class ReadActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_read)

        val db = Firebase.firestore

        var tvName = findViewById<TextView>(R.id.tvName)
        var tvEmail = findViewById<TextView>(R.id.tvEmail)
        var tvAge = findViewById<TextView>(R.id.tvAge)
        var tvNumber = findViewById<TextView>(R.id.tvNumber)
        var btnUpdate = findViewById<Button>(R.id.btnUpdate)
        var btnDelete = findViewById<Button>(R.id.btnDelete)


        btnUpdate.setOnClickListener(View.OnClickListener {
            var i = Intent(this, UpdateActivity::class.java)
            startActivity(i)
        })

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
        }.addOnFailureListener {
            Toast.makeText(applicationContext, "$it", Toast.LENGTH_SHORT).show()
        }

        btnDelete.setOnClickListener {
            db.collection("users").document("details").delete()
            var i = Intent(this, HomeActivity::class.java)
            startActivity(i)
        }
    }
}
