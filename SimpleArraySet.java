/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.util.Arrays;

/**
 *
 * @author joacodiaz
 */
public class SimpleArraySet<E> implements SimpleSet<E> {

    private E[] array;
    private int size = 0;
    private static final int DEFAULT_SIZE = 4;

    @SuppressWarnings("unchecked")
    public SimpleArraySet() {
        array = (E[]) new Object[DEFAULT_SIZE];
    }

    @Override
    public boolean add(E element) {
        validateElement(element);
        // Si ya esta no lo agregamos, en un set no hay repetidos
        if (contains(element))
            return false;
        validateSize(size + 1);
        array[size] = element;
        size++;
        return true;
    }

    @Override
    public boolean remove(E element) {
        validateElement(element);
        int index = indexOf(element);
        if (index == -1)
            return false;
        shiftLeft(index);
        size--;
        return true;
    }

    @Override
    public boolean contains(E element) {
        validateElement(element);
        return indexOf(element) != -1;
    }

    @Override
    public void clear() {
        array = (E[]) new Object[size];
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public E[] toArray(E[] typeArray) {
        if (typeArray == null)
            throw new IllegalArgumentException("El array no puede ser nulo");

        // Si el array que nos pasan es chico, creamos uno del mismo tipo con el tamaño justo
        if (typeArray.length < size)
            typeArray = Arrays.copyOf(typeArray, size);

        for (int i = 0; i < size; i++)
            typeArray[i] = array[i];

        return typeArray;
    }

    @Override
    public SimpleSet<E> unionWith(SimpleSet<E> other) {
        validateSet(other);
        SimpleSet<E> result = new SimpleArraySet<>();

        // Agregamos todos los de este set
        for (int i = 0; i < size; i++)
            result.add(array[i]);

        // Agregamos todos los del otro, los repetidos el add los ignora solo
        E[] otherarray = other.toArray(Arrays.copyOf(array, 0));
        for (int i = 0; i < otherarray.length; i++)
            result.add(otherarray[i]);

        return result;
    }

    @Override
    public SimpleSet<E> intersectWith(SimpleSet<E> other) {
        validateSet(other);
        SimpleSet<E> result = new SimpleArraySet<>();

        // Nos quedamos con los que estan en los dos
        for (int i = 0; i < size; i++) {
            if (other.contains(array[i]))
                result.add(array[i]);
        }
        return result;
    }

    @Override
    public SimpleSet<E> differenceWith(SimpleSet<E> other) {
        validateSet(other);
        SimpleSet<E> result = new SimpleArraySet<>();

        // Nos quedamos con los que estan en este y NO en el otro
        for (int i = 0; i < size; i++) {
            if (!other.contains(array[i]))
                result.add(array[i]);
        }
        return result;
    }

    private int indexOf(E element) {
        for (int i = 0; i < size; i++) {
            if (array[i].equals(element))
                return i;
        }
        return -1;
    }

    private void validateElement(E element) {
        if (element == null)
            throw new IllegalArgumentException("El elemento no puede ser nulo");
    }

    private void validateSet(SimpleSet<E> other) {
        if (other == null)
            throw new IllegalArgumentException("El set no puede ser nulo");
    }

    private void validateSize(int newSize) {
        if (newSize >= array.length)
            resize();
    }

    @SuppressWarnings("unchecked")
    private void resize() {
        // Creamos un nuevo array del doble de largo que el actual
        E[] nuevoArray = (E[]) new Object[array.length * 2];

        // Copiamos todo lo que esta en array al nuevo
        for (int i = 0; i < size; i++)
            nuevoArray[i] = array[i];

        array = nuevoArray;
    }

    private void shiftLeft(int index) {
        // Corremos cada elemento a la izquierda
        for (int i = index; i < size - 1; i++)
            array[i] = array[i + 1];

        // Borramos el ultimo para que no quede duplicado
        array[size - 1] = null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < size; i++) {
            sb.append(array[i]);
            if (i < size - 1) sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }

    private E[] E() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
