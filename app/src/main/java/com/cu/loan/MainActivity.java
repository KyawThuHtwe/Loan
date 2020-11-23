package com.cu.loan;

import androidx.appcompat.app.AppCompatActivity;
import me.myatminsoe.mdetect.MDetect;
import me.myatminsoe.mdetect.Rabbit;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    EditText etLoan, etInterest, etPayment;
    TextView tv;
    Button calculate;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MDetect.INSTANCE.init(this);

        etLoan=findViewById(R.id.etLoan);
        etInterest=findViewById(R.id.etInterest);
        etPayment=findViewById(R.id.etPayment);
        tv=findViewById(R.id.tv);
        calculate=findViewById(R.id.calculate);

        if(!MDetect.INSTANCE.isUnicode()){
            etLoan.setHint(Rabbit.uni2zg(etLoan.getHint().toString()));
            etInterest.setHint(Rabbit.uni2zg(etInterest.getHint().toString()));
            etPayment.setHint(Rabbit.uni2zg(etPayment.getHint().toString()));
            calculate.setText(Rabbit.uni2zg(calculate.getText().toString()));
        }
    }
    public void onClick(View v){
        String out="";
        double loan=Double.parseDouble(etLoan.getText().toString());
        double interest=Double.parseDouble(etInterest.getText().toString());
        double payment=Double.parseDouble(etPayment.getText().toString());
        int month=0;
        while(loan>0){
            month++;
            loan-=(payment-(loan*interest/100));
            if(loan<=0){
                out+="("+month+") "+(payment+loan) +"သွင်းရန်။ ပြီး။";
                break;
            }
            out+="("+month+") "+payment +"သွင်းရန်။ "+loan +" ကျန်။\n\n";
        }
        tv.setText(out);
        if(!MDetect.INSTANCE.isUnicode()){
            tv.setText(Rabbit.uni2zg(out));
        }

    }
}