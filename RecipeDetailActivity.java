package com.ethiopia.addisababa.manny.reverserecipe2;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class RecipeDetailActivity extends AppCompatActivity {
    TextView ingridientsTextView;
    TextView stepsTextView;
    FloatingActionButton addToFavorite;
    ImageView foodImage;
    Button nextButton;
    Button prevButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);

        foodImage = findViewById(R.id.food_image);
        ingridientsTextView = findViewById(R.id.ingridients);
        stepsTextView = findViewById(R.id.steps);
        addToFavorite = findViewById(R.id.add_to_favorites);
        nextButton = findViewById(R.id.next_button);
        prevButton = findViewById(R.id.prev_button);

//        foodImage.setOnTouchListener(new OnSwipeTouchListener(this){
//            public void onSwipLeft(){
//                Toast.makeText(getApplicationContext(), "left", Toast.LENGTH_SHORT);
//            }
//        });

        Intent intent = getIntent();
        int id = intent.getIntExtra("recipe_id",0);
        setContent(id);

    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    public void goToNext(int currId){

//        Intent intent = new Intent(this, RecipeDetailActivity.class);
        int id = (currId + 1) % Utility.recipeList.length;
        setContent(Utility.recipeList[id]);
        //        intent.putExtra("recipe_id", Utility.recipeList[id]);
        //        startActivity(intent);
//        finish();
    }
    public void goToPrev(int currId){
//        Intent intent = new Intent(this, RecipeDetailActivity.class);
        int id = (currId - 1) % Utility.recipeList.length;
        if (currId == 0){
            id = Utility.recipeList.length - 1;
        }
        setContent(Utility.recipeList[id]);
//        intent.putExtra("recipe_id", Utility.recipeList[id]);
//        startActivity(intent);
//        finish();
    }


    public void setContent(int id){
        int k;
        for(k = 0;k < Utility.recipeList.length;k++){
            if(Utility.recipeList[k] == id){
                break;
            }
        }
        try {

            int image = Utility.imageList[k];
            if (image != 0){
                foodImage.setImageResource(image);
            }
            else{
                foodImage.setImageResource(R.mipmap.ic_launcher);
            }
        }
        catch (Exception ex){

        }
        final String[] recipe = getResources().getString(id).split("::");


        final String recipeName = recipe[0];
        setTitle(recipeName);
        String[] ingridients = recipe[1].split(";");
        String ingridientsText = "";
        for(String i: ingridients){
            String[] j = i.split("@");
            i = "";
            for(String l: j){
                i = i + " " + l;
            }
            ingridientsText +=  i + "\n";
        }
        String steps = recipe[2];

//        recipeNameTextView = findViewById(R.id.recipe_name);

        final int finalK = k;
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToNext(finalK);
            }
        });

        prevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToPrev(finalK);
            }
        });


        MyDBHandler myDBHandler2 = new MyDBHandler(getApplicationContext(),null,null,1);
        Recipe recipe2 = new Recipe(recipeName);
        if(myDBHandler2.inDatabase(new Recipe(recipe2.get_productName()))){
//            addToFavorite.setText("Remove from Favorites");
            addToFavorite.setImageResource(R.drawable.ic_favorite_button_after);
        }
        else{
//            addToFavorite.setText("Add to Favorite");
            addToFavorite.setImageResource(R.drawable.ic_favorites_button_before);
        }

        addToFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDBHandler myDBHandler = new MyDBHandler(getApplicationContext(),null,null,1);
                Recipe recipe1 = new Recipe(recipeName);

                if(!myDBHandler.inDatabase(new Recipe(recipe1.get_productName()))){
                    myDBHandler.addRecipe(new Recipe(recipeName));
//                        addToFavorite.setText("Remove from Favorites");
                    addToFavorite.setImageResource(R.drawable.ic_favorite_button_after);
                }
                else{
                    myDBHandler.deleteRecipe(new Recipe(recipeName).get_productName());
//                        addToFavorite.setText("Add to Favorites");
                    addToFavorite.setImageResource(R.drawable.ic_favorites_button_before);
                }
            }
        });


//        recipeNameTextView.setText(recipeName);
        ingridientsTextView.setText(ingridientsText.substring(0,ingridientsText.length()-1));
        stepsTextView.setText(steps);
    }
}