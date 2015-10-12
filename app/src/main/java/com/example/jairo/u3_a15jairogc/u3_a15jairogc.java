package com.example.jairo.u3_a15jairogc;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.SearchManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class u3_a15jairogc extends Activity {


    AlertDialog.Builder dialogo;

    private static final int COD_PETICION=1;
    private TextView busqueda_oculta;
    private TextView telefono_oculto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.principal_u3_a15jairogc);
        busqueda_oculta =(TextView) findViewById(R.id.text_buscar);
        telefono_oculto =(TextView) findViewById(R.id.text_telefono);
        Button botonactividad = (Button) findViewById(R.id.lanzaactivity);
        botonactividad.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                showDialog(1);
                return true;
            }
        });
            }

    protected Dialog onCreateDialog(int id) {

                dialogo = new AlertDialog.Builder(this);
                dialogo.setIcon(android.R.drawable.ic_dialog_alert);
                dialogo.setTitle(getResources().getString(R.string.dialog_title));

                dialogo.setPositiveButton(getResources().getString(R.string.dialog_buscar), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String busqueda = getResources().getString(R.string.text_casa);
                        if (busqueda_oculta != null && busqueda_oculta.getText() != "") {
                            busqueda = busqueda_oculta.getText().toString();
                        }
                        Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
                        intent.putExtra(SearchManager.QUERY, busqueda);
                        startActivity(intent);

                    }

                });

                dialogo.setNegativeButton(getResources().getString(R.string.dialog_telefono), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        if (telefono_oculto != null && telefono_oculto.getText() != "") {
                            Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + telefono_oculto.getText().toString()));
                            startActivity(intent);

                        } else {
                            Toast.makeText(u3_a15jairogc.this, getResources().getString(R.string.aviso_telf), Toast.LENGTH_SHORT).show();
                        }

                    }
                });


        return dialogo.create();
                }
    public void muestradatos(View view){

        Toast.makeText(u3_a15jairogc.this,getResources().getString(R.string.dialog_buscar)+":\n"+ busqueda_oculta.getText().toString() +"\n"+getResources().getString(R.string.dialog_telefono)+":\n"+ telefono_oculto.getText().toString(), Toast.LENGTH_SHORT).show();

    }


    public void lanzactivity(View view){

        Intent intent =new Intent(this,U3_a15jairogc2.class );
        startActivityForResult(intent, COD_PETICION);
    }
    @Override
    protected void onActivityResult(int Codigo_requerido,int Codigo_resultado,Intent datos){
        if(Codigo_requerido==COD_PETICION){
            if(Codigo_resultado==RESULT_OK) {
                if (datos.hasExtra("Telefono")||datos.hasExtra("Texto")) {
                    busqueda_oculta.setText(datos.getExtras().getString("Texto"));
                    telefono_oculto.setText(datos.getExtras().getString("Telefono"));
                } else {
                    Toast.makeText(u3_a15jairogc.this, "Vacio", Toast.LENGTH_SHORT).show();

                }
            }
        }
    }
    @Override
    protected void onSaveInstanceState(Bundle estado){
        super.onSaveInstanceState(estado);
        estado.putString("Texto", busqueda_oculta.getText().toString());
        estado.putString("Telefono", telefono_oculto.getText().toString());

    }


    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState){
        super.onRestoreInstanceState(savedInstanceState);
        busqueda_oculta.setText(savedInstanceState.getString("Texto"));
        telefono_oculto.setText(savedInstanceState.getString("Telefono"));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_u3_a15jairogc, menu);
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
