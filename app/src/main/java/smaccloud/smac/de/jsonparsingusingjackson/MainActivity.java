package smaccloud.smac.de.jsonparsingusingjackson;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {
    private UsersController usersController;
    private TextView displayJson;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        usersController = new UsersController();

        displayJson = (TextView) findViewById(R.id.jsonDisplay);

        Button startParsing = (Button) findViewById(R.id.startParsing);
        startParsing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gettingJson();
            }
        });
    }

    final void gettingJson() {
        final Thread checkUpdate = new Thread() {
            public void run() {
                usersController.init();
                final StringBuilder str = new StringBuilder("user : ");
                for (User u : usersController.findAll()) {
                    str.append("\n").append("first name : ").append(u.getFirstname());
                    str.append("\n").append("last name : ").append(u.getLastname());
                    str.append("\n").append("login : ").append(u.getLogin());
                    str.append("\n").append("twitter : ").append(u.getTwitter());
                    str.append("\n").append("Web : ").append(u.getWeb());
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        displayJson.setText(str.toString());
                    }
                });

            }
        };
        checkUpdate.start();
    }

}
