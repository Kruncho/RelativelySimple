package com.example.vincent.relativitytime;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final int SPEED_MS = 0;
    private static final int SPEED_KMH = 1;

    EditText mSpeedEditView;
    TextView mSpeedTextView;

    private int mSpeedSystem = SPEED_MS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSpeedEditView = (EditText) findViewById(R.id.et_speed_input);
        mSpeedTextView = (TextView) findViewById(R.id.tv_speed_output);

        mSpeedEditView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double properTime;
                switch (mSpeedSystem) {
                    case SPEED_KMH :
                        properTime = 1 / gamma(toMs(Double.valueOf(mSpeedEditView.getText().toString())));
                        mSpeedTextView.setText(Double.toString(toKmH(properTime)));
                        break;
                    case SPEED_MS:
                        properTime = 1 / gamma(Double.valueOf(mSpeedEditView.getText().toString()));
                        mSpeedTextView.setText(Double.toString(properTime));
                }
            }
        });
    }

    public double toKmH(double ms) {
        return ms * 3.6;
    }

    public double toMs(double kmH) {
        return kmH / 3.6;
    }

    public double gamma(double speed) {
        return 1 / (Math.sqrt(1 - Math.pow(speed, 2) / Math.pow(SPEED_OF_LIGHT, 2)));
    }

    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();

        switch(view.getId()) {
            case R.id.rb_speed_kmh:
                if (checked)
                    mSpeedSystem = SPEED_KMH;
                    break;
            case R.id.rb_speed_ms:
                if (checked)
                    mSpeedSystem = SPEED_MS;
                    break;
        }
    }
}
