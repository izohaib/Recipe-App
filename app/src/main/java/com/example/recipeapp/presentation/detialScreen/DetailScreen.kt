package com.example.recipeapp.presentation.detialScreen

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.recipeapp.presentation.homeScreen.HomeActivity
import com.example.recipeapp.R
import com.example.recipeapp.local.RecipeDatabase
import com.example.recipeapp.netowrk.RetrofitClient
import com.example.recipeapp.repository.CategoryRepository
import com.makeramen.roundedimageview.RoundedImageView

class DetailScreen : AppCompatActivity() {

    private lateinit var viewModel: DetailViewModel

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detail_screen)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // 1️⃣ Get Meal ID
        val mealId = intent.getStringExtra("meal_id") ?: return

        // 2️⃣ Setup ViewModel
        val dao = RecipeDatabase.Companion.getDatabase(applicationContext).recipesDao()
        val api = RetrofitClient.api
        val repository = CategoryRepository(api, dao)
        val factory = DetailViewModelFactory(repository)
        viewModel = ViewModelProvider(this, factory)[DetailViewModel::class.java]

        // 3️⃣ Fetch meal detail
        viewModel.fetchMealDetailById(mealId)

        // 4️⃣ Observe and update UI
        viewModel.mealDetail.observe(this) { meal ->
            if (meal != null) {
                findViewById<TextView>(R.id.categoryName).text = meal.strCategory
                findViewById<TextView>(R.id.tv_dish).text = meal.strMeal
                findViewById<TextView>(R.id.tvInstructions).text = meal.strInstructions

                //open the youtube link
                val youtubeUrl = meal.strYoutube  // Save the URL

                val youtubeButton = findViewById<Button>(R.id.btnYoutube)
                youtubeButton.text = "YouTube"  // Set correct label

                youtubeButton.setOnClickListener {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(youtubeUrl))
                    intent.setPackage("com.google.android.youtube")  // Try to open in YouTube app

                    // If YouTube app not available, open in browser
                    if (intent.resolveActivity(packageManager) != null) {
                        startActivity(intent)
                    } else {
                        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(youtubeUrl)))
                    }
                }


                // Load image
                val imageView = findViewById<RoundedImageView>(R.id.imgItem)
                Glide.with(this)
                    .load(meal.strMealThumb)
                    .placeholder(R.drawable.img_1)
                    .error(R.drawable.img_1)
                    .into(imageView)


                // Create ingredients list with safe casting
                val ingredients = listOf(
                    meal.strIngredient1,
                    meal.strIngredient2,
                    meal.strIngredient3,
                    meal.strIngredient4,
                    meal.strIngredient5,
                    meal.strIngredient6,
                    meal.strIngredient7,
                    meal.strIngredient8,
                    meal.strIngredient9,
                    meal.strIngredient10,
                    meal.strIngredient11,
                    meal.strIngredient12,
                    meal.strIngredient13,
                    meal.strIngredient14,
                    meal.strIngredient15,
                    meal.strIngredient16 as? String,
                    meal.strIngredient17 as? String,
                    meal.strIngredient18 as? String,
                    meal.strIngredient19 as? String,
                    meal.strIngredient20 as? String
                )

                // ✅ Step 2: Create measures list with safe casting
                val measures = listOf(
                    meal.strMeasure1,
                    meal.strMeasure2,
                    meal.strMeasure3,
                    meal.strMeasure4,
                    meal.strMeasure5,
                    meal.strMeasure6,
                    meal.strMeasure7,
                    meal.strMeasure8,
                    meal.strMeasure9,
                    meal.strMeasure10,
                    meal.strMeasure11,
                    meal.strMeasure12,
                    meal.strMeasure13,
                    meal.strMeasure14,
                    meal.strMeasure15,
                    meal.strMeasure16 as? String,
                    meal.strMeasure17 as? String,
                    meal.strMeasure18 as? String,
                    meal.strMeasure19 as? String,
                    meal.strMeasure20 as? String
                )

                // ✅ Step 3: Combine ingredients + measures and filter out blanks
                val finalIngredients =
                    ingredients.zip(measures)  // pair them like (ingredient1, measure1)
                    .filter { it.first != null && it.first.toString().isNotBlank() }  // only keep non-empty ingredients
                    .map { (ingredient, measure) ->
                        "• ${measure.orEmpty().trim()} ${ingredient.orEmpty().trim()}"
                    }
                    .joinToString("\n")  // add line breaks between each

                // ✅ Step 4: Set to TextView
                findViewById<TextView>(R.id.tvIngredients).text = finalIngredients
            }
        }

        // go back to the screen using the back button
        findViewById<ImageButton>(R.id.imgToolbarBtnBack).setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
            finish()
        }

    }
}