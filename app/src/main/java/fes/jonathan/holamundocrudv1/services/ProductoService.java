package fes.jonathan.holamundocrudv1.services;

import java.util.ArrayList;
import java.util.List;

import fes.jonathan.holamundocrudv1.models.ProductoModel;

public class ProductoService {
    public static ProductoService productoService;

    private List<ProductoModel> productos;

    private ProductoService() {
        productos = new ArrayList<>();
        productos.add(new ProductoModel(1, "Hawaiana", "Trae Piña y Jamón"));
        productos.add(new ProductoModel(2, "Mexicana", "Pues es de aqui , es mexicana "));
    }


    public static ProductoService getInstance(){
        if(productoService == null){
            productoService = new ProductoService();
        }
        return productoService;
    }

    public List<ProductoModel> obtenerTodos(){
        return productos;
    }

    public ProductoModel obtenerPorId(int productoId){
        ProductoModel producto = null;

        for (ProductoModel productoModel: productos) {
            if(productoModel.getId() == productoId){
                producto = productoModel;
                break;
            }
        }

        return  producto;
    }

    public void borrar(int productoId){
        int position = 0;
        for (int i=0; i < productos.size(); i++){
            if(productoId == productos.get(i).getId()){
                position = i;
            }
        }

        productos.remove(productos.get(position));
    }


    public  int agregar(ProductoModel productoModel){
        int id = this.productos.size() + 1;
        productoModel.setId(id);
        productos.add(productoModel);

        return  id;
    }

    public void editar(ProductoModel productoModificado){
        for (ProductoModel producto :
                productos) {
            if (producto.getId() == productoModificado.getId()) {
//                producto = productoModificado;
                producto.setNombre(productoModificado.getNombre());
                producto.setDescripcion(productoModificado.getDescripcion());
                break;
            }
        }
    }
}
