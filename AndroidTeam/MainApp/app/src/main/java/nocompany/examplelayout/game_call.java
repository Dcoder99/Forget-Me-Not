package nocompany.examplelayout;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class game_call extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_call);

        Button gamebtn = (Button) findViewById(R.id.playbtn);
        gamebtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent =new Intent(this,GAME.class);
    startActivity(intent);
    }
}
