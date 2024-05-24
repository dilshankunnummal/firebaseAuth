package cusat.ddukk.firbaseauth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class AddActivity : AppCompatActivity() {
    private lateinit var etProductName: EditText
    private lateinit var etProductPrice: EditText
    private lateinit var btnSave1: Button
  //  private val db = FirebaseFirestore.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)
    }
}