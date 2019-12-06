package com.ethiopia.addisababa.manny.reverserecipe2;

import android.content.Intent;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.SearchView;

public class MainActivity extends AppCompatActivity {
    Button RevButton;
    Button ViewRecButton;
    Button ViewFavsButton;
    ImageButton RevImageBtn;
    ImageButton ViewImageBtn;
    ImageButton FavImageBtn;
    private android.support.v7.widget.SearchView searchView;
    private android.support.v7.widget.SearchView.SearchAutoComplete searchAutoComplete;

    LinearLayout RevLayout;
    LinearLayout ViewRecLayout;
    LinearLayout ViewFavsLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RevButton = findViewById(R.id.reverse_button);
        ViewRecButton = findViewById(R.id.view_recipes_button);
        ViewFavsButton = findViewById(R.id.favorites_button);
        RevImageBtn = findViewById(R.id.reverse_imageButton);
        ViewImageBtn = findViewById(R.id.listImageButton);
        FavImageBtn = findViewById(R.id.favorites_imagebutton);

        RevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ReverseActivity.class);
                startActivity(intent);
            }
        });
        RevImageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ReverseActivity.class);
                startActivity(intent);
            }
        });

        ViewRecButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RecipeListActivity.class);
                startActivity(intent);
            }
        });
        ViewImageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RecipeListActivity.class);
                startActivity(intent);
            }
        });

        ViewFavsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), FavoritesActivity.class);
                startActivity(intent);
            }
        });
        FavImageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), FavoritesActivity.class);
                startActivity(intent);
            }
        });
    }

//    public boolean onCreateOptionsMenu(Menu menu){
//        super.onCreateOptionsMenu(menu);
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.options_menu, menu);
//        MenuItem item = menu.findItem(R.id.action_search);
//        searchView = (SearchView) MenuItemCompat.getActionView(item);
//        searchAutoComplete = (android.support.v7.widget.SearchView.SearchAutoComplete) searchView.findViewById
//                (android.support.v7.appcompat.R.id.search_src_text);
//
////        searchAutoComplete.setDrop
//        searchAutoComplete.setDropDownAnchor(R.id.action_search);
//        String[] coun = {"Belgium","German"};
////        searchAutoComplete.setThreshold(0);
//        ArrayAdapter<String> adapter = new ArrayAdapter <String>(this, android.R.layout.simple_list_item_1,coun);
//
//        searchAutoComplete.setAdapter(adapter);
//        return true;
//    }
}
