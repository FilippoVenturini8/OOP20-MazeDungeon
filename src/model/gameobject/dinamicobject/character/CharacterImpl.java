package model.gameobject.dinamicobject.character;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashSet;
import java.util.Set;

import model.common.GameObjectType;
import model.common.Point2D;
import model.common.Vector2D;
import model.gameobject.GameObject;
import model.gameobject.dinamicobject.AbstractDinamicObject;
import model.gameobject.dinamicobject.DinamicObject;
import model.gameobject.dinamicobject.bullet.*;
import model.room.Room;
import model.room.RoomImpl;
import model.shop.Item;


public class CharacterImpl extends AbstractDinamicObject implements Character {

    private final double MAXLIFE = 4.0;
    private double life;
    private Set<Item> items; //contains set di items
    private BulletFactory bulletFactory;

    public CharacterImpl(final int id, final int speed, final Point2D position, final Vector2D direction, final GameObjectType gameObjectType, final Room room) {
        super(id, speed, position, direction, gameObjectType, room);
        this.life = MAXLIFE;
        this.items = new HashSet<>();
        this.bulletFactory = new BulletFactoryImpl();
    }


    @Override
    public void shoot() {
        //**// //ROOM MANAGER DEVE METTERE DENTRO LA STANZA IL BULLET CREATO DALLA FACTORY
    }

    @Override
    public void takeDamage(final int damage) {
        this.life = this.life - damage;
    }

    @Override
    public void updateState(final double elapsed) { 
        //this.move(elapsed);

    }

    /*@Override
    public void move(double elapsed) {
        super.setSpeed(8);
        super.setPosition(new Point2D(super.getPosition().getX() + super.getDirection().getX(),
                               super.getPosition().getY() + super.getDirection().getY()));
    }*/


    @Override
    public double getLife() {
        return this.life;
    }

    @Override
    public void setLife(final int life) {
        this.life = life;
    }

    @Override
    public Set<Item> getItems() {
        return this.items;
    }

    @Override
    public void addItem(Item item) {
        this.items.add(item);
    }


    @Override
    public void moveUp() {
        this.setPosition(this.getPosition().sum(new Vector2D(0, -10)));
        this.updateBoundingBoxPosition();
    }


    @Override
    public void moveDown() {
        this.setPosition(this.getPosition().sum(new Vector2D(0, 10)));
        this.updateBoundingBoxPosition();
    }


    @Override
    public void moveRight() {
        this.setPosition(this.getPosition().sum(new Vector2D(10, 0)));
        this.updateBoundingBoxPosition();

    }


    @Override
    public void moveLeft() {
        this.setPosition(this.getPosition().sum(new Vector2D(-10, 0)));
        this.updateBoundingBoxPosition();
    }


    @Override
    public void collideWith(GameObject obj2) {
        
    }



}
