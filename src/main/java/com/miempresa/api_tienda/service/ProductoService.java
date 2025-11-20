package com.miempresa.api_tienda.service;

import com.miempresa.api_tienda.dto.ProductoDTO;

import java.util.List;

public interface ProductoService {
    
    ProductoDTO crear(ProductoDTO productoDTO);
    
    ProductoDTO obtenerPorId(Long id);
    
    List<ProductoDTO> obtenerTodos();
    
    ProductoDTO actualizar(Long id, ProductoDTO productoDTO);
    
    void eliminar(Long id);
    
    List<ProductoDTO> buscarPorNombre(String nombre);
    
    List<ProductoDTO> buscarPorCategoria(String categoria);
}
