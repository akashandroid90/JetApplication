package app.jetpackapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;
import app.jetpackapplication.ui.main.MainFragment;
import app.jetpackapplication.worker.ContactWorker;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, MainFragment.newInstance())
                    .commitNow();
        }
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        OneTimeWorkRequest build=new OneTimeWorkRequest.Builder(ContactWorker.class).build();
        WorkManager.getInstance().enqueue(build);
    }
}
