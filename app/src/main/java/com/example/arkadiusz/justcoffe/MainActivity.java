package com.example.arkadiusz.justcoffe;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int quanity =0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        display(quanity);
    }
    private void display(int quanity) {
        TextView lcd= findViewById(R.id.numberOfCoffe);
        lcd.setText(String.valueOf(quanity));
    }

    public void decrement(View view){
        if(quanity>0){quanity = quanity-1;}
        display(quanity);
    }
    public void increment(View view){
        if(quanity<10){
        quanity = quanity+1;}
        display(quanity);
    }

    public void  submitOrder(View view){

        String nameUser;
        CheckBox whippedCreamCheckBox = findViewById(R.id.whipped_cream_checkbox);
        boolean hasWhippedCream = whippedCreamCheckBox.isChecked();
        CheckBox chocolateCheckBox = findViewById(R.id.chocolate_checkbox);
        boolean hasChocolate = chocolateCheckBox.isChecked();
        EditText getNameUser = findViewById(R.id.name_text);
        nameUser= getNameUser.getText().toString();
       int price = calculatePrice(hasWhippedCream,hasChocolate);
        String priceMessage=createOrderSummary(price,hasWhippedCream,hasChocolate,nameUser);
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this

        intent.putExtra(Intent.EXTRA_SUBJECT, "Just Java Coffe order for"+nameUser);
        intent.putExtra(Intent.EXTRA_TEXT,priceMessage);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);}

       //displayMessage(priceMessage);
    }

    public int calculatePrice(boolean hasWhippedCream, boolean hasChocolate){
       int price=5;

        if(hasWhippedCream){price +=1;

        }
        if(hasChocolate){price +=2;

        }
        price = quanity*price;
       return price;


    }




    public String createOrderSummary(int price, boolean addWhippedCream, boolean hasChocolate,String nameUser){
        String priceMessage;
        priceMessage = getString(R.string.name,nameUser);
        priceMessage +="\n"+getString(R.string.quanity)+" "+quanity+"";
        priceMessage += "\n"+getString(R.string.whipped_cream)+" "+addWhippedCream;
        priceMessage += "\n"+getString(R.string.chocolate)+" "+hasChocolate;
        priceMessage += "\n"+getString(R.string.total)+" "+price;
        priceMessage += "\n"+getString(R.string.thank_you);
        return priceMessage;
    }
    }
