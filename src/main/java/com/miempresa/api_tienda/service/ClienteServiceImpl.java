package com.miempresa.api_tienda.service;

import com.miempresa.api_tienda.dto.ClienteDTO;
import com.miempresa.api_tienda.entity.Cliente;
import com.miempresa.api_tienda.exception.BadRequestException;
import com.miempresa.api_tienda.exception.ResourceNotFoundException;
import com.miempresa.api_tienda.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepository;

    @Override
    @Transactional
    public ClienteDTO crear(ClienteDTO clienteDTO) {
        log.debug("Creando nuevo cliente: {} {}", clienteDTO.getNombre(), clienteDTO.getApellido());
        
        // Verificar si el email ya existe
        if (clienteRepository.findByEmail(clienteDTO.getEmail()).isPresent()) {
            throw new BadRequestException("Ya existe un cliente con el email: " + clienteDTO.getEmail());
        }
        
        Cliente cliente = convertirDTOAEntidad(clienteDTO);
        Cliente clienteGuardado = clienteRepository.save(cliente);
        log.info("Cliente creado con ID: {}", clienteGuardado.getId());
        return convertirEntidadADTO(clienteGuardado);
    }

    @Override
    @Transactional(readOnly = true)
    public ClienteDTO obtenerPorId(Long id) {
        log.debug("Buscando cliente con ID: {}", id);
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente", "id", id));
        return convertirEntidadADTO(cliente);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ClienteDTO> obtenerTodos() {
        log.debug("Obteniendo todos los clientes");
        List<Cliente> clientes = clienteRepository.findAll();
        return clientes.stream()
                .map(this::convertirEntidadADTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public ClienteDTO actualizar(Long id, ClienteDTO clienteDTO) {
        log.debug("Actualizando cliente con ID: {}", id);
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente", "id", id));
        
        // Verificar si el email ya existe en otro cliente
        clienteRepository.findByEmail(clienteDTO.getEmail()).ifPresent(clienteExistente -> {
            if (!clienteExistente.getId().equals(id)) {
                throw new BadRequestException("Ya existe un cliente con el email: " + clienteDTO.getEmail());
            }
        });
        
        cliente.setNombre(clienteDTO.getNombre());
        cliente.setApellido(clienteDTO.getApellido());
        cliente.setEmail(clienteDTO.getEmail());
        cliente.setTelefono(clienteDTO.getTelefono());
        cliente.setDireccion(clienteDTO.getDireccion());
        cliente.setCiudad(clienteDTO.getCiudad());
        cliente.setPais(clienteDTO.getPais());
        
        Cliente clienteActualizado = clienteRepository.save(cliente);
        log.info("Cliente actualizado con ID: {}", clienteActualizado.getId());
        return convertirEntidadADTO(clienteActualizado);
    }

    @Override
    @Transactional
    public void eliminar(Long id) {
        log.debug("Eliminando cliente con ID: {}", id);
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente", "id", id));
        clienteRepository.delete(cliente);
        log.info("Cliente eliminado con ID: {}", id);
    }

    @Override
    @Transactional(readOnly = true)
    public ClienteDTO buscarPorEmail(String email) {
        log.debug("Buscando cliente por email: {}", email);
        Cliente cliente = clienteRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente", "email", email));
        return convertirEntidadADTO(cliente);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ClienteDTO> buscarPorNombre(String nombre) {
        log.debug("Buscando clientes por nombre: {}", nombre);
        List<Cliente> clientes = clienteRepository
                .findByNombreContainingIgnoreCaseOrApellidoContainingIgnoreCase(nombre, nombre);
        return clientes.stream()
                .map(this::convertirEntidadADTO)
                .collect(Collectors.toList());
    }

    private ClienteDTO convertirEntidadADTO(Cliente cliente) {
        ClienteDTO dto = new ClienteDTO();
        dto.setId(cliente.getId());
        dto.setNombre(cliente.getNombre());
        dto.setApellido(cliente.getApellido());
        dto.setEmail(cliente.getEmail());
        dto.setTelefono(cliente.getTelefono());
        dto.setDireccion(cliente.getDireccion());
        dto.setCiudad(cliente.getCiudad());
        dto.setPais(cliente.getPais());
        return dto;
    }

    private Cliente convertirDTOAEntidad(ClienteDTO dto) {
        Cliente cliente = new Cliente();
        cliente.setNombre(dto.getNombre());
        cliente.setApellido(dto.getApellido());
        cliente.setEmail(dto.getEmail());
        cliente.setTelefono(dto.getTelefono());
        cliente.setDireccion(dto.getDireccion());
        cliente.setCiudad(dto.getCiudad());
        cliente.setPais(dto.getPais());
        return cliente;
    }
}
