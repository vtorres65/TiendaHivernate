package org.example.alura.tienda.prueba;

import org.example.alura.tienda.dao.CategoriaDao;
import org.example.alura.tienda.dao.ProductoDao;
import org.example.alura.tienda.modelo.Categoria;
import org.example.alura.tienda.modelo.Producto;
import org.example.alura.tienda.utils.JPAUtils;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

public class RegistroDeProducto {

    public static void main(String[] args){
        registrarProducto();
        EntityManager em = JPAUtils.getEntityManager();

        ProductoDao productoDao = new ProductoDao(em);
        Producto producto = productoDao.consultaPorId(1l);
        System.out.println(producto.getNombre());

        List<Producto> productos = productoDao.consultaPorNombreDeCategoria("CELULARES");
        productos.forEach(producto1 -> System.out.println(producto1.getDescripcion()));
    }

    private static void registrarProducto() {
        Categoria celulares = new Categoria("CELULARES");
        Producto celular = new Producto("Samsung", "telefono usado", new BigDecimal("1000"), celulares);

        EntityManager em = JPAUtils.getEntityManager();

        ProductoDao productoDao = new ProductoDao(em);
        CategoriaDao categoriaDao = new CategoriaDao(em);

        em.getTransaction().begin();

        categoriaDao.guardar(celulares);
        productoDao .guardar(celular);

        em.getTransaction().commit();
        em.close();
    }
}
