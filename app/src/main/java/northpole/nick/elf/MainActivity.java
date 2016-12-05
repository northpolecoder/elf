package northpole.nick.elf;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.OvershootInterpolator;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText name;
    private ViewGroup resultContainer;
    private TextView resultText;
    private ViewGroup bottomContainer;
    private ProgressBar spinner;
    private TextView progressText;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name = (EditText) findViewById(R.id.name);
        resultContainer = (ViewGroup) findViewById(R.id.result_container);
        resultText = (TextView) findViewById(R.id.result);
        bottomContainer = (ViewGroup) findViewById(R.id.bottom_container);
        spinner = (ProgressBar) findViewById(R.id.spinner);
        progressText = (TextView) findViewById(R.id.progress_text);
        button = (Button) findViewById(R.id.button);

        name.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (EditorInfo.IME_ACTION_DONE == actionId) {
                    onCheckClick(null);
                }
                return false;
            }
        });
    }

    public void onCheckClick(View view) {
        check(name.getText().toString());
    }

    private void check(String name) {
        spinner.setVisibility(View.VISIBLE);
        button.setEnabled(false);
        resultContainer.setVisibility(View.INVISIBLE);
        new CheckNameTask(this) {
            @Override
            protected void onProgressUpdate(String... values) {
                progressText.setText(values[0]);
            }

            @Override
            protected void onPostExecute(final Boolean result) {
                spinner.setVisibility(View.INVISIBLE);
                progressText.setText("");
                resultText.setText(result ? R.string.nice_list : R.string.naughty_list);
                button.setEnabled(true);
                bottomContainer.animate().translationY(resultContainer.getHeight()).withEndAction(new Runnable() {
                    @Override
                    public void run() {
                        resultContainer.setScaleX(0.8f);
                        resultContainer.setScaleY(0.8f);
                        resultContainer.setAlpha(0);
                        resultContainer.setVisibility(View.VISIBLE);
                        resultContainer.animate().alpha(1).scaleX(1).scaleY(1).setInterpolator(new OvershootInterpolator()).start();
                    }
                }).start();
            }
        }.execute(name);
    }
}
