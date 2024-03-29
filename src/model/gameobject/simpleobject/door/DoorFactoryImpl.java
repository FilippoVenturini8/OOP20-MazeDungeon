package model.gameobject.simpleobject.door;

import model.common.CardinalPoint;
import model.common.GameObjectType;
import model.common.Point2D;

/**
 * the DoorFactoryImpl is a DoorFactory that return simple doors.
 */
public class DoorFactoryImpl implements DoorFactory {

    private static final Point2D WEST_DOOR_POSITION = new Point2D(110, 360);
    private static final Point2D NORTH_DOOR_POSITION = new Point2D(590, 40);
    private static final Point2D EAST_DOOR_POSITION = new Point2D(1010, 360);
    private static final Point2D SOUTH_DOOR_POSITION = new Point2D(590, 615);

    private Door createWestDoor() {
        return new Door(WEST_DOOR_POSITION, GameObjectType.DOOR_WEST, CardinalPoint.WEST);
    }

    private Door createEastDoor() {
        return new Door(EAST_DOOR_POSITION, GameObjectType.DOOR_EAST, CardinalPoint.EAST);
    }

    private Door createNorthDoor() {
        return new Door(NORTH_DOOR_POSITION, GameObjectType.DOOR_NORTH, CardinalPoint.NORTH);
    }

    private Door createSouthDoor() {
        return new Door(SOUTH_DOOR_POSITION, GameObjectType.DOOR_SOUTH, CardinalPoint.SOUTH);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Door createDoor(final CardinalPoint cardinalPoint) {
        switch (cardinalPoint) {
        case NORTH:
            return this.createNorthDoor();
        case SOUTH:
            return this.createSouthDoor();
        case WEST:
            return this.createWestDoor();
        case EAST:
            return this.createEastDoor();
        default:
            throw new IllegalStateException("not valid cardinal point");
        }
    }

}
