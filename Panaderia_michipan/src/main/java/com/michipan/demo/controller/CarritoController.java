package com.michipan.demo.controller;

import com.michipan.demo.domain.Item;
import com.michipan.demo.domain.Producto;
import com.michipan.demo.service.ItemService;
import com.michipan.demo.service.ProductoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CarritoController {

   @Autowired
   private ItemService itemservice;
    @Autowired
    private ProductoService productoservice;
    
    
    @GetMapping("/carrito/listado")
    public String listado(Model model) {

        var lista = itemservice.gets();
        var carritoTotal=0;
        for (Item i : lista){
        carritoTotal+=(i.getCantidad()*i.getPrecio());
                }
        model.addAttribute("items", lista);
        model.addAttribute("CarritoTotal", carritoTotal);

        return "/carrito/listado";
    }
    
    @GetMapping("/carrito/agregar/{idProducto}")
    public ModelAndView agregar(Model model, Item item) {
        Item item2=itemservice.get(item);
        
        if(item2==null){//no existe el producto
            Producto p=productoservice.getProducto(item);
            item2=new Item(p);
        }
        itemservice.save(item2);
        var lista=itemservice.gets();
        var TotalCarrito=0;
        var TotalCompra=0;
        
        for(Item i : lista){
            TotalCarrito+=i.getCantidad();
            TotalCompra+=(i.getCantidad()*i.getPrecio());
            
        }
        model.addAttribute("listaItems",lista);
        model.addAttribute("listaTotal",TotalCarrito);
        model.addAttribute("carritoTotal",TotalCompra);
        return new ModelAndView("/carrito/fragmentos :: verCarrito");
    }

   @GetMapping("/carrito/eliminar/{idProducto}")
   public String eliminar(Item item){
       itemservice.delete(item);
       return "redirect:/carrito/listado";
   }
   
   
   @GetMapping("/carrito/modificar/{idProducto}")
   public String modificar(Item item, Model model){
       item=itemservice.get(item);
       model.addAttribute("item",item);
       
       return "/carrito/modifica";
   }
   
      @PostMapping("/carrito/guardar/")
   public String guardar(Item item, Model model){
       itemservice.update(item);
       
       return "redirect:/carrito/listado";
   }
   
   @GetMapping("/facturar/carrito")
   public String facturar(){
       itemservice.facturar();
       
       return "redirect:/Ventas2";
   }
   
   
}
