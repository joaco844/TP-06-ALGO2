/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */

/**
 *
 * @author joacodiaz
 */
public interface SimpleSet<E> {
    public boolean add(E element);
    public boolean remove(E element);
    public boolean contains(E element);
    public void clear();
    public int size();
    public boolean isEmpty();
    public E[] toArray(E[] typeArray);
    public SimpleSet<E> unionWith(SimpleSet<E> other);
    public SimpleSet<E> intersectWith(SimpleSet<E> other);
    public SimpleSet<E> differenceWith(SimpleSet<E> other);
}
