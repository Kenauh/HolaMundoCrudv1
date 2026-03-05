package fes.jonathan.holamundocrudv1;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.List;

import fes.jonathan.holamundocrudv1.activities.AgregarActivity;
import fes.jonathan.holamundocrudv1.activities.DetallesActivity;
import fes.jonathan.holamundocrudv1.activities.EditarActivity;
import fes.jonathan.holamundocrudv1.models.ProductoModel;
import fes.jonathan.holamundocrudv1.services.ProductoService;

public class MainActivity extends AppCompatActivity {
    Spinner spinner;
    ProductoService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
//        mostrarDialog();
        spinner = findViewById(R.id.spinner);
        service = ProductoService.getInstance();
        List<ProductoModel> productoModels = service.obtenerTodos();
        llenarSpinner(productoModels);
    }

    public void mostrarDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Este es un titulo")
                .setMessage("Este es un mensaje");
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getBaseContext(),"Ok", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getBaseContext(),"Cancelar", Toast.LENGTH_SHORT).show();

            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    };
    public void llenarSpinner(List<ProductoModel> productModel){
        ArrayAdapter<ProductoModel> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                productModel
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    public void confirmarBorrar(View view){
        ProductoModel productoModel = (ProductoModel) spinner.getSelectedItem();
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Eliminar")
                .setMessage("¿Quiéres borrar este elemento? "+productoModel.getNombre());
        builder.setPositiveButton("Borrar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                service.borrar(productoModel.getId());
                Toast.makeText(getBaseContext(),"Producto borrado", Toast.LENGTH_SHORT).show();
                List<ProductoModel> productoModels = service.obtenerTodos();
                llenarSpinner(productoModels);
            }
        });
        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
//                Toast.makeText(getBaseContext(),"Cancelar", Toast.LENGTH_SHORT).show();

            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    public void irAAgregar(View view){
        Intent intent = new Intent(this, AgregarActivity.class);
        startActivity(intent);
    }

    public void irAEditar(View view){
        Intent intent = new Intent(this, EditarActivity.class);
        ProductoModel productoModel = (ProductoModel) spinner.getSelectedItem();
        intent.putExtra("ProductoId", productoModel.getId());
        startActivity(intent);
    }

    public void irADetalles(View view){
        Intent intent = new Intent(this, DetallesActivity.class);
        ProductoModel productoModel = (ProductoModel) spinner.getSelectedItem();
        intent.putExtra("ProductoId", productoModel.getId());
        startActivity(intent);
    }
}