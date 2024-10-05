package com.example.elixir.event

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.elixir.R
import com.squareup.picasso.Picasso


class EventListAdapter(private val context: Context , private val dataList: EventData)
    : RecyclerView.Adapter<EventListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.event_list, parent, false)
        return ViewHolder(itemView)
    }



    override fun onBindViewHolder(holder: ViewHolder, position : Int) {
        val currentItem = dataList[position]
        holder.eventName.text = currentItem.name
        Picasso.get().load(currentItem.img_link).into(holder.eventLogo)

        holder.registerBtn.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(currentItem.form_link)
            context.startActivity(intent)
        }
        holder.clubName.text = currentItem.club
        holder.eventDate.text = currentItem.date

    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val eventLogo : ImageView
        val eventName : TextView
        val registerBtn : TextView
        val clubName : TextView
        val eventDate : TextView
        val eventCard : CardView

        init {
            eventName = itemView.findViewById(R.id.event_name)
            eventLogo = itemView.findViewById(R.id.event_logo)
            registerBtn = itemView.findViewById(R.id.register_btn)
            clubName = itemView.findViewById(R.id.club_name)
            eventDate = itemView.findViewById(R.id.event_date)
            eventCard = itemView.findViewById(R.id.event_card)
        }
    }
}


