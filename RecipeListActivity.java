package com.ethiopia.addisababa.manny.reverserecipe2;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

public class RecipeListActivity extends AppCompatActivity {
    LinearLayout parentLayout;

    @TargetApi(Build.VERSION_CODES.M)
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_list);
        setTitle("Recipes");

        parentLayout = findViewById(R.id.parent);

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        int k = 1;
        for(int i: Utility.recipeList){
            Button title = new Button(this);
            title.setText(k+". "+getResources().getString(i).split("::")[0]);
            title.setTextSize(30);
            title.setTextAppearance(this,R.style.TextAppearance_AppCompat_Widget_Button);
            title.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_START);
            title.setPadding(100,40,10,40);
            title.setOnClickListener(new ButtonListenerClass(i));
            title.setLayoutParams(layoutParams);
            parentLayout.addView(title);
            k++;
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
            startActivity(intent);
            Toast toast = Toast.makeText(getApplicationContext(),"",Toast.LENGTH_SHORT);


        }
    }
}
