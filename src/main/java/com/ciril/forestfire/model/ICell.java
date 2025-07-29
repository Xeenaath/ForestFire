package com.ciril.forestfire.model;

public interface ICell {
    boolean isBurning();
    boolean isBurnable();
    void burn();
    void hasBurnt();
    Position getPosition();

}
