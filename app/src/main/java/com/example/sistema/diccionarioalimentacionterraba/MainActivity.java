package com.example.sistema.diccionarioalimentacionterraba;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;


public class MainActivity extends AppCompatActivity {

    int index = 0;
    float start = 0;
    int upperBound = 260;
    int lowerBound = 201;

    String nombre = "broranso";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        index = 201;

        try
        {
            this.getSupportActionBar().hide();
        }
        catch (NullPointerException e)
        {

        }

        //Asigna la detección deslizamiento izquierdo y derecho
        ImageView img = (ImageView) findViewById(R.id.imageView);

        img.setOnTouchListener( new View.OnTouchListener(){
            @Override
            public boolean onTouch(View v, MotionEvent event) {


                if(event.getAction() == MotionEvent.ACTION_DOWN) //Cuando empieza el toque
                {
                    start = event.getX();
                }
                else
                    if(event.getAction() == MotionEvent.ACTION_UP) //Cuando termina el toque
                    {
                        float end = event.getX();

                        if(end - start < 0)
                        {
                            rightSlide(v);
                        }
                        else
                        {
                            leftSlide(v);
                        }
                    }

                return true;
            }
        }

        );



    }

    public void actualizarImagen(ImageView imageView)
    {
        String nombre_imagen = nombre + index;
        int id_imagen = getResources().getIdentifier(nombre_imagen,"drawable",getPackageName());

        imageView.setImageResource(id_imagen);
    }

    public void rightSlide(View img)
    {
        ImageView imageView = (ImageView) img;

        if (index < upperBound) {
            index++;
            actualizarImagen(imageView);

        }
    }

    public void leftSlide(View img)
    {
        ImageView imageView = (ImageView) img;

        if (index > lowerBound) {
            index--;
            actualizarImagen(imageView);

        }
    }

    public void irOpcion(int newIndex)
    {
        ImageView img = (ImageView) findViewById(R.id.imageView);
        index = newIndex;
        actualizarImagen(img);
    }

    public void displayMenuList(View v)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Indice");


        String[] animals = {"Portada", "Frutas", "Legumbres", "Herramientas", "Arbustos"};
        builder.setItems(animals, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case 0:  irOpcion(201);    break;//Portada
                    case 1:  irOpcion(207);    break;//Frutas
                    case 2:  irOpcion(211);    break;//Legumbres
                    case 3:  irOpcion(212);    break;//Herramientas
                    case 4:  irOpcion(215);    break;//Arbustos
                }
            }
        });

// create and show the alert dialog
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
