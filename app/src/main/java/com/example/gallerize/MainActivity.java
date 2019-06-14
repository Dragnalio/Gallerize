package com.example.gallerize;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.io.File;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;

    private Adaptor mAdaptor;

    private RecyclerView.LayoutManager mLayoutManager;

    private static final int LOAD_IMAGE_PERMISSION_CODE = 1;

    Context context;

    boolean isBasic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, LOAD_IMAGE_PERMISSION_CODE);

        context = MainActivity.this;

        mLayoutManager = new GridLayoutManager(this,2 );

        mRecyclerView = findViewById(R.id.recycler);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(mLayoutManager);

        isBasic = true;
    }
    @Override
    protected void onStart(){
        super.onStart();

        showFile();
    }

    private ArrayList<Pictures> getExData(String path){
        ArrayList<Pictures> pictureArray = new ArrayList<>();

        File folderimage = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES + path) ;
        if(folderimage.exists()){
            File files[] = folderimage.listFiles();

            for(File eachFile : files){
                Pictures picture = new Pictures();

                picture.setUri(Uri.fromFile(eachFile));
                pictureArray.add(picture);
            }
        }else{
//            Log.d("key", "text");
        }
        return pictureArray;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);

        if(isBasic) menu.findItem(R.id.mItem).setTitle("Basic");
        else menu.findItem(R.id.mItem).setTitle("Advance");

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.mItem:
                isBasic = !isBasic;
                invalidateOptionsMenu();
                showFile();
                return true;

            default:
                return super.onOptionsItemSelected(item);

        }
    }

    //menampilkan path
    private void showFile(){
        String path;
        if(isBasic){
            path = "/Basic";
        }else path = "/Advance";
        mAdaptor = new Adaptor(this, getExData(path));
        mRecyclerView.setAdapter(mAdaptor);
    }



    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case LOAD_IMAGE_PERMISSION_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                } else {
                    finish();
                }
                break;
        }
    }
}
