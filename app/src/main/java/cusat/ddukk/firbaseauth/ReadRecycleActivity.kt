package cusat.ddukk.firbaseauth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import cusat.ddukk.firbaseauth.adapter.RecycleAdapter
import cusat.ddukk.firbaseauth.model.RecycleItemModel

class ReadRecycleActivity : AppCompatActivity() {
    var db : FirebaseFirestore = FirebaseFirestore.getInstance()
    lateinit var productRv: RecyclerView
    lateinit var userAdapter: RecycleAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_read_recycle)

        productRv = findViewById(R.id.recycleView)
        productRv.layoutManager = LinearLayoutManager(this)


        val productArrayList = ArrayList<RecycleItemModel>()

        db.collection("products").get().addOnSuccessListener { querySnapshot ->
            for (document in querySnapshot) {
                val products = document.toObject(RecycleItemModel::class.java)
                productArrayList.add(document.toObject(RecycleItemModel::class.java))
                Log.d("FirestoreData", "User: $products")
            }
            userAdapter = RecycleAdapter(productArrayList)
            productRv.adapter = userAdapter
            userAdapter.notifyDataSetChanged()
        }.addOnFailureListener { e ->
            Toast.makeText(applicationContext, "failed", Toast.LENGTH_SHORT).show()
            Log.e("FirestoreError", "Error getting documents: $e")
        }

        for (d in productArrayList) {
            Log.d("ArrayData", "User: $d")
        }



    }


}