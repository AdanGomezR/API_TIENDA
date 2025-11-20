package com.miempresa.api_tienda.controller;

import com.miempresa.api_tienda.dto.ClienteDTO;
import com.miempresa.api_tienda.service.ClienteService;
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
@RequestMapping("/api/clientes")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Clientes", description = "API para la gestión de clientes")
public class ClienteController {

    private final ClienteService clienteService;

    @Operation(summary = "Crear un nuevo cliente", description = "Crea un nuevo cliente en el sistema")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Cliente creado exitosamente"),
        @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos o email duplicado")
    })
    @PostMapping
    public ResponseEntity<ClienteDTO> crear(@Valid @RequestBody ClienteDTO clienteDTO) {
        log.info("Solicitud para crear cliente: {} {}", clienteDTO.getNombre(), clienteDTO.getApellido());
        ClienteDTO clienteCreado = clienteService.crear(clienteDTO);
        return new ResponseEntity<>(clienteCreado, HttpStatus.CREATED);
    }

    @Operation(summary = "Obtener cliente por ID", description = "Obtiene un cliente específico por su ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Cliente encontrado"),
        @ApiResponse(responseCode = "404", description = "Cliente no encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<ClienteDTO> obtenerPorId(
            @Parameter(description = "ID del cliente") @PathVariable Long id) {
        log.info("Solicitud para obtener cliente con ID: {}", id);
        ClienteDTO cliente = clienteService.obtenerPorId(id);
        return ResponseEntity.ok(cliente);
    }

    @Operation(summary = "Listar todos los clientes", description = "Obtiene una lista de todos los clientes")
    @ApiResponse(responseCode = "200", description = "Lista de clientes obtenida")
    @GetMapping
    public ResponseEntity<List<ClienteDTO>> obtenerTodos() {
        log.info("Solicitud para obtener todos los clientes");
        List<ClienteDTO> clientes = clienteService.obtenerTodos();
        return ResponseEntity.ok(clientes);
    }

    @Operation(summary = "Actualizar cliente", description = "Actualiza un cliente existente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Cliente actualizado exitosamente"),
        @ApiResponse(responseCode = "404", description = "Cliente no encontrado"),
        @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos o email duplicado")
    })
    @PutMapping("/{id}")
    public ResponseEntity<ClienteDTO> actualizar(
            @Parameter(description = "ID del cliente") @PathVariable Long id,
            @Valid @RequestBody ClienteDTO clienteDTO) {
        log.info("Solicitud para actualizar cliente con ID: {}", id);
        ClienteDTO clienteActualizado = clienteService.actualizar(id, clienteDTO);
        return ResponseEntity.ok(clienteActualizado);
    }

    @Operation(summary = "Eliminar cliente", description = "Elimina un cliente del sistema")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Cliente eliminado exitosamente"),
        @ApiResponse(responseCode = "404", description = "Cliente no encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(
            @Parameter(description = "ID del cliente") @PathVariable Long id) {
        log.info("Solicitud para eliminar cliente con ID: {}", id);
        clienteService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Buscar cliente por email", description = "Busca un cliente por su dirección de email")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Cliente encontrado"),
        @ApiResponse(responseCode = "404", description = "Cliente no encontrado")
    })
    @GetMapping("/buscar/email")
    public ResponseEntity<ClienteDTO> buscarPorEmail(
            @Parameter(description = "Email a buscar") @RequestParam String email) {
        log.info("Solicitud para buscar cliente por email: {}", email);
        ClienteDTO cliente = clienteService.buscarPorEmail(email);
        return ResponseEntity.ok(cliente);
    }

    @Operation(summary = "Buscar clientes por nombre", description = "Busca clientes que contengan el nombre o apellido especificado")
    @ApiResponse(responseCode = "200", description = "Lista de clientes encontrados")
    @GetMapping("/buscar/nombre")
    public ResponseEntity<List<ClienteDTO>> buscarPorNombre(
            @Parameter(description = "Nombre a buscar") @RequestParam String nombre) {
        log.info("Solicitud para buscar clientes por nombre: {}", nombre);
        List<ClienteDTO> clientes = clienteService.buscarPorNombre(nombre);
        return ResponseEntity.ok(clientes);
    }
}
