package cusat.ddukk.firbaseauth.adapter

import android.content.ContentValues.TAG
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import cusat.ddukk.firbaseauth.R
import cusat.ddukk.firbaseauth.model.RecycleItemModel

class RecycleAdapter(private val list: ArrayList<RecycleItemModel>) :
    RecyclerView.Adapter<RecycleAdapter.RecycleViewHolder>() {
    private lateinit var parent: ViewGroup

//    private var listener: RecycleAdapterClickInterface? = null
//    fun setListener(listener: RecycleAdapterClickInterface) {
//
//        this.listener = listener
//    }

    class RecycleViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        val tvProductName: TextView = itemView.findViewById(R.id.tvProductName1)
        val tvProductPrice: TextView = itemView.findViewById(R.id.tvProductPrice1)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecycleViewHolder {
        //this.parent = parent
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.recycle_item, parent, false)
        return RecycleViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: RecycleViewHolder, position: Int) {

        val currentItem = list[position]
        Log.d("FirestoreData", "User: $currentItem")

        holder.tvProductName.text = currentItem.name
        holder.tvProductPrice.text = currentItem.price
    }
}