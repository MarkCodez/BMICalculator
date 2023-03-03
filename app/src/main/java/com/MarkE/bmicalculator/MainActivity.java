package com.MarkE.bmicalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private TextView resultText;
    private RadioButton maleButton;
    private RadioButton femaleButton;
    private EditText ageText, feetText, inchesText, weightText;
    private Button calculateButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
        setupButtonClickListener();
    }

    private void findViews() {
        resultText = findViewById(R.id.text_view_result);

        maleButton = findViewById(R.id.radio_button_male);
        femaleButton = findViewById(R.id.radio_button_female);

        ageText = findViewById(R.id.edit_text_age);
        feetText = findViewById(R.id.edit_text_feet);
        inchesText = findViewById(R.id.edit_text_inches);
        weightText = findViewById(R.id.edit_text_weight);

        calculateButton = findViewById(R.id.button_calculate);
    }

    private void setupButtonClickListener() {
        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                double bmiResult = calculateBMI();

                // check client age
                String ageT = ageText.getText().toString();
                int age = Integer.parseInt(ageT);

                if (age >= 18)
                {
                    displayResult(bmiResult);
                }
                else
                {
                    displayGuidance(bmiResult);
                }
            }
        });


    }

    private double calculateBMI() {
        String feetT = feetText.getText().toString();
        String inchesT = inchesText.getText().toString();
        String weightT = weightText.getText().toString();

        // converting strings into integers to be applied to math
        int feet = Integer.parseInt(feetT);
        int inches = Integer.parseInt(inchesT);
        int weight = Integer.parseInt(weightT);

        // convert feet and inches into only inches
        int totalInches =  (feet * 12) + inches;

        // converting inches into meters
        double heightInMeters = (totalInches * 0.0254);

        // bmi calculation formula
        return weight / (heightInMeters * heightInMeters);
    }

    private void displayResult(double bmi){
        DecimalFormat myDecimalFormatter = new DecimalFormat("0.00");
        String bmiTextResult = myDecimalFormatter.format(bmi);

        // printing bmi result to screen
        String fullResultbmi;

        if (bmi < 18.5)
        {
            // display underweight
            fullResultbmi = bmiTextResult + " - You're underweight...";
        }
        else if (bmi < 25)
        {
            // display healthy
            fullResultbmi = bmiTextResult + " - You're healthy...";
        }
        else if (bmi < 30)
        {
            // display overweight
            fullResultbmi = bmiTextResult + " - You're overweight...";
        }
        else
        {
            // display obese
            fullResultbmi = bmiTextResult + " - You're obese, see a doctor NOW....";
        }

        resultText.setText(fullResultbmi);
    }

    private void displayGuidance(double bmi) {
        DecimalFormat myDecimalFormatter = new DecimalFormat("0.00");
        String bmiTextResult = myDecimalFormatter.format(bmi);

        String fullResultbmi;

        if (maleButton.isChecked())
        {
            // display guidance for boys
            fullResultbmi = bmiTextResult + " - You are under 18, please consult further with your doctor for the healthy BMI range for boys...";
        }
        else if (femaleButton.isChecked())
        {
            // display guidance for girls
            fullResultbmi = bmiTextResult + " - You are under 18, please consult further with your doctor for the healthy BMI range for girls...";
        }
        else
        {
            // display general guidance
            fullResultbmi = bmiTextResult + " - You are under 18, please consult with your doctor for your healthy BMI range...";
        }

        resultText.setText(fullResultbmi);
    }


}