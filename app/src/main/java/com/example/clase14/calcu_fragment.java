package com.example.clase14;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link calcu_fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class calcu_fragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public calcu_fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment calcu_fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static calcu_fragment newInstance(String param1, String param2) {
        calcu_fragment fragment = new calcu_fragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);



        }
    }

    private TextView txt1,txt2;
    private String num1,num2,op;
    private Boolean operacion = false,decimal = false;
    private int error = 0;
    private Button b0,b1,b2,b3,b4,b5,b6,b7,b8,b9;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_calcu_fragment,container,false);

        txt2 = view.findViewById(R.id.textView1);
        txt1 = view.findViewById(R.id.textView2);
        num1 = "";
        num2 = "";
        txt1.setText("0");
        txt2.setText("hola");
        op = "";
        inicionbtn();

        return inflater.inflate(R.layout.fragment_calcu_fragment, container, false);
    }

    private void inicionbtn() {
        b0= getView().findViewById(R.id.btn0);
        b1= getView().findViewById(R.id.btn1);
        b2= getView().findViewById(R.id.btn2);
        b3= getView().findViewById(R.id.btn3);
        b4= getView().findViewById(R.id.btn4);
        b5= getView().findViewById(R.id.btn5);
        b6= getView().findViewById(R.id.btn6);
        b7= getView().findViewById(R.id.btn7);
        b8= getView().findViewById(R.id.btn8);
        b9= getView().findViewById(R.id.btn9);
    }

    public void Escribir()
    {
        if(error == 2)
        {
            num1 = "";
            num2 = "";
            txt1.setText("0");
            op = "";
            operacion = false;
            decimal = false;
            error = 0;
        }
        txt1.setText(num1);
        if(num2 != "")
        {
            txt2.setText(num2);
            decimal = false;
        }
        else
            txt2.setText("la calculadora mas bonita desde 2021");
    }

    public void punto(View view) {
        if(!decimal)
        {
            num1 += ".";
            decimal = true;
        }
        Escribir();
    }
    public void BorrarTodo(View view) {
        num1 = "";
        num2 = "";
        txt1.setText("0");
        op = "";
        operacion = false;
        decimal = false;
    }
    public void Borrar(View view) {
        if(num1 != "")
        {
            if(num1.substring(num1.length() - 1, num1.length()).equals("."))
                decimal = false;
            num1 = num1.substring(0, num1.length() - 1);
            Escribir();
        }
    }
    public void porcentaje(View view) {
        if(operacion && op.equals("x"))
        {
            num1 = String.valueOf(Double.valueOf(num2.substring(0, num2.length() - 1))*(Double.valueOf(num1)*0.01));
            num2 = "";
            Escribir();
            operacion = false;
        }
    }
    public void opbasicas(Character ope)
    {
        if(num1.substring(num1.length() - 1, num1.length()).equals("."))
        {
            num1 = num1.substring(0, num1.length() - 1);
            decimal = false;
        }
        if(operacion)
        {
            num2 = num2.substring(0, num2.length() - 1);
            num2 += ope;
        }
        else
        {
            num2 = num1 + ope;
            num1 = "";
            operacion = true;
        }
        op = String.valueOf(ope);
        Escribir();
    }
     //************************--**************************  //
    public void division(View view) {
        opbasicas('/');
    }
    public void multiplicar(View view) {
        opbasicas('x');
    }
    public void menos(View view) {
        opbasicas('-');
    }
    public void mas(View view) {
        opbasicas('+');
    }
    public void igual(View view) {
        if(operacion)
        {
            switch (op)
            {
                case "/":
                {
                    if(Double.valueOf(num1) == 0)
                    {
                        num1 = "NO / 0, Andina, No?";
                        error += 1;
                    }
                    else
                    {
                        num1 = String.valueOf(Double.valueOf(num2.substring(0, num2.length() - 1))/Double.valueOf(num1));
                    }
                }
                break;
                case "x":  num1 = String.valueOf(Double.valueOf(num2.substring(0, num2.length() - 1))*Double.valueOf(num1));;
                    break;
                case "-":  num1 = String.valueOf(Double.valueOf(num2.substring(0, num2.length() - 1))-Double.valueOf(num1));
                    break;
                case "+":  num1 = String.valueOf(Double.valueOf(num2.substring(0, num2.length() - 1))+Double.valueOf(num1));;
                    break;
                default:
                {
                    BorrarTodo(view);
                    txt1.setText("SYNTAX ERROR");
                }break;
            }
            num2 = "";
            Escribir();
            error += 1;
            decimal = true;
            operacion = false;
        }
    }

    public void uno(View view) {
        num1+='1';
        Escribir();
    }
    public void dos(View view) {
        num1+='2';
        Escribir();
    }
    public void tres(View view) {
        num1+='3';
        Escribir();
    }
    public void cuatro(View view) {
        num1+='4';
        Escribir();
    }
    public void cinco(View view) {
        num1+='5';
        Escribir();
    }
    public void seis(View view) {
        num1+='6';
        Escribir();
    }
    public void siete(View view) {
        num1+='7';
        Escribir();
    }
    public void ocho(View view) {
        num1+='8';
        Escribir();
    }
    public void nueve(View view) {
        num1+='9';
        Escribir();
    }



}

























