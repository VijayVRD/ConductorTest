package com.example.conductor.controllers

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bluelinelabs.conductor.RouterTransaction
import com.bluelinelabs.conductor.changehandler.FadeChangeHandler
import com.example.conductor.R
import com.example.conductor.controllers.base.BaseController
import com.example.conductor.databinding.ControllerChildBinding
import com.example.conductor.databinding.RowHomeBinding

class ChildController(args: Bundle) : BaseController<ControllerChildBinding>(args) {


    constructor(title: String, backgroundColor: Int) : this(
        Bundle().apply {
            putString(KEY_TITLE, title)
            putInt(KEY_BG_COLOR, backgroundColor)
        }
    )

    override fun inflateView(
        inflater: LayoutInflater,
        container: ViewGroup
    ): ControllerChildBinding {
        return DataBindingUtil.inflate(inflater,
            R.layout.controller_child, container, false)
    }

    override fun onViewBound(view: View) {
        super.onViewBound(view)

        binding.tvTitle.text = args.getString(KEY_TITLE)

        var bgColor = args.getInt(KEY_BG_COLOR)
        view.setBackgroundColor(bgColor)
        binding.recyclerView.apply {
            setHasFixedSize(true)
            adapter = AdditionalModulesAdapter(
                LayoutInflater.from(view.context),
                arrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 12, 11, 13, 14, 15, 16, 17, 18, 19, 20)
            )
        }
    }

    fun onModelRowClick(model: Int) {
        router.pushController(
            RouterTransaction.with(DragDismissController())
                .pushChangeHandler(FadeChangeHandler())
                .popChangeHandler(FadeChangeHandler())
        )
    }

    internal inner class AdditionalModulesAdapter(
        private val inflater: LayoutInflater,
        private val items: Array<Int>
    ) :
        RecyclerView.Adapter<AdditionalModulesAdapter.ViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            return ViewHolder(inflater.inflate(R.layout.row_home, parent, false))
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.bind(items[position])
        }

        override fun getItemCount(): Int {
            return items.size
        }

        internal inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

            val binding: RowHomeBinding = DataBindingUtil.bind(itemView)!!

            fun bind(item: Int) {
                with(binding) {
                    tvTitle.text = "$item bla bla bla"
                    rowRoot.setOnClickListener { onModelRowClick(items[adapterPosition]) }
                }
            }
        }
    }

    companion object {

        private val KEY_TITLE = "ChildController.title"
        private val KEY_BG_COLOR = "ChildController.bgColor"
    }
}
