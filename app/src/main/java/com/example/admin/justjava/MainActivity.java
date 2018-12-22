package com.example.admin.justjava;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

//import java.text.NumberFormat;
/**
 * TESTING GITHUB!!!
 */
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
        String message = "The price is " + calculatePrice() + ", man!";
        message += " Thanks. Love you!";
        displayMessage(message);
    }

    /**
     * Calculates the price of the order.
     *
     * @param
     */
    private int calculatePrice() {
        return quantity * 5;
    }

    public void increment(View view) {
        if (quantity < 100) {
            quantity = quantity + 1;
            displayQuantity(quantity);
        }
    }


    public void dicrement(View view) {
        if (quantity >= 2) {
            quantity = quantity - 1;
            displayQuantity(quantity);
        }

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