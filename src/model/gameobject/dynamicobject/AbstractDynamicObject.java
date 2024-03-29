package model.gameobject.dynamicobject;

import animations.State;
import model.common.BoundingBox;
import model.common.GameObjectType;
import model.common.Point2D;
import model.common.Vector2D;
import model.gameobject.simpleobject.AbstractSimpleObject;

/**
 * An abstract class that implements the DynamicObject as a SimpleObject,
 * with more features defined in the DynamicObject's interface.
 * 
 * It also implements the changing of the state of the DynamicObject,
 * making explicit if it's moving and in what direction.
 *
 */
public abstract class AbstractDynamicObject extends AbstractSimpleObject implements DynamicObject {

    private static final int BASE_HEIGHT = 40;
    private Vector2D direction;
    private int speed;
    private Point2D lastPosition;

    /**
     * Build a new DynamicObject using it's speed, position in the room and it'0s type.
     * 
     * @param speed : the initial speed of the DinamicObject.
     * @param position : the initial position of the DinamicObject.
     * @param gameObjectType : the type of the DinamicObject.
     */
    public AbstractDynamicObject(final int speed, final Point2D position, final GameObjectType gameObjectType) {
        super(position, gameObjectType);
        this.speed = speed;
        this.lastPosition = this.getPosition();
        this.direction = new Vector2D(0, 0);
    }

    /**
     * @return the speed of the DinamicObject
     */
    @Override
    public int getSpeed() {
        return this.speed;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setSpeed(final int speed) {
        this.speed = speed;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Vector2D getDirection() {
        return this.direction;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setDirection(final Vector2D newDirection) {
        this.changeState(newDirection);
        this.direction = newDirection;
    }

    private void changeState(final Vector2D newDirection) {
        if (newDirection.getX() > 0 && this.getGameObjectType().getStates().contains(State.MOVE_RIGHT)) {
            this.setState(State.MOVE_RIGHT);
            return;
        } else if (newDirection.getX() < 0 && this.getGameObjectType().getStates().contains(State.MOVE_LEFT)) {
            this.setState(State.MOVE_LEFT);
            return;
        } else if (newDirection.getY() < 0 && this.getGameObjectType().getStates().contains(State.MOVE_UP)) {
            this.setState(State.MOVE_UP);
            return;
        } else if (newDirection.getY() > 0 && this.getGameObjectType().getStates().contains(State.MOVE_DOWN)) {
            this.setState(State.MOVE_DOWN);
            return;
        }
        this.setState(State.IDLE);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setPosition(final Point2D newPosition) {
        this.lastPosition = this.getPosition();
        super.setPosition(newPosition);
        this.updateBoundingBoxPosition();
    }

    /**
     * Move the bounding box, following the DinamicObject movement.
     */
    private void updateBoundingBoxPosition() {
        if (this.getBoundingBox() != null) {
            this.getBoundingBox().move(this.getPosition());
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Point2D getLastPosition() {
        return this.lastPosition;
    }

    /**
     * @param elapsed : the time passed from the last movement.
     */
    protected void move(final double elapsed) {
        this.setPosition(this.getPosition().sum(this.getDirection().mul(speed).mul(elapsed)));
    }

    /**
     * @return a BoundingBox only for the base of the DynamicObject.
     */
    protected BoundingBox getBaseBoundingBox() {
        final Point2D footColliderUL = new Point2D(this.getBoundingBox().getULCorner().getX(), 
                                                   this.getBoundingBox().getBRCorner().getY() - BASE_HEIGHT);
        return new BoundingBox(footColliderUL, this.getBoundingBox().getWidth(), BASE_HEIGHT);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public abstract void update(double elapsed);
}
