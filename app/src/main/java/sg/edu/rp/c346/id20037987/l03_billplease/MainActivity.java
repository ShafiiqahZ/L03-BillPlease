package sg.edu.rp.c346.id20037987.l03_billplease;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    EditText amountTxt;
    EditText numTxt;
    ToggleButton svsBtn;
    ToggleButton gstBtn;
    TextView discountTxt;
    RadioGroup paymentMethod;
    Button splitBtn;
    Button resetBtn;
    TextView view1;
    TextView view2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        amountTxt = findViewById(R.id.editAmount);
        numTxt = findViewById(R.id.editNum);
        svsBtn = findViewById(R.id.toggleButton);
        gstBtn = findViewById(R.id.toggleButton2);
        discountTxt = findViewById(R.id.displayDiscount);
        paymentMethod = findViewById(R.id.paymentMethod);
        splitBtn = findViewById(R.id.splitButton);
        resetBtn = findViewById(R.id.resetButton);
        view1 = findViewById(R.id.textView4);
        view2 = findViewById(R.id.textView2);

        splitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               //code for the action
               String stringAmount = amountTxt.getText().toString();
               String stringNum = numTxt.getText().toString();
               double disc = 0;

               if((svsBtn.isChecked() == true && gstBtn.isChecked() == false) || (gstBtn.isChecked() == true && svsBtn.isChecked() == false)) {
                   if (svsBtn.isChecked() == false){
                       disc = 0.1;
                       discountTxt.setText(10 + "%");
                   } else if (gstBtn.isChecked() == false){
                       disc = 0.07;
                       discountTxt.setText(7 + "%");
                   }
               } else {
                   disc = 0;
                   discountTxt.setText(0 + "%");
               }

               double additionCharge = Double.parseDouble(stringAmount) * disc;
               double totalPrice = Double.parseDouble(stringAmount) + additionCharge;
               double endTotal = totalPrice / Double.parseDouble(stringNum);
               String stringEndTotal = String.format("Total Bill: $%.2f",totalPrice);
               view1.setText(stringEndTotal);

               int checkedRadioId = paymentMethod.getCheckedRadioButtonId();
               if(checkedRadioId == R.id.cashRB) {
                   String stringTotal = String.format("Each Pays: $%.2f in cash.",endTotal);
                   view2.setText(stringTotal);
               } else if (checkedRadioId == R.id.payNowRB) {
                   String stringTotal = String.format("Each Pays: $%.2f via PayNow to 912345678",endTotal);
                   view2.setText(stringTotal);
               }
            }
        });

        resetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                amountTxt.setText("");
                numTxt.setText("");
                svsBtn.setChecked(false);
                gstBtn.setChecked(false);
                discountTxt.setText("");
                paymentMethod.check(R.id.paymentMethod);
                view1.setText("Total Bill: $");
                view2.setText("Each Pays: $");
            }
        });
    }
}