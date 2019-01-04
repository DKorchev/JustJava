package com.example.admin.justjava;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.jar.Attributes;

//import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {
    int quantity = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        EditText enterName = findViewById(R.id.textPersonName);
        String Name = enterName.getText().toString();

        boolean hasWhippedCream = ((CheckBox) findViewById(R.id.whipped_cream_checkbox)).isChecked();
        boolean hasChocolate = ((CheckBox) findViewById(R.id.chocolate_checkbox)).isChecked();
        String priceMessage = createOrderSummaryMessage(hasWhippedCream, hasChocolate, Name);

        String subject = "JustJava order for " + Name;
        String sendto = "admin@adeya.com.ua";
        composeEmail(subject, priceMessage, sendto);

        //displayMessage(priceMessage);
    }

    public void composeEmail(String subject, String body, String sendto) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{sendto});
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, body);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    /**
     * Calculates the price of the order.
     *
     * @param hasWhipperCream is true when user want to add whipped cream to the order
     * @param hasChocolate    is true when user want to add chocolate to the order
     */
    private int calculatePrice(boolean hasWhipperCream, boolean hasChocolate) {
        int base = 5;
        if (hasWhipperCream)
            base += 1;
        if (hasChocolate)
            base += 2;
        return quantity * base;
    }

    /**
     * This method is call when plus button is clicked
     */

    public void increment(View view) {
        if (quantity < 100) {
            quantity = quantity + 1;
            displayQuantity(quantity);
        } else
            //this will show short message (Toast), when user try to dicrement number of cofees below 1
            Toast.makeText(getApplicationContext(), "You can order maximum 100 cups of coffee", Toast.LENGTH_SHORT).show();
    }

    /**
     * This method is call when minus button is clicked
     */
    public void dicrement(View view) {
        if (quantity >= 2) {
            quantity = quantity - 1;
            displayQuantity(quantity);
        } else
            //this will show short message (Toast), when user try to dicrement number of cofees below 1
            Toast.makeText(getApplicationContext(), "You can order minimum 1 cup of coffee", Toast.LENGTH_SHORT).show();
    }

    /**
     * This method create the message, that will be displayed on the screen when order button is clicked.
     * calculatePrice(hasWhippedCream, hasChocolate) method called for order price
     */

    private String createOrderSummaryMessage(boolean hasWhippedCream, boolean hasChocolate, String Name) {
        String priceMessage = "Name: " + Name;
        priceMessage += "\nAdd whipped cream: " + hasWhippedCream;
        priceMessage += "\nAdd chocolate: " + hasChocolate;
        priceMessage += "\nQuantity: " + quantity;
        priceMessage += "\nThe price is: " + calculatePrice(hasWhippedCream, hasChocolate);
        priceMessage += "\nThank you!";
        return priceMessage;
    }

    /**
     * This method displays the given quantity value on the screen.
     */

    private void displayQuantity(int number) {
        TextView quantityTextView = findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /**
     * This method displays the given text on the screen.
     */
//    private void displayMessage(String message) {
//        TextView orderSummaryTextView = findViewById(R.id.order_summary_text_view);
//        orderSummaryTextView.setText(message);
//
//    }

}