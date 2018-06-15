package nocompany.examplelayout;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
CardView cd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cd=(CardView)findViewById(R.id.card3);
        cd.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent=new Intent(this,Main2Activity.class);
        startActivity(intent);

    }



}
