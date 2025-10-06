package UsuariosYAficionesConcordantes;


import Objetos.Persona;

import java.io.Serializable;

public class Usuarios implements Serializable {
    private String aficiones;
    private int codigo;

    public void Persona(int codigo, String aficiones) {
        this.codigo = codigo;
        this.aficiones = aficiones;
    }

    public String getAficiones() {
        return aficiones;
    }
    public void setAficiones(String aficiones) {
        this.aficiones = aficiones;
    }
    public int getCodigo() {
        return codigo;
    }
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
}
