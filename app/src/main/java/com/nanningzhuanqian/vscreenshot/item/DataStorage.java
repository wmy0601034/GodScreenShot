package com.nanningzhuanqian.vscreenshot.item;

import java.util.List;

public interface DataStorage<E> {

    void add(E e);

    void add(E e, int location);

    void add(List<E> e);

    boolean contains(E e);

    boolean contains(String attributeName, Object attributeValue);

    E get(String attributeName, Object attributeValue);

    List<E> get();

    E get(int location);

    void remove(int location);

    void remove(int location1, int location2);

    void remove(E e);

    void removeAll(List<E> e);

    int size();

    boolean isEmpty();

    void clear();

    void addFirst(E e);

}
