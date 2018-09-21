package com.nanningzhuanqian.vscreenshot.item;

import java.lang.reflect.Field;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public abstract class DataStorageImpl<E> implements DataStorage<E> {

    protected LinkedList<E> data;

    public DataStorageImpl() {
        this.data = new LinkedList<>();
    }

    @Override
    public void add(E e) {
        this.data.add(e);
    }

    public void add(E e, int location) {
        if (!this.contains(e)) {
            this.data.add(0, e);
        }
    }

    @Override
    public void add(List<E> e) {
        this.data.addAll(e);
    }

    @Override
    public boolean contains(E e) {
        return this.data.contains(e);
    }

    @Override
    public boolean contains(String attributeName, Object attributeValue) {

        for (E e : this.data) {

            try {
                Field field = e.getClass().getField(attributeName);
                Object fieldValue = field.get(e);
                if (fieldValue.equals(attributeValue))
                    return true;
            } catch (NoSuchFieldException | IllegalAccessException
                    | IllegalArgumentException e1) {
            }

        }
        return false;
    }

    @Override
    public E get(String attributeName, Object attributeValue) {

        for (E e : this.data) {

            try {
                if (e != null) {
                    Field field = e.getClass().getField(attributeName);
                    Object fieldValue = field.get(e);
                    if (fieldValue.equals(attributeValue))
                        return e;

                }
            } catch (NoSuchFieldException | IllegalAccessException
                    | IllegalArgumentException e1) {
                // e1.printStackTrace();
            }

        }
        return null;
    }

    @Override
    public List<E> get() {
        return this.data;
    }

    @Override
    public E get(int location) {
        return this.data.get(location);
    }

    @Override
    public void remove(int location) {

        if (location >= 0 && location < this.data.size()) {
            this.data.remove(location);
        }
    }

    @Override
    public void remove(E e) {
        this.data.remove(e);
    }

    @Override
    public int size() {
        return this.data.size();
    }

    @Override
    public boolean isEmpty() {
        return this.data.isEmpty();
    }

    @Override
    public void clear() {
        this.data.clear();
    }

    @Override
    public void addFirst(E e) {
        this.data.addFirst(e);
    }

    @Override
    public void removeAll(List<E> e) {
        this.data.removeAll(e);
    }

    @Override
    public void remove(int location1, int location2) {
        for(int i = location1;i<data.size();i++){
            data.remove(location1);
            i--;
        }
    }

}
