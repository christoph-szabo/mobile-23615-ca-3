package com.cszabo.recyclerviewsharedprefs

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.row.view.*

class MainAdapter(val arrayList: ArrayList<Model>, val context: Context) :
    RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    //binding items to relevant variables in model
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun bindItems(model: Model) {

            itemView.titleTv.text = model.title
            itemView.descriptionTv.text = model.des
            itemView.imageIv.setImageResource(model.image)
        }
    }
    //creating layout inflater
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.row, parent, false)

        return ViewHolder(v)
    }
    //getting size of array
    override fun getItemCount(): Int {
        return arrayList.size
    }

    //For the future it is possible to have an onclick action when clicking an item in the recycler, potentially editing the values.
    //Didn't have time to finish this.
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(arrayList[position])

        holder.itemView.setOnClickListener {

            if(position == 0){
                Toast.makeText(
                    context,
                    "You clicked on item 1",
                    Toast.LENGTH_LONG
                ).show()
            }

            if(position == 1){
                Toast.makeText(
                    context,
                    "You clicked on item 2",
                    Toast.LENGTH_LONG
                ).show()
            }

            if(position == 2){
                Toast.makeText(
                    context,
                    "You clicked on item 3",
                    Toast.LENGTH_LONG
                ).show()
            }

            if(position == 3){
                Toast.makeText(
                    context,
                    "You clicked on item 4",
                    Toast.LENGTH_LONG
                ).show()
            }

            if(position == 4){
                Toast.makeText(
                    context,
                    "You clicked on item 5",
                    Toast.LENGTH_LONG
                ).show()
            }

        }
    }
}