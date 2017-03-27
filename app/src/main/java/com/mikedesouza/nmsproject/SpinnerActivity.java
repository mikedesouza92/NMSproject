package com.mikedesouza.nmsproject;

import android.app.Activity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

/**
 * Created by Mike on 27/03/2017.
 */

public class SpinnerActivity extends Activity implements AdapterView.OnItemSelectedListener {




    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        parent.getItemAtPosition(position);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
