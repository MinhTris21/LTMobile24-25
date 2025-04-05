package com.example.calculator;
//CAC THU VIEN SU DUNG TRONG BAI LAM
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView display;
    private String currentNumber = "";
    private String operator = "";
    private double firstNumber = 0;
    private boolean isNewOperation = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize the display
        display = findViewById(R.id.display);

        //CAC BUTTON DAI DIEN CHO SO 1,2,3,4,5... 9
        int[] digitButtonIds = {
                R.id.btn_0, R.id.btn_1, R.id.btn_2, R.id.btn_3, R.id.btn_4,
                R.id.btn_5, R.id.btn_6, R.id.btn_7, R.id.btn_8, R.id.btn_9
        };

        for (int id : digitButtonIds) {
            Button button = findViewById(id);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (isNewOperation) {
                        currentNumber = "";
                        isNewOperation = false;
                    }
                    currentNumber += ((Button) v).getText().toString();
                    display.setText(currentNumber);
                }
            });
        }

        // BUTTON '.'
        Button btnDecimal = findViewById(R.id.btn_decimal);
        btnDecimal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isNewOperation) {
                    currentNumber = "0";
                    isNewOperation = false;
                }
                // Only add a decimal point if the current number doesn't already have one
                if (!currentNumber.contains(".")) {
                    currentNumber += ".";
                    display.setText(currentNumber);
                }
            }
        });

        // CAC BUTTON CHO PHEP CONG, TRU NHAN CHIA
        Button btnPlus = findViewById(R.id.btn_plus);
        Button btnMinus = findViewById(R.id.btn_minus);
        Button btnMultiply = findViewById(R.id.btn_multiply);
        Button btnDivide = findViewById(R.id.btn_divide);

        View.OnClickListener operatorListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!currentNumber.isEmpty()) {
                    firstNumber = Double.parseDouble(currentNumber);
                    operator = ((Button) v).getText().toString();
                    currentNumber = "";
                    display.setText(operator);
                    isNewOperation = false;
                }
            }
        };

        btnPlus.setOnClickListener(operatorListener);
        btnMinus.setOnClickListener(operatorListener);
        btnMultiply.setOnClickListener(operatorListener);
        btnDivide.setOnClickListener(operatorListener);

        // BUTTON '=', KHI BAM SE TRA VE KET QUA
        Button btnEquals = findViewById(R.id.btn_equals);
        btnEquals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!currentNumber.isEmpty() && !operator.isEmpty()) {
                    double secondNumber = Double.parseDouble(currentNumber);
                    double result = calculate(firstNumber, secondNumber, operator);
                    // Format the result to avoid unnecessary trailing zeros
                    String resultString = formatNumber(result);
                    display.setText(resultString);
                    currentNumber = resultString;
                    operator = "";
                    isNewOperation = true;
                }
            }
        });

        //BUTTON XOA, KHI BAM SE XOA HET CAC DU LIEU TREN TEXTVIEW
        Button btnDel = findViewById(R.id.btn_del);
        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentNumber = "";
                operator = "";
                firstNumber = 0;
                display.setText("0");
                isNewOperation = true;
            }
        });

        // BUTTON PHAN TRAM
        Button btnPercent = findViewById(R.id.btn_percent);
        btnPercent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!currentNumber.isEmpty()) {
                    double number = Double.parseDouble(currentNumber);
                    number = number / 100;
                    currentNumber = formatNumber(number);
                    display.setText(currentNumber);
                }
            }
        });
    }
    //HAM THUC HIEN CAC PHEP TINH TOAN
    private double calculate(double first, double second, String op) {
        switch (op) {
            case "+":
                return first + second;
            case "-":
                return first - second;
            case "X":
                return first * second;
            case "/":
                if (second != 0) {
                    return first / second;
                } else {
                    display.setText("Error");
                    return 0;
                }
            default:
                return 0;
        }
    }

    private String formatNumber(double number) {
        // If the number is a whole number, return it as an integer
        if (number == (long) number) {
            return String.valueOf((long) number);
        } else {
            // Otherwise, return the number as a string with up to 10 decimal places
            // and remove trailing zeros and unnecessary decimal point
            String result = String.format("%.10f", number);
            result = result.replaceAll("0*$", ""); // Remove trailing zeros
            result = result.replaceAll("\\.$", ""); // Remove trailing decimal point if no digits follow
            return result;
        }
    }
}