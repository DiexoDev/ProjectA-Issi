package com.diegobpz.Issi.com.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;


public interface AssetsService {

    public void iniciarAlmacenamientoDeArchivos();

    public String guardarArchivo(MultipartFile imagen);

    public Path cargarArchivo(String nombreArchivo);

    public Resource cargarComoRecurso(String nombreArchivo);

    public void eliminarArchivo(String nombreArchivo);

}
