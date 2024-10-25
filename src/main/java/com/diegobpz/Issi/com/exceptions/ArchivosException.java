package com.diegobpz.Issi.com.exceptions;

public class ArchivosException extends RuntimeException{
    public ArchivosException(String mensaje) {
        super(mensaje);
    }

    public ArchivosException(String mensaje, Throwable exception) {
        super(mensaje, exception);
    }
}
