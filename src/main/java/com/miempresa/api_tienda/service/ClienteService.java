package com.miempresa.api_tienda.service;

import com.miempresa.api_tienda.dto.ClienteDTO;

import java.util.List;

public interface ClienteService {
    
    ClienteDTO crear(ClienteDTO clienteDTO);
    
    ClienteDTO obtenerPorId(Long id);
    
    List<ClienteDTO> obtenerTodos();
    
    ClienteDTO actualizar(Long id, ClienteDTO clienteDTO);
    
    void eliminar(Long id);
    
    ClienteDTO buscarPorEmail(String email);
    
    List<ClienteDTO> buscarPorNombre(String nombre);
}
