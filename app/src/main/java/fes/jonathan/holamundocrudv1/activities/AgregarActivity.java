package fes.jonathan.holamundocrudv1.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import fes.jonathan.holamundocrudv1.R;
import fes.jonathan.holamundocrudv1.models.ProductoModel;
import fes.jonathan.holamundocrudv1.services.ProductoService;
public class AgregarActivity extends AppCompatActivity {

    EditText editTextNombre;
    EditText editTextDescripcion;

    ProductoService productoService = new ProductoService(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_agregar);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        editTextNombre = findViewById(R.id.editTextNombre);
        editTextDescripcion = findViewById(R.id.editTextDescripcion);
    }

    public void agregarPizza(View view){
        String nombre = editTextNombre.getText().toString();
        String descripcion= editTextDescripcion.getText().toString();
        ProductoModel productoModel = new ProductoModel(0,nombre, descripcion);
        productoService.agregar(productoModel);
        Toast.makeText(getBaseContext(),"Producto Agregado "+nombre, Toast.LENGTH_SHORT).show();
        finish();
    }

}