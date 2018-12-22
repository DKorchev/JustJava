package com.example.admin.justjava;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

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
        boolean hasWhippedCream = ((CheckBox) findViewById(R.id.whipped_cream_checkbox)).isChecked();

        String priceMessage = createOrderSummaryMessage(hasWhippedCream);
        displayMessage(priceMessage);
    }

    /**
     * Calculates the price of the order.
     * @param hasWhipperCream is true when user want to add whipped cream to the order
     */
    private int calculatePrice(boolean hasWhipperCream) {
        int base = 5;
        if (hasWhipperCream == true)
            base += 1;
        return quantity * base;
    }

    /**
     * This method is call when plus button is clicked
     */

    public void increment(View view) {
        if (quantity < 100) {
            quantity = quantity + 1;
            displayQuantity(quantity);
        }
    }

    /**
     * This method is call when minus button is clicked
     */
    public void dicrement(View view) {
        if (quantity >= 2) {
            quantity = quantity - 1;
            displayQuantity(quantity);
        }

    }

    /**
     * This method create the message, that will be displayed on the screen when order button is clicked.
     * calculatePrice(hasWhippedCream) method called for order price
     */

    private String createOrderSummaryMessage(boolean hasWhippedCream) {
        String priceMessage = "Add whipped cream: " + hasWhippedCream + "\nThe price is " + calculatePrice(hasWhippedCream) + ", man!";
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
    private void displayMessage(String message) {
        TextView orderSummaryTextView = findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);

    }

}