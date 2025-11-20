package com.miempresa.api_tienda.controller;

import com.miempresa.api_tienda.dto.ProductoDTO;
import com.miempresa.api_tienda.service.ProductoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Productos", description = "API para la gestión de productos")
public class ProductoController {

    private final ProductoService productoService;

    @Operation(summary = "Crear un nuevo producto", description = "Crea un nuevo producto en el sistema")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Producto creado exitosamente"),
        @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos")
    })
    @PostMapping
    public ResponseEntity<ProductoDTO> crear(@Valid @RequestBody ProductoDTO productoDTO) {
        log.info("Solicitud para crear producto: {}", productoDTO.getNombre());
        ProductoDTO productoCreado = productoService.crear(productoDTO);
        return new ResponseEntity<>(productoCreado, HttpStatus.CREATED);
    }

    @Operation(summary = "Obtener producto por ID", description = "Obtiene un producto específico por su ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Producto encontrado"),
        @ApiResponse(responseCode = "404", description = "Producto no encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<ProductoDTO> obtenerPorId(
            @Parameter(description = "ID del producto") @PathVariable Long id) {
        log.info("Solicitud para obtener producto con ID: {}", id);
        ProductoDTO producto = productoService.obtenerPorId(id);
        return ResponseEntity.ok(producto);
    }

    @Operation(summary = "Listar todos los productos", description = "Obtiene una lista de todos los productos")
    @ApiResponse(responseCode = "200", description = "Lista de productos obtenida")
    @GetMapping
    public ResponseEntity<List<ProductoDTO>> obtenerTodos() {
        log.info("Solicitud para obtener todos los productos");
        List<ProductoDTO> productos = productoService.obtenerTodos();
        return ResponseEntity.ok(productos);
    }

    @Operation(summary = "Actualizar producto", description = "Actualiza un producto existente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Producto actualizado exitosamente"),
        @ApiResponse(responseCode = "404", description = "Producto no encontrado"),
        @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos")
    })
    @PutMapping("/{id}")
    public ResponseEntity<ProductoDTO> actualizar(
            @Parameter(description = "ID del producto") @PathVariable Long id,
            @Valid @RequestBody ProductoDTO productoDTO) {
        log.info("Solicitud para actualizar producto con ID: {}", id);
        ProductoDTO productoActualizado = productoService.actualizar(id, productoDTO);
        return ResponseEntity.ok(productoActualizado);
    }

    @Operation(summary = "Eliminar producto", description = "Elimina un producto del sistema")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Producto eliminado exitosamente"),
        @ApiResponse(responseCode = "404", description = "Producto no encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(
            @Parameter(description = "ID del producto") @PathVariable Long id) {
        log.info("Solicitud para eliminar producto con ID: {}", id);
        productoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Buscar productos por nombre", description = "Busca productos que contengan el nombre especificado")
    @ApiResponse(responseCode = "200", description = "Lista de productos encontrados")
    @GetMapping("/buscar/nombre")
    public ResponseEntity<List<ProductoDTO>> buscarPorNombre(
            @Parameter(description = "Nombre a buscar") @RequestParam String nombre) {
        log.info("Solicitud para buscar productos por nombre: {}", nombre);
        List<ProductoDTO> productos = productoService.buscarPorNombre(nombre);
        return ResponseEntity.ok(productos);
    }

    @Operation(summary = "Buscar productos por categoría", description = "Busca productos de una categoría específica")
    @ApiResponse(responseCode = "200", description = "Lista de productos encontrados")
    @GetMapping("/buscar/categoria")
    public ResponseEntity<List<ProductoDTO>> buscarPorCategoria(
            @Parameter(description = "Categoría a buscar") @RequestParam String categoria) {
        log.info("Solicitud para buscar productos por categoría: {}", categoria);
        List<ProductoDTO> productos = productoService.buscarPorCategoria(categoria);
        return ResponseEntity.ok(productos);
    }
}
