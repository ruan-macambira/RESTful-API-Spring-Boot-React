package com.rhgreat.api.restful;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class UsuarioController {
    private final UsuarioRepository repository;
    private final UsuarioModelAssembler assembler;

    UsuarioController(UsuarioRepository repository, UsuarioModelAssembler assembler) {
        this.repository = repository;
        this.assembler = assembler;
    }

    // tag::get-aggregate-root[]
    @GetMapping("/usuarios")
    CollectionModel<EntityModel<Usuario>> all() {
        List<EntityModel<Usuario>> usuarios = repository.findAll().stream()
            .map(assembler::toModel)
            .collect(Collectors.toList());
        
        return CollectionModel.of(usuarios, linkTo(methodOn(UsuarioController.class).all()).withSelfRel());
    }
    // end::get-aggregate-root[]

    @PostMapping("/usuarios")
    ResponseEntity<?> newUsuario(@RequestBody Usuario newUsuario) {
        EntityModel<Usuario> entityModel = assembler.toModel(repository.save(newUsuario));

        return ResponseEntity
            .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
            .body(entityModel);
    }

    @GetMapping("/usuarios/{id}")
    EntityModel<Usuario> one(@PathVariable Long id) {
        Usuario usuario = repository.findById(id).orElseThrow(() -> new UsuarioNotFoundException(id));

        return assembler.toModel(usuario);
    }

    @PutMapping("/usuarios/{id}")
    ResponseEntity<?> replaceUsuario(@RequestBody Usuario newUsuario, @PathVariable Long id) {
        Usuario updatedUsuario = repository.findById(id)
            .map(usuario -> {
                usuario.setNome(newUsuario.getNome());
                return repository.save(usuario);
            })
            .orElseGet(() -> {
                newUsuario.setId(id);
                return repository.save(newUsuario);
        });
        EntityModel<Usuario> entityModel = assembler.toModel(updatedUsuario);

        return ResponseEntity
            .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
            .body(entityModel);
    }

    @DeleteMapping("/usuarios/{id}")
    ResponseEntity<?> deleteUsuario(@PathVariable Long id) {
        repository.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}
