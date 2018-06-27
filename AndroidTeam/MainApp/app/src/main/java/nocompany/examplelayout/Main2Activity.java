package nocompany.examplelayout;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

<<<<<<< HEAD
public class Main2Activity extends AppCompatActivity {
=======
public class Main2Activity extends AppCompatActivity implements View.OnClickListener {
    CardView cd2;
>>>>>>> Puneeth

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
<<<<<<< HEAD

    }

=======
        cd2 = (CardView)findViewById(R.id.schedule);
        cd2.setOnClickListener(this);

    }

    @Override
    public void onClick(View view){
        Intent intent = new Intent(this,Schedulemain.class);
        startActivity(intent);
    }

>>>>>>> Puneeth


    public void LaunchMap(View view) {
        Uri gmmIntentUri = Uri.parse("google.navigation:q=PESIT+Research+center, Hosur+Karnataka&mode=w");
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        startActivity(mapIntent);

    }
}