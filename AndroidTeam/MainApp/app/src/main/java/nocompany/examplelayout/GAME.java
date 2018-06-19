package nocompany.examplelayout;
import com.nocompany.examplelayout.UnityPlayerActivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class GAME extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        Intent intent = new Intent(this, UnityPlayerActivity.class);
        startActivity(intent);
    }
}
