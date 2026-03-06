package fes.jonathan.holamundocrudv1.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import fes.jonathan.holamundocrudv1.R;
import fes.jonathan.holamundocrudv1.models.ProductoModel;
import fes.jonathan.holamundocrudv1.services.ProductoService;
public class EditarActivity extends AppCompatActivity {
    ProductoService productoService = new ProductoService(this);
    private EditText editTextNombreEditar;
    private EditText editTextDescripcionEditar;
    private int productoId;
    ProductoModel productoAEditar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_editar);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        editTextNombreEditar = findViewById(R.id.editTextNombreEditar);
        editTextDescripcionEditar = findViewById(R.id.editTextDescripcionEditar);
        Bundle bundle = getIntent().getExtras();
        productoId= bundle.getInt("ProductoId");
        productoAEditar= productoService.obtenerPorId(productoId);
        editTextNombreEditar.setText(productoAEditar.getNombre());
        editTextDescripcionEditar.setText(productoAEditar.getDescripcion());
    }

    public void actualizarDatosDePizza(View view){
        String nombre = editTextNombreEditar.getText().toString();
        String descripcion = editTextDescripcionEditar.getText().toString();
//      productoAEditar.setNombre(nombre);
//      productoAEditar.setDescripcion(descripcion);
        ProductoModel editado = new ProductoModel(productoId,nombre,descripcion);
        productoService.editar(editado);
        finish();
    }
}