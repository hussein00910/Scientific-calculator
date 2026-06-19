package com.example.calculator;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class BasicFragment extends Fragment {

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
        View v = inflater.inflate(R.layout.fragment_basic, container, false);
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

        // Long-press AC = backspace
        v.findViewById(R.id.btnClear).setOnLongClickListener(x -> {
            // handled in MainActivity via backspace
            return false;
        });
    }
}
