package com.example.sistema.diccionarioagriculturabroran;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;


public class MainActivity extends AppCompatActivity {

    int index = 0; //Indica la página actual
    float start = 0; //Indica el principio de la página
    int upperBound = 260; //Indica la página máxima
    int lowerBound = 201; //Indica la página mínima

    String nombre = "broranso";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        index = lowerBound;

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

                //Permite desplazar de página según el toque realizado
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
        });
    }

    /**
     * Actualiza la imagen desplegada dependiendo del indice actual, el cual es global
     * @param imageView la rerefencia al imageView que despliega la imagen
     */
    public void actualizarImagen(ImageView imageView)
    {
        String nombre_imagen = nombre + index;
        int id_imagen = getResources().getIdentifier(nombre_imagen,"drawable",getPackageName());

        imageView.setImageResource(id_imagen);
    }

    /**
     * Avanza a la próxima página
     * @param img la referencia al imageView que despliega la imagen
     */
    public void rightSlide(View img)
    {
        ImageView imageView = (ImageView) img;

        //Impide que se exceda del límite
        if (index < upperBound) {
            index++;
            actualizarImagen(imageView);

        }
    }

    /**
     * Retrocede a la página anterior
     * @param img referencia al imageView
     */
    public void leftSlide(View img)
    {
        ImageView imageView = (ImageView) img;

        //Impide que se exceda del límite
        if (index > lowerBound) {
            index--;
            actualizarImagen(imageView);

        }
    }

    /**
     * Cambia la imagen desplegada de acuerdo a la opción seleccionada en el menú
     * @param newIndex
     */
    public void irOpcion(int newIndex)
    {
        ImageView img = (ImageView) findViewById(R.id.imageView);
        index = newIndex;
        actualizarImagen(img);
    }

    /**
     * Despliega un menú con todas las opciones disponibles de navegación
     * @param v
     */
    public void displayMenuList(View v)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Indice");

        String[] animals = {"Portada", "Dŕí ëre dëgué̈i crun̈ roi í", "Zhé̈bo párcocro cró shco",
                "Pac cró shco", "é̈b", "shtahuó", "c'uofrurún", "Íc", "shúb", "có",
                "ibín̈, bin̈síhua / québin̈", "srórbo", "t'ú, jóco / jócuo", "dobórba", "Créditos"};
        builder.setItems(animals, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case 0:  irOpcion(201);    break;//Portada
                    case 1:  irOpcion(207);    break;//Frutas
                    case 2:  irOpcion(212);    break;//Herramientas
                    case 3:  irOpcion(215);    break;//Arbustos
                    case 4:  irOpcion(227);    break;//eb
                    case 5:  irOpcion(234);    break;//shtahuó
                    case 6:  irOpcion(240);    break;//c'uofrurún
                    case 7:  irOpcion(245);    break;//ic
                    case 8:  irOpcion(247);    break;//shub
                    case 9:  irOpcion(249);    break;//co
                    case 10:  irOpcion(251);    break;//
                    case 11:  irOpcion(254);    break;//srórbo
                    case 12:  irOpcion(256);    break;//
                    case 13:  irOpcion(258);    break;//dororba
                    case 14:  irOpcion(260);    break;//creditos

                }
            }
        });

        // create and show the alert dialog
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}

