package com.example.android.justjava;
import java.text.NumberFormat;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {
    int quantity = 2;
    Boolean isWhipCreamchecked = false;
    Boolean isChocklateChecked = false;
    int price_Whipcream = 1;
    int price_chocolate =2;
    String name=null;
    //String summary=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    //Toast toast = Toast.makeText(getApplicationContext(),"submitting order",Toast.LENGTH_SHORT);
    /**
     * This method is called when the order button is clicked.
     */

  /*  public int calculatePrice(){
    return quantity*5;
    }*/
    public String createOrderSummary(int quantity, Boolean isWhipCreamchecked,Boolean isChocklateChecked, String name){
        int price;
        if(isWhipCreamchecked ==true &&isChocklateChecked==true){
        price = (quantity + price_chocolate+ price_Whipcream)*5;
        }
       else if (isWhipCreamchecked==true && isChocklateChecked==false){
            price = (quantity+ price_Whipcream)*5;
        }
        else if (isWhipCreamchecked==false && isChocklateChecked==true){
            price = (quantity+ price_chocolate)*5;
        }
        else{
            price = quantity*5;
        }
       //summary = "Name:-" + name + "\nquantity =" + quantity+ "\nWhipped cream ordered :- "+ isWhipCreamchecked + "\n Chocolate ordered :- "+ isChocklateChecked +"\ntotal: "+ NumberFormat.getCurrencyInstance().format(price)+"\nthank you!";

        String summary;
        summary = getString(R.string.ordersummary,name,quantity,isWhipCreamchecked,isChocklateChecked,NumberFormat.getCurrencyInstance().format(price));
        return summary;
    }
    public void submitOrder(View view) {
        CheckBox whippedCream = (CheckBox) findViewById(R.id.checkbox_whippedCream);
        CheckBox chocolate = (CheckBox) findViewById(R.id.checkbox_Chocolate);
         isWhipCreamchecked = whippedCream.isChecked();
        isChocklateChecked = chocolate.isChecked();
        EditText nameTextbox = (EditText) findViewById(R.id.name_text_input);
        name = nameTextbox.getText().toString();
        String order_summary = createOrderSummary(quantity,isWhipCreamchecked,isChocklateChecked,name);
        //displayMessage(createOrderSummary(quantity,isWhipCreamchecked,isChocklateChecked,name));
            Intent email_intent = new Intent(Intent.ACTION_SENDTO);
            email_intent.setData(Uri.parse("mailto:")); // only email apps should handle this
            email_intent.putExtra(Intent.EXTRA_TEXT,order_summary);
            email_intent.putExtra(Intent.EXTRA_SUBJECT, name);
            if (email_intent.resolveActivity(getPackageManager()) != null) {
                startActivity(email_intent);
            }
        }

    public void increment(View view){
       if(quantity==100){
           Toast.makeText(this, "no of coffees can not be greater than 100",Toast.LENGTH_SHORT).show();
           return;
       }
        quantity =quantity +1;
        display(quantity);
    }
    public void decrement(View view) {
        if (quantity ==1) {
           Toast.makeText(this, "no of coffees can not be less than 2",Toast.LENGTH_SHORT).show();
            return;
        }
        quantity = quantity - 1;
        display(quantity);
    }
    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }
    /**
     * This method displays the given price on the screen.
     */

    /**
     * This method displays the given text on the screen.
     */
   /* private void displayMessage(String message) {
        TextView orderTextView = (TextView) findViewById(R.id.order_text_view);
        orderTextView.setText(message);
    }*/
}
