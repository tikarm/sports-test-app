package com.example.testapp.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.testapp.R

class EventsAdapter : RecyclerView.Adapter<EventsAdapter.EventViewHolder>() {

    private var eventUiStateList: List<EventUiState> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_event, parent, false)
        return EventViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        val event = eventUiStateList[position]
        holder.bind(event)
    }

    override fun getItemCount(): Int {
        return eventUiStateList.size
    }

    fun addItems(eventUiStateList: List<EventUiState>) {
        this.eventUiStateList = eventUiStateList
        notifyDataSetChanged()
    }

    class EventViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val titleTextView: TextView = itemView.findViewById(R.id.tv_title_event)
        private val subtitleTextView: TextView = itemView.findViewById(R.id.tv_subtitle_event)
        private val dateTextView: TextView = itemView.findViewById(R.id.tv_date_event)
        private val imageView: ImageView = itemView.findViewById(R.id.iv_event_image)

        fun bind(eventUiState: EventUiState) {
            titleTextView.text = eventUiState.title
            subtitleTextView.text = eventUiState.subtitle
            dateTextView.text = eventUiState.date

            Glide.with(itemView)
                .load(eventUiState.imageUrl)
                .into(imageView)

        }
    }
}