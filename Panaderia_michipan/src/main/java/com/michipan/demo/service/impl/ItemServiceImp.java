package com.michipan.demo.service.impl;

import com.michipan.demo.dao.FacturaDao;
import com.michipan.demo.dao.ProductoDao;
import com.michipan.demo.dao.UsuarioDao;
import com.michipan.demo.dao.VentaDao;
import com.michipan.demo.domain.Factura;
import com.michipan.demo.domain.Item;
import com.michipan.demo.domain.Producto;
import com.michipan.demo.domain.Usuario;
import com.michipan.demo.domain.Venta;
import com.michipan.demo.service.ItemService;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class ItemServiceImp implements ItemService {

    @Override
    public List<Item> gets() {
        return listaItems;
    }

    //busca en el arraylist y si lo encuentra lo pasa... si no lo encuentra retorna null
    @Override
    public Item get(Item item) {
        for (Item i : listaItems) {
            if (i.getIdProducto() == item.getIdProducto()) {
                return i;

            }
        }
        return null;
    }

    @Override
    public void delete(Item item) {
        var posicion = -1;
        var existe = false;
        for (Item i : listaItems) {
            posicion++;
            if (i.getIdProducto() == item.getIdProducto()) {
                existe = true;
                break;

            }
        }
        if (existe) {
            listaItems.remove(posicion);
        }
    }

    @Override
    public void save(Item item) {

        var existe = false;
        //Primero busca si el item existe en el carrito
        for (Item i : listaItems) {
            if (i.getIdProducto() == item.getIdProducto()) {

                existe = true;

                if (i.getCantidad() < i.getExistencias()) {
                    i.setCantidad(i.getCantidad() + 1);
                }
                break;

            }
        }
        if (!existe) {
            item.setCantidad(1);
            listaItems.add(item);
        }
    }

    @Override
    public void update(Item item) {
        for (Item i : listaItems) {
            if (i.getIdProducto() == item.getIdProducto()) {
                i.setCantidad(item.getCantidad());
                break;
            }
        }
    }

    @Autowired
    private UsuarioDao usuarioDao;
    @Autowired
    private ProductoDao productoDao;
    @Autowired
    private FacturaDao facturaDao;
    @Autowired
    private VentaDao ventaDao;

    @Override
    public void facturar() {
//Se debe recuperar el usuario autenticado

        String username;

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails userDetails) {
            username = userDetails.getUsername();

        } else {
            username = principal.toString();
        }

        if (username.isBlank()) {
            System.out.println("Username en blanco");
            return;
        }

        Usuario usuario = usuarioDao.findByUsername(username);
        if (usuario == null) {
            System.out.println("Usuario no existe");
            return;
        }

//se debe registrar la factura incluyendo usuario

Factura factura =new Factura(usuario.getIdUsuario());
factura=facturaDao.save(factura);

//se debe registrar las ventas de cada producto y actualizando existencia
double total=0;
for(Item i: listaItems){
    Venta venta= new Venta(factura.getIdFactura(), i.getIdProducto(), i.getPrecio(), i.getCantidad());
    ventaDao.save(venta);
    
    Producto producto=productoDao.getReferenceById(i.getIdProducto());
    producto.setExistencias(producto.getExistencias()-i.getCantidad());
    productoDao.save(producto);
    total+=i.getCantidad()*i.getPrecio();
    
}

//se debe registrar el total de la venta en la factura
factura.setTotal(total);
facturaDao.save(factura);

//se debe limpiar el carrito la lista

listaItems.clear();
    }

}
