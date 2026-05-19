package com.example.Mediturno.Controller;

import com.example.Mediturno.DTO.usuario.UsuarioResponseDTO;
import com.example.Mediturno.Model.Usuario;
import com.example.Mediturno.Service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Endpoints:
 *   GET    /api/usuarios              → listar todos       (ADMIN)
 *   GET    /api/usuarios/{id}         → obtener por id     (ADMIN)
 *   GET    /api/usuarios/email/{email}→ buscar por email   (ADMIN)
 *   POST   /api/usuarios              → crear usuario      (ADMIN)
 *   PUT    /api/usuarios/{id}         → actualizar         (ADMIN)
 *   DELETE /api/usuarios/{id}         → eliminar           (ADMIN)
 *
 * Todos los endpoints devuelven UsuarioResponseDTO (no la entidad directa)
 * para evitar referencias circulares en la serialización JSON.
 */
@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ROLE_ADMINISTRADOR')")
public class UsuarioController {

    private final UsuarioService usuarioService;

    // GET /api/usuarios — listar todos (RF3)
    @GetMapping
    public ResponseEntity<List<UsuarioResponseDTO>> listar() {
        List<UsuarioResponseDTO> dtos = usuarioService.obtenerUsuarios()
                .stream()
                .map(UsuarioResponseDTO::fromUsuario)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    // GET /api/usuarios/{id} — obtener por ID (RF3)
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> obtenerPorId(@PathVariable Long id) {
        return usuarioService.obtenerUsuarioPorId(id)
                .map(UsuarioResponseDTO::fromUsuario)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // GET /api/usuarios/email/{email} — buscar por email (RF4)
    @GetMapping("/email/{email}")
    public ResponseEntity<UsuarioResponseDTO> obtenerPorEmail(@PathVariable String email) {
        return usuarioService.obtenerUsuarioPorEmail(email)
                .map(UsuarioResponseDTO::fromUsuario)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST /api/usuarios — crear usuario (RF3)
    @PostMapping
    public ResponseEntity<UsuarioResponseDTO> crear(@RequestBody Usuario usuario) {
        Usuario creado = usuarioService.crearUsuario(usuario);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(UsuarioResponseDTO.fromUsuario(creado));
    }

    // PUT /api/usuarios/{id} — actualizar usuario (RF3)
    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> actualizar(
            @PathVariable Long id,
            @RequestBody Usuario usuario) {
        Usuario actualizado = usuarioService.actualizarUsuario(id, usuario);
        return ResponseEntity.ok(UsuarioResponseDTO.fromUsuario(actualizado));
    }

    // DELETE /api/usuarios/{id} — eliminar usuario (RF3)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        usuarioService.eliminarUsuario(id);
        return ResponseEntity.noContent().build();
    }
}