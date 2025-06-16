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
import com.example.recipeapp.model.mealItem.MealItem

class SubCategoryAdapter(
    private val onSubCategoryClick: (String) -> Unit,
) :
    RecyclerView.Adapter<SubCategoryAdapter.ViewHolder>() {


        
    var arrSubCategory = ArrayList<MealItem>()

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tv_dish: TextView = view.findViewById(R.id.tv_dish)
        val img_dish: ImageView = view.findViewById(R.id.img_dish)
    }


    @SuppressLint("NotifyDataSetChanged")
    fun setData(arrData: List<MealItem>) {
        arrSubCategory.clear()
        arrSubCategory.addAll(arrData)
        notifyDataSetChanged()
    }
    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_rv_sub_category, viewGroup, false)

        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        val item = arrSubCategory[position]

        viewHolder.tv_dish.text = item.strMeal

        Glide.with(viewHolder.itemView.context)
            .load(item.strMealThumb)
            .placeholder(R.drawable.dish)
            .into(viewHolder.img_dish)

        //passing id of subcategory
        viewHolder.itemView.setOnClickListener {
            onSubCategoryClick(item.idMeal) // Pass idMeal to the parent
        }

    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount():Int = arrSubCategory.size


}