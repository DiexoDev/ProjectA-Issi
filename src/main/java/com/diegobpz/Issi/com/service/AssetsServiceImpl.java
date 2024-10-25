package com.diegobpz.Issi.com.service;

import com.diegobpz.Issi.com.exceptions.ArchivosException;
import com.diegobpz.Issi.com.exceptions.FileNotFoundException;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class AssetsServiceImpl implements AssetsService {
    @Value("${storage.location}")
    private String storageLocation;

    @PostConstruct //Ejecuta el metodo automáticamente al iniciar un ProductoServiceImpl.
    @Override
    public void iniciarAlmacenamientoDeArchivos() {
        try {
            //Crea un directorio que indica donde almacenará las imagenes.
            Files.createDirectories(Paths.get(storageLocation));
        } catch (IOException exception) {
            throw new ArchivosException("Error al iniciar la ubicación en el almacen de archivos.");
        }

    }

    @Override
    public String guardarArchivo(MultipartFile archivo) {
        String nombreArchivo = archivo.getOriginalFilename();
        if (archivo.isEmpty()) {
            throw new ArchivosException("No se puede almacenar un archivo vacío.");
        }
        try {
            InputStream inputStream = archivo.getInputStream();
            Files.copy(inputStream, Paths.get(storageLocation).resolve(nombreArchivo), StandardCopyOption.REPLACE_EXISTING);
            //Obtengo el inputStream del archivo, luego paso el nombre y si ingreso un archivo con el mismo nombre los va a reemplazar.
        } catch (IOException exception) {
            throw new ArchivosException("Error al guardar el archivo " + nombreArchivo, exception);
        }
        return nombreArchivo;
    }

    @Override
    public Path cargarArchivo(String nombreArchivo) {
        return Paths.get(storageLocation).resolve(nombreArchivo);
    }

    @Override
    public Resource cargarComoRecurso(String nombreArchivo) {
        try {
            Path archivo = cargarArchivo(nombreArchivo);
            Resource recurso = new UrlResource(archivo.toUri());
            if (recurso.exists() || recurso.isReadable()) {
                return recurso;
            } else {
                throw new FileNotFoundException("No se pudo encontrar el archivo");
            }
        } catch (MalformedURLException exception) {
            throw new FileNotFoundException("No se pudo encontrar el archivo");
        }
    }

    @Override
    public void eliminarArchivo(String nombreArchivo) {
        Path archivo = cargarArchivo(nombreArchivo);
        try {
            FileSystemUtils.deleteRecursively(archivo);
        } catch (IOException excepcion) {
            throw new ArchivosException("Error al eliminar el archivo " + nombreArchivo, excepcion);
        }
    }
}
