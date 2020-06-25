package tbc.dma.implicitintents;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.provider.MediaStore;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;
import java.net.URI;

public class MainActivity extends AppCompatActivity {
    private Intent intent;
    private ImageView cameraImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        EditText website = findViewById(R.id.editTextViewURL);
        final Editable urlText =  website.getText();
        Button btnUrl = findViewById(R.id.buttonURL);
        btnUrl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                generateToast("btnGeo clicked "+ urlText);
                intent = new Intent(Intent.ACTION_VIEW, Uri.parse(urlText.toString()));
                startActivity(intent);
            }
        });

        EditText geo = findViewById(R.id.editTextViewGeo);
        final Editable geoText =  geo.getText();
        Button btnGeo = findViewById(R.id.buttonGeo);
        btnGeo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                generateToast("btnGeo clicked "+ geoText);
                intent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:" + urlText.toString()));
                startActivity(intent);
            }
        });

        final EditText share = findViewById(R.id.editTextViewShare);
        final String shareText =  share.getText().toString();
        Button btnShare = findViewById(R.id.buttonShare);
        btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                generateToast("btnShare clicked "+ shareText);
                intent = new Intent(Intent.ACTION_SEND);

                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT, "Share Content");
                intent.putExtra(Intent.EXTRA_SUBJECT, shareText);
                if (intent.resolveActivity(getPackageManager()) == null) {
                    startActivity(Intent.createChooser(intent, "No app found to share this content."));
                }else{
                    startActivity(intent);
                }
                Log.d("TAG", "onClick: "+intent.resolveActivity(getPackageManager()));



            }
        });

        Button camera = findViewById(R.id.buttonCamera);
        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 generateToast("Camera button is clicked");
                 try {
                    intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE_SECURE);
                    if (intent.resolveActivity(getPackageManager()) != null) {
                        startActivityForResult(intent, 1);
                    }else{
                        Log.d("ACTION_IMAGE_CAPTURE", "resolveActivity(getPackageManager()) = null");
                    }
                }catch (SecurityException ex)
                {
                    Log.d("ACTION_IMAGE_CAPTURE","Exception: " + ex);
                }
            }
        });

    }
    void generateToast(String msg)
    {
        Toast.makeText(MainActivity.this, msg , Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Log.d("MainActivity", "onConfigurationChanged: landscape");
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
            Log.d("MainActivity", "onConfigurationChanged: portrait");
        }
        setContentView(R.layout.activity_main);
        // Checks the orientation of the screen

    }
    // This method will help to retrieve the image
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Match the request 'pic id with requestCode
        super.onActivityResult(requestCode, resultCode, data);
        try {
            if (requestCode == 1 && resultCode == Activity.RESULT_OK) {
                Log.d("TAG", "onActivityResult: "+data.getExtras().keySet());
                // BitMap is data structure of image file
                // which stor the image in memory
                //Bitmap photo = (Bitmap) data.getExtras().get("data");
                // Set the image in imageview for display
                //cameraImage.setImageBitmap(photo);
            }
        }catch (SecurityException ex)
        {
            Log.d("ACTION_IMAGE_CAPTURE","Exception: " + ex);
        }

    }












    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
