/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package admon;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import ln.ProductoLN;
import modelo.Producto;

/**
 *
 * @author Richy
 */
@ManagedBean
@SessionScoped
public class AdmonProducto {
    @EJB
    private ProductoLN productoLN;
    private Producto producto =new Producto();
    private int id;
    private List<Producto> prod;
    private List<String> desProds;
    private String desProd;
    private String pagina;
    private static final int registrosxpagina=6;
    private double registrosxpagina1;
    private int inicio;
    private int inicio1;
    private int totalregistros;
    private double totalregistros1;
    private int totalpaginas;
    private int totalpaginasceil;
    private final int rango[] =new int[2];
    private int ultima;
    
    public Producto getPro() {
        return producto;
    }
    public void setPro(Producto pro) {
        this.producto = pro;
    }
    public void agregaProd(ActionEvent e) {
        productoLN.agregaProd(producto);
    }
    public List<Producto> getProds() {
        prod=productoLN.getProds();
        return prod;
    }
    public void editarid(ActionEvent e,Object i) {
        this.id=(int) i;
        producto=productoLN.getProd(id);
    }
    public void editarNombre(ActionEvent e,Object i) {
        producto=new Producto();
        this.id=(int) i;
        producto=productoLN.getProd(id);
    }
    public void eliminarid(ActionEvent e,Producto i) {
        productoLN.eliminarProd(i);
    } 
    public String getDesProd() {
        return desProd;
    }
    public void setDesProd(String desProd) {
        this.desProd = desProd;
    }     
    public List<String> getDesProds(){
        prod=productoLN.getProds();
        desProds = new ArrayList<String>();
        for(Producto p:prod){
            desProds.add(p.getDescripcion());
        }
        return desProds;
    }
    public String pagina() {
        return pagina;
    }
    public void presentaProd(ActionEvent e) {
        producto = prod.get(desProds.indexOf(desProd));
    }
    void mostrar(ActionEvent e, Object i){
       producto=new Producto();
        this.desProd=(String) i;
        producto=productoLN.getProdS(desProd);
        
    }
    public void editaProd(ActionEvent e){
        productoLN.editarProd(producto);
    }
    public void eliminaProd(ActionEvent e){
        productoLN.eliminarProd(producto);
    }
    public int getCuenta(){
        totalregistros=productoLN.count();
        return totalregistros;
    }
    public void iniciar(ActionEvent e){
        registrosxpagina1=(double)registrosxpagina;
        inicio=1;
        inicio1=(inicio-1)*registrosxpagina;
        totalregistros=productoLN.count();
        totalregistros1= (double)totalregistros;
        totalpaginas =(totalregistros/registrosxpagina);
        totalpaginasceil =(int)Math.ceil(totalregistros1/registrosxpagina1);
        rango[0]=inicio1;
        rango[1]=registrosxpagina;
        ultima=0;
        for(int n=1; n<=totalpaginasceil ; n++){
            ultima++;
        }
        prod=productoLN.getProdsxPagina(rango);
    }
    public void prodsXPagina(ActionEvent e) {
        rango[0]=inicio1;
        rango[1]=registrosxpagina;
        prod=productoLN.getProdsxPagina(rango);
    }
    public void prodsPaginaInicio(ActionEvent e) {
        inicio=1;
        inicio1=(inicio-1)*registrosxpagina;
        rango[0]=inicio1;
        rango[1]=registrosxpagina;
        prod=productoLN.getProdsxPagina(rango);
    }
    public void prodsPaginaFin(ActionEvent e) {
        inicio=ultima;
        inicio1=(inicio-1)*registrosxpagina;
        rango[0]=inicio1;
        rango[1]=registrosxpagina;
        prod=productoLN.getProdsxPagina(rango);
    }
    public void prodsPaginaSiguiente(ActionEvent e) {
        inicio=((inicio-registrosxpagina)+(registrosxpagina+1));
        inicio1=(inicio-1)*registrosxpagina;
        rango[0]=inicio1;
        rango[1]=registrosxpagina;
        prod=productoLN.getProdsxPagina(rango);
    }
    public void prodsPaginaAnterior(ActionEvent e) {
        inicio=((inicio-registrosxpagina)+(registrosxpagina-1));
        inicio1=(inicio-1)*registrosxpagina;
        rango[0]=inicio1;
        rango[1]=registrosxpagina;
        prod=productoLN.getProdsxPagina(rango);
    }
    public List<Producto> getTabla(){
        return prod;
    }
    public void nullProducto(ActionEvent e){
        producto=null;
    }
    public AdmonProducto() {
    }
}
