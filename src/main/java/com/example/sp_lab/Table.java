package com.example.sp_lab;

import jakarta.persistence.Entity;

// TABLE is a reserved SQL keyword, so the table needs another name (fully qualified: this class is also called Table)
@Entity
@jakarta.persistence.Table(name = "TableElement")
public class Table extends BaseElement {
    @Override
    public void print() {
        System.out.println("Table");
    }

    @Override
    public void addElement(Element e) {
        throw new UnsupportedOperationException("Table cannot contain children");
    }

    @Override
    public Element getElement(int i) {
        throw new UnsupportedOperationException("Table cannot contain children");
    }

    @Override
    public void removeElement(Element e) {
        throw new UnsupportedOperationException("Table cannot contain children");
    }

    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }
}