package cusat.ddukk.firbaseauth.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore
import cusat.ddukk.firbaseauth.R
import cusat.ddukk.firbaseauth.ReadRecycleActivity

class RecycleActivity : AppCompatActivity() {

    private lateinit var etProductName: EditText
    private lateinit var etProductPrice: EditText
    private lateinit var btnSave1: Button
    private val db = FirebaseFirestore.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycle)


        etProductName = findViewById(R.id.tvProductName)
        etProductPrice = findViewById(R.id.tvProductPrice)
        btnSave1 = findViewById(R.id.btnProductSave)
        var btnReadRecycle = findViewById<Button>(R.id.btnReadRecycle)

        btnSave1.setOnClickListener {
            var getProductName = etProductName.text.toString()
            var getProductPrice = etProductPrice.text.toString()

            if (getProductName.isNotEmpty() && getProductPrice.isNotEmpty()) {
                val productDetails = hashMapOf<String, Any>(
                    "name" to getProductName,
                    "price" to getProductPrice
                )
                db.collection("products").add(productDetails)
                    .addOnSuccessListener { documentReference ->
                        Toast.makeText(applicationContext, "Successfully Added", Toast.LENGTH_SHORT)
                            .show()
                        var i = Intent(applicationContext, ReadRecycleActivity::class.java)
                        startActivity(i)
                    }.addOnFailureListener {
                        Toast.makeText(
                            applicationContext,
                            "Successfully Failed",
                            Toast.LENGTH_SHORT
                        )
                            .show()
                    }
            } else {
                Toast.makeText(this, "Please enter valid product details", Toast.LENGTH_SHORT)
                    .show()
            }
        }

        btnReadRecycle.setOnClickListener(View.OnClickListener {
            var i = Intent(applicationContext, ReadRecycleActivity::class.java)
            startActivity(i)
        })
    }
}