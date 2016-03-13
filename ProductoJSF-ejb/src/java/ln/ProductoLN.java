/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ln;

import dao.ProductoFacade;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import modelo.Producto;

/**
 *
 * @author Richy
 */
@Stateless
@LocalBean
public class ProductoLN {
    @EJB
    private ProductoFacade productoFacade;
    
    public void agregaProd(Producto p){
        productoFacade.create(p);
    } 
    public List<Producto> getProds(){
        return productoFacade.findAll();
    }
    public Producto getProd(int i){
        return productoFacade.find(i);
    }
    public Producto getProdS(String i){
        return productoFacade.find(i);
    }
    public List<Producto> getProdsxPagina(int[] rango){
        return productoFacade.findRange(rango);
    }
    public void eliminarProd(Producto p){
        productoFacade.remove(p);
    }
    public void editarProd(Producto p){
        productoFacade.edit(p);
    }
    public int count(){
        return productoFacade.count();
    }
}
