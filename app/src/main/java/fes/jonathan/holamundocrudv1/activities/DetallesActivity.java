package fes.jonathan.holamundocrudv1.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import fes.jonathan.holamundocrudv1.R;
import fes.jonathan.holamundocrudv1.models.ProductoModel;
import fes.jonathan.holamundocrudv1.services.ProductoService;

public class DetallesActivity extends AppCompatActivity {

    private ProductoModel productoDetalle;
    ProductoService productoService = new ProductoService(this);
    private int productoId;
    private TextView nombre;
    private TextView descripcion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_detalles);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Bundle bundle = getIntent().getExtras();
        productoId= bundle.getInt("ProductoId");
        productoDetalle = productoService.obtenerPorId(productoId);
        nombre = findViewById(R.id.textViewNombre);
        descripcion= findViewById(R.id.textViewDescripcion);
        nombre.setText(productoDetalle.getNombre());
        descripcion.setText(productoDetalle.getDescripcion());
    }

    public void regresar(View view){
        finish();
    }
}