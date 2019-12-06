package com.ethiopia.addisababa.manny.reverserecipe2;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class ReverseActivity extends AppCompatActivity {

//    TabLayout x;
    AutoCompleteTextView editText;
    ImageButton imageButton;
    Button addButton;
    Button removeButton;
    Button reverseButton;
    LinearLayout linearLayout;
    LinearLayout parent2;
    ScrollView parent3;
    int inputCount = 1;
    ArrayList<String> ingridients1;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reverse);

        editText = findViewById(R.id.editText);
        linearLayout = findViewById(R.id.parent);
        addButton = findViewById(R.id.add_button);
        parent2 = findViewById(R.id.parent2);
        reverseButton = findViewById(R.id.reverse_button);
        removeButton = findViewById(R.id.remove_button);

        String[][] x = new String[Utility.recipeList.length][];
        ingridients1 = new ArrayList <>();

        removeButton.setOnClickListener(new removeButtonListener());

        for (int i = 0; i < Utility.recipeList.length; i++) {
            String[] recipe = getResources().getString(Utility.recipeList[i]).split("::");
            String[] ingridients = recipe[1].split(";");
            String[] ingridientsFinal = new String[ingridients.length];
            for (int j = 0; j < ingridients.length; j++) {
                String[] y = ingridients[j].split("@");
                ingridientsFinal[j] = y[1];
                if(!inArrayList(ingridients1, y[1])){
                    ingridients1.add(y[1]);
                }
            }
            x[i] = ingridientsFinal;
        }
        adapter = new ArrayAdapter <String>(this,
                android.R.layout.simple_dropdown_item_1line, ingridients1);

        editText.setAdapter(adapter);

        addButton.setOnClickListener(new addButtonListenerClass());
        reverseButton.setOnClickListener(new searchButtonListener());

    }

    public boolean inList(String[] li, String item) {
        for (String li_item : li) {
            if (li_item.contains(item)) {
                return true;
            }
        }
        return false;
    }
    public int inList2(String[] li, String item) {
        int count = 0;
        for (String li_item : li) {
            if (li_item.contains(item)) {
                return count;
            }
            count++;
        }
        return -1;
    }

    class addButtonListenerClass implements View.OnClickListener{

        @SuppressLint({"ResourceAsColor", "ResourceType"})
        @Override
        public void onClick(View v) {
            if(inputCount < 4){
//                EditText editText = findViewById(R.id.editText);
//                EditText editText1 = new EditText(getApplicationContext());
////                EditText editText1 = getResources().getLayout(R.layout.text_box).get;
//
//                editText1.setLayoutParams(editText.getLayoutParams());
//                editText1.setTextColor(editText.getTextColors());
//                editText1.setHintTextColor(editText.getHintTextColors());
//
//                editText1.setHint(R.string.search_hint);
                View view = getLayoutInflater().inflate(R.layout.text_box, null);
                AutoCompleteTextView editText1 = view.findViewById(R.id.editText);
                editText1.setAdapter(adapter);

                parent2.addView(editText1);
                inputCount++;
            }
            else{
                Toast.makeText(getApplicationContext(),"Can't put Ingridients more than 4",Toast.LENGTH_LONG).show();
            }
        }
    }
    public boolean inArrayList(ArrayList<String> arrayList, String x){
        return arrayList.contains(x);
    }

    class removeButtonListener implements View.OnClickListener{
        public void onClick(View v){
            if(parent2.getChildCount() > 1){
                parent2.removeViewAt(parent2.getChildCount()-1);
                inputCount--;
            }
        }
    }

    class searchButtonListener implements View.OnClickListener{

        public void onClick(View v){
            int num = parent2.getChildCount();
            linearLayout.removeAllViewsInLayout();

            String[][] x = new String[Utility.recipeList.length][];
            String[] recipeNameList = new String[Utility.recipeList.length];

            for (int i = 0; i < Utility.recipeList.length; i++) {
                String[] recipe = getResources().getString(Utility.recipeList[i]).split("::");
                recipeNameList[i] = recipe[0];
                String[] ingridients = recipe[1].split(";");
                String[] ingridientsFinal = new String[ingridients.length];

                for (int j = 0; j < ingridients.length; j++) {
                    String[] y = ingridients[j].split("@");
                    ingridientsFinal[j] = y[1];
                }
                x[i] = ingridientsFinal;
            }



            String[] searchIngridients = new String[num];
            for(int i = 0;i < num;i++){
                EditText ingridient = (EditText) parent2.getChildAt(i);
                searchIngridients[i] = ingridient.getText().toString();
            }
            for(int i = 0;i<x.length;i++){
                boolean val = false;
                for(String ing: searchIngridients){
                    if(inList2(x[i],ing) != -1){
                        val = true;
                    }
                    else{
                        val = false;
                        break;
                    }
                }
                if(val == true){
                    Button button = new Button(getApplicationContext());
                    button.setText(""+recipeNameList[i]);
                    button.setOnClickListener(new ButtonListenerClass(Utility.recipeList[i]));
                    linearLayout.addView(button);
                }
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
            startActivity(intent);
            Toast toast = Toast.makeText(getApplicationContext(),"",Toast.LENGTH_SHORT);


        }
    }

}
