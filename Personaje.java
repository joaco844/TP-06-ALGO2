/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author joacodiaz
 */
public class Personaje {
    private String nombre;
    private SimpleSet<String> habilidades;

    public Personaje(String nombre) {
        this.nombre = nombre;
        this.habilidades = new SimpleArraySet<>();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public SimpleSet<String> getHabilidades() {
        return habilidades;
    }

    public boolean agregarHabilidad(String habilidad) {
        return habilidades.add(habilidad);
    }

    public boolean quitarHabilidad(String habilidad) {
        return habilidades.remove(habilidad);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Personaje)) {
            return false;
        }
        Personaje otro = (Personaje) obj;
        return nombre.equalsIgnoreCase(otro.nombre);
    }
    
    @Override
    public String toString() {
        return nombre + " " + habilidades;
    }
}
