package model.room;

import java.util.LinkedList;
import java.util.List;

import model.common.BoundingBox;
import model.common.GameObjectType;
import model.common.Point2D;
import model.gameobject.GameObject;
import model.gameobject.dinamicobject.DinamicObject;
import model.gameobject.simpleobject.SimpleObject;
import model.gameobject.simpleobject.SimpleObjectImpl;

public class RoomImpl implements Room {

    private final List<SimpleObject> simpleObjects = new LinkedList<>();
    private final List<DinamicObject> dinamicObjects = new LinkedList<>();
    private final RoomManager roomManager;

    public RoomImpl(final RoomManager roomManager) {
        this.roomManager = roomManager;
        //this.setWalls();
    }

    //TODO va fatto bene. cosi è solo temporaneo (solo per test)
    /*NOTA: I MURI POSSONO ESSERE CARICATI DIRETTAMENTE DALLA FACTORY CHE SI OCCUPA
     * DI CARICARE GLI OSTACOLI. OGNI STANZA INDIPENDENTEMENTE DALLA DISPOSIZIONE DEGLI 
     * OSTACOLI HA GLI STESSI MURI
     */
    private void setWalls() {
        final SimpleObject leftWall = new SimpleObjectImpl(101, new Point2D(240, 177), GameObjectType.INVISIBLE_OBJECT);
        leftWall.setBoundingBox(new BoundingBox(leftWall.getPosition(), 1, 456));
        this.addSimpleObject(leftWall);

        final SimpleObject rightWall = new SimpleObjectImpl(102, new Point2D(1025, 177), GameObjectType.INVISIBLE_OBJECT);
        rightWall.setBoundingBox(new BoundingBox(rightWall.getPosition(), 1, 456));
        this.addSimpleObject(rightWall);

        final SimpleObject topWall = new SimpleObjectImpl(103, new Point2D(240, 177), GameObjectType.INVISIBLE_OBJECT);
        topWall.setBoundingBox(new BoundingBox(topWall.getPosition(), 785, 1));
        this.addSimpleObject(topWall);

        final SimpleObject bottomWall = new SimpleObjectImpl(104, new Point2D(240, 633), GameObjectType.INVISIBLE_OBJECT);
        bottomWall.setBoundingBox(new BoundingBox(bottomWall.getPosition(), 785, 1));
        this.addSimpleObject(bottomWall);
    }

    /**
     * @param elapsed the time passed
     */
    @Override
    public void update(final double elapsed) {
        for (final DinamicObject obj : dinamicObjects) {
            obj.updateState(elapsed);
        }
        this.checkCollisions();
    }

    /**
     * 
     * @param obj
     */
    public void addDinamicObject(final DinamicObject obj) {
        obj.setRoom(this);
        dinamicObjects.add(obj);
    }

    /**
     * 
     * @param obj
     */
    public void addSimpleObject(final SimpleObject obj) {
        obj.setRoom(this);
        simpleObjects.add(obj);
    }

    /**
     * 
     * @return .
     */
    public List<GameObject> getCurrentGameObjects() {
        final List<GameObject> gameObjects = new LinkedList<>(simpleObjects);
        gameObjects.addAll(dinamicObjects);
        return gameObjects;
    }

    /**
     * 
     */
    @Override
    public void deleteGameObject(final GameObject gameObject) {
        simpleObjects.remove(gameObject);
        dinamicObjects.remove(gameObject);
    }

    private void checkCollisions() {
        for (final DinamicObject obj1 : this.dinamicObjects) {
            for (final GameObject obj2 : this.getCurrentGameObjects()) {
                if (obj1.getBoundingBox() == null || obj1.getBoundingBox() == null || obj1.equals(obj2)) {
                    continue;
                }
                if (obj1.getBoundingBox().intersectWith(obj2.getBoundingBox())) {
                    obj1.collideWith(obj2);
                }
            }
        }
    }


}
