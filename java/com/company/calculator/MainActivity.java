package com.company.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private Button btn0, btn1, btn2, btn3,btn4, btn5, btn6, btn7, btn8, btn9,
                        btnAc, btnDel, btnDiv, btnMulti,btnSub, btnAdd,btnEquals, btnDot;

    private TextView textViewHistory, textViewResult;

    private  String num=null;

    double firstNum=0;
    double finalNum=0;
    String status=null;
    boolean operator=false;

    DecimalFormat myFormatter=new DecimalFormat("######.######");

    String history, currentResult;

    boolean dot=true;
    boolean btnAcControl=true;

    boolean btnEqualsControl=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn0=findViewById(R.id.btn0);
        btn1=findViewById(R.id.btn1);
        btn2=findViewById(R.id.btn2);
        btn3=findViewById(R.id.btn3);
        btn4=findViewById(R.id.btn4);
        btn5=findViewById(R.id.btn5);
        btn6=findViewById(R.id.btn6);
        btn7=findViewById(R.id.btn7);
        btn8=findViewById(R.id.btn8);
        btn9=findViewById(R.id.btn9);

        btnAdd=findViewById(R.id.btnAdd);
        btnSub=findViewById(R.id.btnSub);
        btnMulti=findViewById(R.id.btnMulti);
        btnDiv=findViewById(R.id.btnDiv);

        btnAc=findViewById(R.id.btnAc);
        btnDel=findViewById(R.id.btnDel);
        btnDot=findViewById(R.id.btnDot);
        btnEquals=findViewById(R.id.btnEquals);

        textViewHistory=findViewById(R.id.textViewHistory);
        textViewResult=findViewById(R.id.textViewResult);

        btn0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numClick("0");
            }
        });

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numClick("1");
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numClick("2");
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numClick("3");
            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numClick("4");
            }
        });

        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numClick("5");
            }
        });

        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numClick("6");
            }
        });

        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numClick("7");
            }
        });

        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numClick("8");
            }
        });

        btn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numClick("9");
            }
        });

        btnAc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                num=null;
                status=null;
                textViewResult.setText("0");
                textViewHistory.setText("");
                firstNum=0;
                finalNum=0;
                dot=true;
                btnAcControl=true;

            }
        });

        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (btnAcControl){
                    textViewHistory.setText("0");
                }
                else {

                    num=num.substring(0,num.length()-1);

                    if (num.length()==0){
                        btnDel.setClickable(false);
                    } else if (num.contains(".")) {
                        dot=false;
                    }else {
                        dot=true;
                    }

                    textViewResult.setText(num);


                }

            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                history=textViewHistory.getText().toString();
                currentResult=textViewResult.getText().toString();
                textViewHistory.setText(history+currentResult+"+");

                if (operator){
                    if (status=="division"){
                        division();
                    } else if (status=="multiplication") {
                        multiplication();
                    } else if (status=="subtraction") {
                        subtraction();
                    }
                    else {
                        addition();
                    }
                }

                status="sum";
                operator=false;
                num=null;
                dot=true;

            }
        });

        btnSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                history=textViewHistory.getText().toString();
                currentResult=textViewResult.getText().toString();
                textViewHistory.setText(history+currentResult+"-");

                if (operator){
                    if (status=="division"){
                        division();
                    } else if (status=="multiplication") {
                        multiplication();
                    } else if (status=="sum") {
                        addition();
                    }
                    else {
                        subtraction();
                    }
                }

                status="subtraction";
                operator=false;
                num=null;
                dot=true;

            }
        });

        btnMulti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                history=textViewHistory.getText().toString();
                currentResult=textViewResult.getText().toString();
                textViewHistory.setText(history+currentResult+"x");

                if (operator){
                    if (status=="division"){
                        division();
                    } else if (status=="sum") {
                        addition();
                    } else if (status=="subtraction") {
                        subtraction();
                    }
                    else {
                        multiplication();
                    }
                }

                status="multiplication";
                operator=false;
                num=null;
                dot=true;


            }
        });

        btnDiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                history=textViewHistory.getText().toString();
                currentResult=textViewResult.getText().toString();
                textViewHistory.setText(history+currentResult+"/");

                if (operator){
                    if (status=="multiplication"){
                        multiplication();
                    } else if (status=="sum") {
                        addition();
                    } else if (status=="subtraction") {
                        subtraction();
                    }
                    else {
                        division();
                    }
                }

                status="division";
                operator=false;
                num=null;
                dot=true;


            }
        });

        btnDot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (dot){
                    if (num==null){
                        num="0.";
                    }
                    else {
                        num=num+".";
                    }

                }

                textViewResult.setText(num);
                dot=false;
            }
        });

        btnEquals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (operator){
                    if(status=="sum"){
                        addition();
                    } else if (status=="subtraction") {
                        subtraction();
                    } else if (status=="multiplication") {
                        multiplication();
                    } else if (status=="division") {
                        division();
                    }
                    else {
                        finalNum=Double.parseDouble(textViewResult.getText().toString());
                    }

                    operator=false;
                    btnEqualsControl=true;
                }

            }
        });
    }

    public void numClick(String view){

        if (num==null){
            num=view;
        } else if (btnEqualsControl) {
            firstNum=0;
            finalNum=0;
            num=view;
        } else {
            num=num+view;
        }
        textViewResult.setText(num);
        operator=true;
        btnAcControl=false;
        btnDot.setClickable(true);
        btnEqualsControl=false;
    }

    //Addition Method
    public  void addition(){

        finalNum= Double.parseDouble(textViewResult.getText().toString());
        firstNum=firstNum+finalNum;

        textViewResult.setText(myFormatter.format(firstNum));
    }

    //Subtraction Method
    public  void subtraction(){

        if (firstNum==0){
            firstNum=Double.parseDouble(textViewResult.getText().toString());
        }
        else {
            finalNum=Double.parseDouble(textViewResult.getText().toString());
            firstNum=firstNum-finalNum;
        }
        textViewResult.setText(myFormatter.format(firstNum));
    }

    //Multiplication Method
    public  void multiplication(){

        if (firstNum==0){
            firstNum=1;
            finalNum=Double.parseDouble(textViewResult.getText().toString());
            firstNum=firstNum*finalNum;
        }
        else {
            finalNum=Double.parseDouble(textViewResult.getText().toString());
            firstNum=firstNum*finalNum;
        }
        textViewResult.setText(myFormatter.format(firstNum));
    }

    //Division Method
    public void division(){

        if (firstNum==0){
            finalNum=Double.parseDouble(textViewResult.getText().toString());
            firstNum=finalNum/1;
        }
        else {
            finalNum=Double.parseDouble(textViewResult.getText().toString());
            firstNum=firstNum/finalNum;
        }
        textViewResult.setText(myFormatter.format(firstNum));
    }

}