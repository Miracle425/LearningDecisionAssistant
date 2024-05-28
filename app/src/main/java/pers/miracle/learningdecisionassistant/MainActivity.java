package pers.miracle.learningdecisionassistant;

import android.app.AlertDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        LinearLayout linearLayoutTextInputEditText = findViewById(R.id.linear_layout_text_input_edit_text);
        Button buttonStart = findViewById(R.id.button_start);
        Button buttonAdd = findViewById(R.id.button_add);
        Button buttonDelete = findViewById(R.id.button_delete);
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                TextInputEditText textInputEditText = new TextInputEditText(MainActivity.this);
                textInputEditText.setBackgroundColor(Color.WHITE);
                textInputEditText.setTextColor(Color.BLACK);
                textInputEditText.setHintTextColor(Color.GRAY);

                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                layoutParams.setMargins(20, 5, 0, 5);

                textInputEditText.setHint("请输入选项");
                textInputEditText.setLayoutParams(layoutParams);
                linearLayoutTextInputEditText.addView(textInputEditText);
            }
        });
        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < linearLayoutTextInputEditText.getChildCount(); i++) {
                    if (linearLayoutTextInputEditText.getChildAt(i) instanceof TextInputEditText) {
                        TextInputEditText textInputEditText = (TextInputEditText) linearLayoutTextInputEditText.getChildAt(i);
                        if (textInputEditText.getText().toString().equals("")) {
                            //删除当前EditText
                            linearLayoutTextInputEditText.removeViewAt(i);
                            i--;

                        }
                    }
                }
            }
        });
        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取textInputLayoutMain中的所有EditText
                String[] texts = new String[linearLayoutTextInputEditText.getChildCount()];
                for (int i = 0; i < linearLayoutTextInputEditText.getChildCount(); i++) {
                    TextInputEditText textInputEditText = (TextInputEditText) linearLayoutTextInputEditText.getChildAt(i);
                    //获取EditText的文本
                    texts[i] = textInputEditText.getText().toString();
                }
                //生成随机数
                int randomNum = new Random().nextInt(linearLayoutTextInputEditText.getChildCount());

                //弹窗显示结果
                new AlertDialog
                        .Builder(MainActivity.this)
                        .setTitle("结果")
                        .setMessage(texts[randomNum])
                        .setPositiveButton("确定", null)
                        .show();
            }
        });
    }
}