package com.example.recipeapp.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.recipeapp.R
import com.example.recipeapp.model.categoryItem.CategoryItem

class MainCategoryAdapter(
    private val onCategoryClick: (String) -> Unit
) :
    RecyclerView.Adapter<MainCategoryAdapter.ViewHolder>() {

    var arrMainCategory = ArrayList<CategoryItem>()

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var tv_dish = view.findViewById<TextView>(R.id.tv_dish)
        var img_dish = view.findViewById<ImageView>(R.id.img_dish)

    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(arrData: List<CategoryItem>) {
        arrMainCategory.clear()
        arrMainCategory.addAll(arrData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_rv_main_category, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
//        viewHolder.tv_dish.text = arrMainCategory[position].strCategory

        val item = arrMainCategory[position]

        viewHolder.tv_dish.text = item.strCategory

        // Handle item click here
        viewHolder.itemView.setOnClickListener {
            onCategoryClick(item.strCategory)  // 🔥 send selected category to ViewModel
        }

        Glide.with(viewHolder.itemView.context)
            .load(item.strCategoryThumb)
            .placeholder(R.drawable.dish)
            .into(viewHolder.img_dish)
    }

    override fun getItemCount(): Int = arrMainCategory.size
}
