package com.example.calculator;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ScientificFragment extends Fragment {

    private ButtonCallback callback;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof ButtonCallback) {
            callback = (ButtonCallback) context;
        }
    }

    @Nullable @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_scientific, container, false);
        wireButtons(v);
        return v;
    }

    private void wireButtons(View v) {
        int[] digits = {R.id.btn0, R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4,
                        R.id.btn5, R.id.btn6, R.id.btn7, R.id.btn8, R.id.btn9};
        for (int i = 0; i < digits.length; i++) {
            final String d = String.valueOf(i);
            v.findViewById(digits[i]).setOnClickListener(x -> callback.onDigit(d));
        }

        v.findViewById(R.id.btnDot).setOnClickListener(x -> callback.onDot());
        v.findViewById(R.id.btnAdd).setOnClickListener(x -> callback.onOperator("+"));
        v.findViewById(R.id.btnSub).setOnClickListener(x -> callback.onOperator("−"));
        v.findViewById(R.id.btnMul).setOnClickListener(x -> callback.onOperator("×"));
        v.findViewById(R.id.btnDiv).setOnClickListener(x -> callback.onOperator("÷"));
        v.findViewById(R.id.btnEqual).setOnClickListener(x -> callback.onEqual());
        v.findViewById(R.id.btnClear).setOnClickListener(x -> callback.onClear());
        v.findViewById(R.id.btnSign).setOnClickListener(x -> callback.onSign());
        v.findViewById(R.id.btnPercent).setOnClickListener(x -> callback.onPercent());

        // Scientific buttons
        v.findViewById(R.id.btnSin).setOnClickListener(x -> callback.onScientific("sin"));
        v.findViewById(R.id.btnCos).setOnClickListener(x -> callback.onScientific("cos"));
        v.findViewById(R.id.btnTan).setOnClickListener(x -> callback.onScientific("tan"));
        v.findViewById(R.id.btnLog).setOnClickListener(x -> callback.onScientific("log"));
        v.findViewById(R.id.btnLn).setOnClickListener(x -> callback.onScientific("ln"));
        v.findViewById(R.id.btnSqrt).setOnClickListener(x -> callback.onScientific("√"));
        v.findViewById(R.id.btnPow).setOnClickListener(x -> callback.onOperator("xʸ"));
        v.findViewById(R.id.btnFactorial).setOnClickListener(x -> callback.onScientific("n!"));
        v.findViewById(R.id.btnInverse).setOnClickListener(x -> callback.onScientific("1/x"));
        v.findViewById(R.id.btnExp).setOnClickListener(x -> callback.onScientific("eˣ"));
        v.findViewById(R.id.btnPi).setOnClickListener(x -> callback.onConstant(Math.PI));
        v.findViewById(R.id.btnE).setOnClickListener(x -> callback.onConstant(Math.E));
    }
}
