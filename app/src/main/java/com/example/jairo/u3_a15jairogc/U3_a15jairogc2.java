package com.example.jairo.u3_a15jairogc;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class U3_a15jairogc2 extends Activity {

        EditText text_busqueda;
        EditText text_telefono;
        Button pechar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.secundaria_u3_a15jairogc);

        pechar =(Button) findViewById(R.id.pechar);
        pechar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text_busqueda=(EditText) findViewById(R.id.busqueda);
                text_telefono=(EditText) findViewById(R.id.telefono);
                Intent datos =new Intent(U3_a15jairogc2.this,u3_a15jairogc.class);
                datos.putExtra("Texto", text_busqueda.getText().toString());
                datos.putExtra("Telefono", text_telefono.getText().toString());
                setResult(RESULT_OK,datos);
                finish();
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_u3_a15jairogc2, menu);
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
    public void finish(){
        super.finish();
    }
}
