
import java.util.Scanner;

/**
 *
 * @author joacodiaz
 */
public class AnalizadorPersonajes {
    SimpleSet<Personaje> personajes = new SimpleArraySet<>();
    SimpleSet<String> habilidades = new SimpleArraySet<>();
    Scanner scanner = new Scanner(System.in);

    public AnalizadorPersonajes() {
        habilidades.add("Espada");
        habilidades.add("Arco");
        habilidades.add("Magia de fuego");
        habilidades.add("Magia de hielo");
        habilidades.add("Curacion");
        habilidades.add("Sigilo");
        habilidades.add("Doble salto");
        habilidades.add("Escudo");
        habilidades.add("Volar");
        habilidades.add("Velocidad");

        Personaje pikachu = new Personaje("Pikachu");
        pikachu.agregarHabilidad("Impactrueno");
        pikachu.agregarHabilidad("Ataque rápido");
        pikachu.agregarHabilidad("Placaje");
        personajes.add(pikachu);

        Personaje charizard = new Personaje("Charizard");
        charizard.agregarHabilidad("Lanzallamas");
        charizard.agregarHabilidad("Vuelo");
        charizard.agregarHabilidad("Garra dragón");
        personajes.add(charizard);

        Personaje greninja = new Personaje("Greninja");
        greninja.agregarHabilidad("Shuriken de agua");
        greninja.agregarHabilidad("Ataque rápido");
        greninja.agregarHabilidad("Doble equipo");
        greninja.agregarHabilidad("Corte");
        personajes.add(greninja);

        Personaje alakazam = new Personaje("Alakazam");
        alakazam.agregarHabilidad("Psíquico");
        alakazam.agregarHabilidad("Recuperación");
        alakazam.agregarHabilidad("Teletransporte");
        alakazam.agregarHabilidad("Paz mental");
        personajes.add(alakazam);

        Personaje lucario = new Personaje("Lucario");
        lucario.agregarHabilidad("Esfera aural");
        lucario.agregarHabilidad("Velocidad extrema");
        lucario.agregarHabilidad("Puño meteoro");
        lucario.agregarHabilidad("Danza espada");
        personajes.add(lucario);
    }

    public void consolaPersonajes(){
        boolean salir = false;
        System.out.println("Bienvenido al analizador de personajes!!");
        System.out.println("Sus opciones son:");

        while (!salir) {

            System.out.println("---------------------------------");
            System.out.println("1. Ver personajes");
            System.out.println("2. Ver habilidades");
            System.out.println("3. Crear personaje");
            System.out.println("4. Crear habilidad");
            System.out.println("5. Asignar habilidad a un personaje");
            System.out.println("6. Quitar habilidad a un personaje");
            System.out.println("7. Comparar dos personajes");
            System.out.println("0. Salir");
            System.out.println("---------------------------------");

            System.out.print("Elija una opcion: ");
            String opcion = scanner.nextLine();

            switch (opcion) {
                case "1":
                    verPersonajes();
                    break;
                case "2":
                    verHabilidades();
                    break;
                case "3":
                    crearPersonaje();
                    break;
                case "4":
                    crearHabilidad();
                    break;
                case "5":
                    asignarHabilidad();
                    break;
                case "6":
                    quitarHabilidad();
                    break;
                case "7":
                    compararPersonajes();
                    break;
                case "0":
                    salir = true;
                    System.out.println("Gracias por usar el analizador!");
                    break;
                default:
                    System.out.println("Opcion invalida, intente de nuevo.");
            }
        }
    }

    public void verPersonajes(){
        if(personajes.isEmpty()){
            System.out.println("No hay personajes cargados.");
            return;
        }
        System.out.println("Personajes: ");
        Personaje[] lista = personajes.toArray(new Personaje[0]);
        for (int i = 0; i < lista.length; i++) {
            System.out.println((i + 1) + ". " + lista[i]);
        }
    }

    public void verHabilidades(){
        if(habilidades.isEmpty()){
            System.out.println("No hay habilidades cargadas.");
            return;
        }
        System.out.println("Habilidades: ");
        String[] lista = habilidades.toArray(new String[0]);
        for (int i = 0; i < lista.length; i++) {
            System.out.println((i + 1) + ". " + lista[i]);
        }
    }

    public void crearPersonaje(){
        System.out.println("Ingrese el nombre del personaje:");
        String nombre = scanner.nextLine().trim();

        if(nombre.isEmpty()){
            System.err.println("El nombre no puede estar vacio.");
            return;
        }
        if(buscarPersonaje(nombre) != null){
            System.err.println("Ya existe un personaje con ese nombre.");
            return;
        }
        personajes.add(new Personaje(nombre));
        System.out.println("Personaje " + nombre + " creado!");
    }

    public void crearHabilidad(){
        System.out.println("Ingrese el nombre de la habilidad:");
        String nombre = scanner.nextLine().trim();

        if(nombre.isEmpty()){
            System.err.println("El nombre no puede estar vacio.");
            return;
        }
        if(buscarHabilidad(nombre) != null){
            System.err.println("Esa habilidad ya existe.");
            return;
        }
        habilidades.add(nombre);
        System.out.println("Habilidad " + nombre + " creada!");
    }

    public void asignarHabilidad(){
        Personaje personaje = pedirPersonaje("Ingrese el nombre del personaje:");
        if(personaje == null){
            return;
        }

        SimpleSet<String> disponibles = habilidades.differenceWith(personaje.getHabilidades());
        if(disponibles.isEmpty()){
            System.err.println(personaje.getNombre() + " ya tiene todas las habilidades.");
            return;
        }
        System.out.println("Habilidades que puede aprender: " + disponibles);
        System.out.println("Ingrese la habilidad a asignar:");
        String habilidad = buscarHabilidad(scanner.nextLine().trim());

        if(habilidad == null){
            System.err.println("Esa habilidad no existe, primero tiene que crearla.");
        } else if(!personaje.agregarHabilidad(habilidad)){
            System.err.println(personaje.getNombre() + " ya tiene esa habilidad.");
        } else {
            System.out.println("Se le asigno " + habilidad + " a " + personaje.getNombre() + "!");
        }
    }

    public void quitarHabilidad(){
        Personaje personaje = pedirPersonaje("Ingrese el nombre del personaje:");
        if(personaje == null){
            return;
        }
        if(personaje.getHabilidades().isEmpty()){
            System.err.println(personaje.getNombre() + " no tiene habilidades.");
            return;
        }
        System.out.println("Habilidades de " + personaje.getNombre() + ": " + personaje.getHabilidades());
        System.out.println("Ingrese la habilidad a quitar:");
        String habilidad = buscarHabilidad(scanner.nextLine().trim());

        if(habilidad == null || !personaje.quitarHabilidad(habilidad)){
            System.err.println(personaje.getNombre() + " no tiene esa habilidad.");
        } else {
            System.out.println("Se le quito " + habilidad + " a " + personaje.getNombre() + "!");
        }
    }

    public void compararPersonajes(){
        if(personajes.size() < 2){
            System.err.println("Tiene que haber al menos 2 personajes para comparar.");
            return;
        }
        Personaje p1 = pedirPersonaje("Ingrese el nombre del primer personaje:");
        if(p1 == null){
            return;
        }
        Personaje p2 = pedirPersonaje("Ingrese el nombre del segundo personaje:");
        if(p2 == null){
            return;
        }
        if(p1.equals(p2)){
            System.err.println("Tiene que elegir dos personajes distintos.");
            return;
        }

        SimpleSet<String> enComun = p1.getHabilidades().intersectWith(p2.getHabilidades());
        SimpleSet<String> soloP1 = p1.getHabilidades().differenceWith(p2.getHabilidades());
        SimpleSet<String> soloP2 = p2.getHabilidades().differenceWith(p1.getHabilidades());
        SimpleSet<String> todas = p1.getHabilidades().unionWith(p2.getHabilidades());

        System.out.println("--- " + p1.getNombre() + " vs " + p2.getNombre() + " ---");
        System.out.println("En comun: " + enComun);
        System.out.println("Solo " + p1.getNombre() + ": " + soloP1);
        System.out.println("Solo " + p2.getNombre() + ": " + soloP2);
        System.out.println("Entre los dos tienen: " + todas);
        System.out.println("-------------------------------------");
    }

    // Pide un nombre y devuelve el personaje, o null si no existe
    private Personaje pedirPersonaje(String mensaje){
        if(personajes.isEmpty()){
            System.err.println("No hay personajes cargados.");
            return null;
        }
        System.out.println(mensaje);
        Personaje personaje = buscarPersonaje(scanner.nextLine().trim());
        if(personaje == null){
            System.err.println("No existe un personaje con ese nombre.");
        }
        return personaje;
    }

    private Personaje buscarPersonaje(String nombre){
        if(nombre.isEmpty()){
            return null;
        }
        Personaje[] lista = personajes.toArray(new Personaje[0]);
        for (int i = 0; i < lista.length; i++) {
            if(lista[i].getNombre().toLowerCase().equals(nombre.toLowerCase())){
                return lista[i];
            }
        }
        return null;
    }

    private String buscarHabilidad(String nombre){
        if(nombre.isEmpty()){
            return null;
        }
        String[] lista = habilidades.toArray(new String[0]);
        for (int i = 0; i < lista.length; i++) {
            if(lista[i].toLowerCase().equals(nombre.toLowerCase())){
                return lista[i];
            }
        }
        return null;
    }
}
