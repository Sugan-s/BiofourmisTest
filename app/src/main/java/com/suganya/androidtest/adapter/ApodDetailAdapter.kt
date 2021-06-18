package com.suganya.androidtest.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.squareup.picasso.Picasso
import com.suganya.androidtest.R
import com.suganya.androidtest.model.ApodDetailModel
import java.util.ArrayList

class ApodDetailAdapter  (private val _context: Context, val apodDetailList: List<ApodDetailModel>) : androidx.recyclerview.widget.RecyclerView.Adapter<ApodDetailAdapter.ViewHolder>() {

    var view: View? = null
    var viewHolder: ApodDetailAdapter.ViewHolder? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        view = LayoutInflater.from(parent.context).inflate(R.layout.cartview_raw, parent, false)

        viewHolder = ViewHolder(view!!)
        return viewHolder!!
    }

    override fun getItemCount(): Int {
        return apodDetailList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.imageTittle.text = apodDetailList[position].title
        holder.imageDate.text = apodDetailList[position].date
        Picasso.get()
            .load(apodDetailList[position].url)
            .resize(150, 100)
            .onlyScaleDown()
            .into(holder.apodImage)
    }



    inner class ViewHolder(itemView: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(itemView) {

        val imageTittle: TextView
        val apodImage: ImageView
        val imageDate : TextView

        init {
            imageTittle = itemView.findViewById(R.id.tv_imagetitle)
            apodImage = itemView.findViewById(R.id.imv_apodimage)
            imageDate = itemView.findViewById(R.id.tv_imagdate)


        }

    }


}
