package org.example.alura.tienda.dao;

import org.example.alura.tienda.modelo.Producto;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

public class ProductoDao {

    private EntityManager em;

    public ProductoDao(EntityManager em) {
        this.em = em;
    }

    public void guardar(Producto producto){
        this.em.persist(producto);
    }
    public  Producto consultaPorId(Long id){
        return em.find(Producto.class, id);
    }

    public List<Producto> consultarTodos(){
        String jpql = "SEÑECT P FROM Producto AS P";
        return em.createQuery(jpql, Producto.class).getResultList();
    }

    public List<Producto> consultaPorNombre(String nombre){
        String jpql = "SELECT P FROM Producto AS P WHERE P.nombre=:nombre";
        return em.createQuery(jpql).setParameter("nombre", nombre).getResultList();
    }

    public List<Producto> consultaPorNombreDeCategoria(String nombre){
        String jpql = "SELECT P FROM Producto AS P WHERE P.categoria.nombre=:nombre";
        return em.createQuery(jpql).setParameter("nombre", nombre).getResultList();
    }

    public BigDecimal consultarPrecioPorNombreDeProducto(String nombre) {
        String jpql="SELECT P.precio FROM Producto AS P WHERE P.nombre=:nombre";
        return em.createQuery(jpql,BigDecimal.class).setParameter("nombre", nombre).getSingleResult();
    }

    public void actualizar(Producto producto){
        this.em.merge(producto);
    }

    public void remover(Producto producto){
        producto = this.em.merge(producto);
        this.em.remove(producto);
    }
}
