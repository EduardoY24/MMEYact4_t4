package com.eymm.mmeyact4_t4.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.eymm.mmeyact4_t4.dto.VideojuegoRequest;
import com.eymm.mmeyact4_t4.dto.VideojuegoResponse;
import com.eymm.mmeyact4_t4.exception.NoEncontradoException;
import com.eymm.mmeyact4_t4.models.Plataforma;
import com.eymm.mmeyact4_t4.models.Videojuego;
import com.eymm.mmeyact4_t4.repository.PlataformaRepository;
import com.eymm.mmeyact4_t4.repository.VideojuegoRepository;

@Service
public class VideojuegoService {

    @Autowired
    private VideojuegoRepository videojuegoRepo;

    @Autowired
    private PlataformaRepository plataformaRepo;

    public Page<VideojuegoResponse> listar(Pageable pageable) {
        return videojuegoRepo.findAll(pageable).map(this::convertir);
    }

    public VideojuegoResponse buscar(Integer id) {
        return convertir(buscarVideojuego(id));
    }

    public VideojuegoResponse crear(VideojuegoRequest request) {
        Videojuego videojuego = new Videojuego();
        llenar(videojuego, request);
        return convertir(videojuegoRepo.save(videojuego));
    }

    public VideojuegoResponse actualizar(Integer id, VideojuegoRequest request) {
        Videojuego videojuego = buscarVideojuego(id);
        llenar(videojuego, request);
        return convertir(videojuegoRepo.save(videojuego));
    }

    public void eliminar(Integer id) {
        Videojuego videojuego = buscarVideojuego(id);
        videojuegoRepo.delete(videojuego);
    }

    private Videojuego buscarVideojuego(Integer id) {
        return videojuegoRepo.findById(id)
                .orElseThrow(() -> new NoEncontradoException("No existe el videojuego con id " + id));
    }

    private void llenar(Videojuego videojuego, VideojuegoRequest request) {
        Plataforma plataforma = plataformaRepo.findById(request.getPlataformaId())
                .orElseThrow(() -> new NoEncontradoException("No existe la plataforma con id " + request.getPlataformaId()));
        videojuego.setTitulo(request.getTitulo());
        videojuego.setGenero(request.getGenero());
        videojuego.setPrecio(request.getPrecio());
        videojuego.setPlataforma(plataforma);
    }

    private VideojuegoResponse convertir(Videojuego v) {
        return new VideojuegoResponse(
                v.getId(),
                v.getTitulo(),
                v.getGenero(),
                v.getPrecio(),
                v.getPlataforma().getNombre());
    }
}