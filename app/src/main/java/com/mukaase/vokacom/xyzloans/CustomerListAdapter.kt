package com.mukaase.vokacom.xyzloans

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_customer.view.*

class CustomerListAdapter internal constructor(context: Context) : RecyclerView.Adapter<CustomerListAdapter.WordViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var list = emptyList<Customer>() // Cached copy of list
    private val listener: Listener = context as Listener

    inner class WordViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val fullNameTv = itemView.item_customer_full_name
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {
        val itemView = inflater.inflate(R.layout.item_customer, parent, false)
//        itemView.setOnClickListener {
//            listener.onCustomerClick(list[position])
//        }
        return WordViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
        val current = list[position]
        holder.fullNameTv.text = current.firstName + current.lastName
        holder.itemView.setOnClickListener {
            listener.onCustomerClick(list[position])
        }
    }

    internal fun setWords(words: List<Customer>) {
        this.list = words
        notifyDataSetChanged()
    }

    override fun getItemCount() = list.size
}

interface Listener {
    fun onCustomerClick(customer: Customer)
}