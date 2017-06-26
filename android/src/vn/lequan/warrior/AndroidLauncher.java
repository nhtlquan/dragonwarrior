package vn.lequan.warrior;

import android.app.FragmentManager;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;

import vn.lequan.warrior.util.App;
import vn.lequan.warrior.views.play.PlayPresenter;


public class AndroidLauncher extends AndroidApplication {
    public static Context context;
    public FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
        context = getApplicationContext();
        fragmentManager = getFragmentManager();
        initialize(new Main(context, fragmentManager), config);
    }

    @Override
    public void onBackPressed() {
//        Toast.makeText(context, "Fuck phệt", Toast.LENGTH_SHORT).show();
        return;
    }
}
