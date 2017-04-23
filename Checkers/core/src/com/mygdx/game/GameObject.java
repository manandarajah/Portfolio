package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;

/**
 * Created by My-Laptop on 19/03/2017.
 */

public abstract class GameObject {

    private Texture object;
    private String objectType;
    private int x, y;

    public GameObject(Texture object, String objectType, int x, int y) {
        this.object = object;
        this.objectType = objectType;
        this.x = x;
        this.y = y;
    }

    protected void relocate(int x, int y) {
        this.x = x;
        this.y = y;
    }
    protected void setObject(Texture object) { this.object = object; }
    protected Texture getObject() { return this.object; }
    protected String getObjectType() { return this.objectType; }
    protected int getX() { return this.x; }
    protected int getY() { return this.y; }
}
