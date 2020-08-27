package com.example.androiduitesting

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    private var mSpinnerLabel = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        label_spinner.onItemSelectedListener = this

        // Create ArrayAdapter using the string array and default
        // spinner layout.

        // Create ArrayAdapter using the string array and default
        // spinner layout.
        val adapter = ArrayAdapter.createFromResource(
            this, R.array.labels_array,
            android.R.layout.simple_spinner_item
        )
        // Specify the layout to use when the list of choices appears.
        // Specify the layout to use when the list of choices appears.
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        label_spinner.adapter = adapter
    }

    fun showText(view: View) {
        val showString: String = editText_main.getText().toString().toString() +
                " - " + mSpinnerLabel
        // Display a Toast message with showString
        // Display a Toast message with showString
        Toast.makeText(this, showString, Toast.LENGTH_SHORT).show()
        // Set the TextView to showString.
        // Set the TextView to showString.
        text_phonelabel.setText(showString)
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {

    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        mSpinnerLabel = p0!!.getItemAtPosition(p2).toString();
        showText(p1!!)
    }
}