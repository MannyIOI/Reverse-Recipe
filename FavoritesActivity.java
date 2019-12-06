package com.ethiopia.addisababa.manny.reverserecipe2;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

public class FavoritesActivity extends AppCompatActivity {
    LinearLayout parent;
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);
        setTitle("Favorites");
        parent = findViewById(R.id.parent);

        MyDBHandler myDBHandler = new MyDBHandler(getApplicationContext(),null,null,1);
        String[] recipes = myDBHandler.getAllRecipes();
        int count = 1;
        for(int i: Utility.recipeList){
            String recipe_single = getResources().getString(i);
            String recipeName = recipe_single.split("::")[0];
            if(myDBHandler.inDatabase(new Recipe(recipeName))){
                Button title = new Button(this);
                title.setText(count++ +". "+recipeName);
                title.setTextAppearance(this,R.style.Widget_AppCompat_Button);
                title.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_START);
                title.setPadding(100,40,10,40);
                title.setOnClickListener(new ButtonListenerClass(i));
                parent.addView(title);
            }
        }
    }


    class ButtonListenerClass implements View.OnClickListener{
        int id;
        public ButtonListenerClass(int id) {
            this.id = id;
        }

        @Override

        public void onClick(View v) {
            Intent intent = new Intent(getApplicationContext(), RecipeDetailActivity.class);
            intent.putExtra("recipe_id",this.id);
            startActivityForResult(intent,1);
            Toast toast = Toast.makeText(getApplicationContext(),"",Toast.LENGTH_SHORT);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        parent.removeAllViewsInLayout();
        MyDBHandler myDBHandler = new MyDBHandler(getApplicationContext(),null,null,1);
        String[] recipes = myDBHandler.getAllRecipes();
        int count = 1;
        for(int i: Utility.recipeList){
            String recipe_single = getResources().getString(i);
            String recipeName = recipe_single.split("::")[0];
            if(myDBHandler.inDatabase(new Recipe(recipeName))){
                Button title = new Button(this);
                title.setText(count++ +". "+recipeName);
                title.setTextAppearance(this,R.style.Widget_AppCompat_Button);
                title.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_START);
                title.setPadding(100,40,10,40);
                title.setOnClickListener(new ButtonListenerClass(i));
                parent.addView(title);
            }
        }
    }
}
