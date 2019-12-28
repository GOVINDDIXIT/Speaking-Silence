package govind.sign_language.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import govind.sign_language.R;

public class BaseActivity extends AppCompatActivity {

    Button btn_sign_lang;
    Button btn_sign_recog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        btn_sign_lang = findViewById(R.id.sign_lang);
        btn_sign_recog = findViewById(R.id.btn_sign_recog);
        btn_sign_lang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent myIntent = new Intent(BaseActivity.this, SignPdfActivity.class);
                myIntent.putExtra("ViewType", "assets");
                startActivity(myIntent);
            }
        });

        btn_sign_recog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent myIntent = new Intent(BaseActivity.this, MainActivity.class);
                startActivity(myIntent);
            }
        });
    }
}
