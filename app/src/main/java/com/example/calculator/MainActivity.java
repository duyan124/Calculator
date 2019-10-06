package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.BaseInputConnection;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    EditText edtInput;
    TextView tvResult;
    Button btnNum1,btnNum2,btnNum3,btnNum4,btnNum5,btnNum6,btnNum7,btnNum8,btnNum9,btnNum0,
            btnDot, btnPlus, btnSub, btnMul,btnDiv,btnClear,btnClearall,btnEqual;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtInput = (EditText) findViewById(R.id.edt_input);
        tvResult = (TextView) findViewById(R.id.tv_result);
        //Init Widget
        btnNum0 = (Button) findViewById(R.id.btn_num0);
        btnNum1 = (Button) findViewById(R.id.btn_num1);
        btnNum2 = (Button) findViewById(R.id.btn_num2);
        btnNum3 = (Button) findViewById(R.id.btn_num3);
        btnNum4 = (Button) findViewById(R.id.btn_num4);
        btnNum5 = (Button) findViewById(R.id.btn_num5);
        btnNum6 = (Button) findViewById(R.id.btn_num6);
        btnNum7 = (Button) findViewById(R.id.btn_num7);
        btnNum8 = (Button) findViewById(R.id.btn_num8);
        btnNum9 = (Button) findViewById(R.id.btn_num9);

        btnPlus = (Button) findViewById(R.id.btn_plus);
        btnSub = (Button) findViewById(R.id.btn_sub);
        btnMul = (Button) findViewById(R.id.btn_mul);
        btnDiv = (Button) findViewById(R.id.btn_div);

        btnDot = (Button) findViewById(R.id.btn_dot);
        btnEqual = (Button) findViewById(R.id.btn_equal);
        btnClear = (Button) findViewById(R.id.btn_clear);
        btnClearall = (Button) findViewById(R.id.btn_clearall);

        //SetEventOnClickListener
        btnNum0.setOnClickListener(this);
        btnNum1.setOnClickListener(this);
        btnNum2.setOnClickListener(this);
        btnNum3.setOnClickListener(this);
        btnNum4.setOnClickListener(this);
        btnNum5.setOnClickListener(this);
        btnNum6.setOnClickListener(this);
        btnNum7.setOnClickListener(this);
        btnNum8.setOnClickListener(this);
        btnNum9.setOnClickListener(this);

        btnPlus.setOnClickListener(this);
        btnSub.setOnClickListener(this);
        btnMul.setOnClickListener(this);
        btnDiv.setOnClickListener(this);

        btnDot.setOnClickListener(this);
        btnEqual.setOnClickListener(this);
        btnClear.setOnClickListener(this);
        btnClearall.setOnClickListener(this);




    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_num0:
                edtInput.append("0");
                break;
            case R.id.btn_num1:
                edtInput.append("1");
                break;
            case R.id.btn_num2:
                edtInput.append("2");
                break;
            case R.id.btn_num3:
                edtInput.append("3");
                break;
            case R.id.btn_num4:
                edtInput.append("4");
                break;
            case R.id.btn_num5:
                edtInput.append("5");
                break;
            case R.id.btn_num6:
                edtInput.append("6");
                break;
            case R.id.btn_num7:
                edtInput.append("7");
                break;
            case R.id.btn_num8:
                edtInput.append("8");
                break;
            case R.id.btn_num9:
                edtInput.append("9");
                break;
            case R.id.btn_plus:
                edtInput.append("+");
                break;
            case R.id.btn_sub:
                edtInput.append("-");
                break;
            case R.id.btn_mul:
                edtInput.append("x");
                break;
            case R.id.btn_div:
                edtInput.append("/");
                break;
            case R.id.btn_dot:
                edtInput.append(".");
                break;
            case R.id.btn_clear:
                BaseInputConnection textFieldInputConnection = new BaseInputConnection(edtInput, true);
                textFieldInputConnection.sendKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_DEL));
                break;
            case R.id.btn_clearall:
                edtInput.setText("");
                break;
            case R.id.btn_equal:
                double result=0;
                addOperation(edtInput.getText().toString());
                addNumber(edtInput.getText().toString());
                //tính toán biểu thức
                if(arrOperation.size()>=arrNumber.size() || arrOperation.size()<1){
                    tvResult.setText("Lỗi định dạng");
                }
                else {

                    for (int i = 0; i < (arrNumber.size() - 1); i++) {
                        switch (arrOperation.get(i)) {
                            case "+":
                                if (i==0)
                                {
                                    result = arrNumber.get(i)+arrNumber.get(i+1);
                                }
                                else
                                    result = result + arrNumber.get(i+1);

                                break;
                            case "-":
                                if (i == 0) {
                                    result = arrNumber.get(i) - arrNumber.get(i + 1);
                                } else {
                                    result = result - arrNumber.get(i + 1);
                                }
                                break;
                            case "x":
                                    if (i==0)
                                    result =arrNumber.get(i) * arrNumber.get(i + 1);
                                    else {
                                        result = result * arrNumber.get(i+1);
                                    }
                                break;
                            case "/":
                                if (i==0){
                                    result = arrNumber.get(i)/arrNumber.get(i+1);
                                }
                                else
                                    result = result / arrNumber.get(i + 1);
                                break;
                            default:
                                break;
                        }
                    }
                    }
                    tvResult.setText(result + "");
                }
        }
    //mảng chứa các phép tính +,-,x,/
    public ArrayList<String> arrOperation;
    //mảng chứa số
    public ArrayList<Double> arrNumber;

    //lưu phép tính vào mảng arrOperation
    public int addOperation(String input)
    {
        arrOperation = new ArrayList<>();
        char[] cArray = input.toCharArray();
        for(int i = 0;i< cArray.length;i++){
            switch (cArray[i]){
                case '+':
                    arrOperation.add(cArray[i] + "");
                    break;
                case '-':
                    arrOperation.add(cArray[i] + "");
                    break;
                case 'x':
                    arrOperation.add(cArray[i] + "");
                    break;
                case '/':
                    arrOperation.add(cArray[i] + "");
                    break;
                default:
                    break;
            }
        }
        return 0;
    }
    //lưu chữ số vào mảng arrNumber
    public void addNumber(String stringInput){
        arrNumber = new ArrayList<>();
        Pattern regex = Pattern.compile("(\\d+(?:\\.\\d+)?)");
        Matcher matcher = regex.matcher(stringInput);
        while(matcher.find()){
            arrNumber.add(Double.valueOf(matcher.group(1)));
        }
    }
}
