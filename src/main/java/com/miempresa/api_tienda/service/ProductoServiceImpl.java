package com.miempresa.api_tienda.service;

import com.miempresa.api_tienda.dto.ProductoDTO;
import com.miempresa.api_tienda.entity.Producto;
import com.miempresa.api_tienda.exception.ResourceNotFoundException;
import com.miempresa.api_tienda.repository.ProductoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductoServiceImpl implements ProductoService {

    private final ProductoRepository productoRepository;

    @Override
    @Transactional
    public ProductoDTO crear(ProductoDTO productoDTO) {
        log.debug("Creando nuevo producto: {}", productoDTO.getNombre());
        Producto producto = convertirDTOAEntidad(productoDTO);
        Producto productoGuardado = productoRepository.save(producto);
        log.info("Producto creado con ID: {}", productoGuardado.getId());
        return convertirEntidadADTO(productoGuardado);
    }

    @Override
    @Transactional(readOnly = true)
    public ProductoDTO obtenerPorId(Long id) {
        log.debug("Buscando producto con ID: {}", id);
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Producto", "id", id));
        return convertirEntidadADTO(producto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductoDTO> obtenerTodos() {
        log.debug("Obteniendo todos los productos");
        List<Producto> productos = productoRepository.findAll();
        return productos.stream()
                .map(this::convertirEntidadADTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public ProductoDTO actualizar(Long id, ProductoDTO productoDTO) {
        log.debug("Actualizando producto con ID: {}", id);
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Producto", "id", id));
        
        producto.setNombre(productoDTO.getNombre());
        producto.setDescripcion(productoDTO.getDescripcion());
        producto.setPrecio(productoDTO.getPrecio());
        producto.setStock(productoDTO.getStock());
        producto.setCategoria(productoDTO.getCategoria());
        
        Producto productoActualizado = productoRepository.save(producto);
        log.info("Producto actualizado con ID: {}", productoActualizado.getId());
        return convertirEntidadADTO(productoActualizado);
    }

    @Override
    @Transactional
    public void eliminar(Long id) {
        log.debug("Eliminando producto con ID: {}", id);
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Producto", "id", id));
        productoRepository.delete(producto);
        log.info("Producto eliminado con ID: {}", id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductoDTO> buscarPorNombre(String nombre) {
        log.debug("Buscando productos por nombre: {}", nombre);
        List<Producto> productos = productoRepository.findByNombreContainingIgnoreCase(nombre);
        return productos.stream()
                .map(this::convertirEntidadADTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductoDTO> buscarPorCategoria(String categoria) {
        log.debug("Buscando productos por categoría: {}", categoria);
        List<Producto> productos = productoRepository.findByCategoria(categoria);
        return productos.stream()
                .map(this::convertirEntidadADTO)
                .collect(Collectors.toList());
    }

    private ProductoDTO convertirEntidadADTO(Producto producto) {
        ProductoDTO dto = new ProductoDTO();
        dto.setId(producto.getId());
        dto.setNombre(producto.getNombre());
        dto.setDescripcion(producto.getDescripcion());
        dto.setPrecio(producto.getPrecio());
        dto.setStock(producto.getStock());
        dto.setCategoria(producto.getCategoria());
        return dto;
    }

    private Producto convertirDTOAEntidad(ProductoDTO dto) {
        Producto producto = new Producto();
        producto.setNombre(dto.getNombre());
        producto.setDescripcion(dto.getDescripcion());
        producto.setPrecio(dto.getPrecio());
        producto.setStock(dto.getStock());
        producto.setCategoria(dto.getCategoria());
        return producto;
    }
}
