package model.gameobject.dinamicobject.enemy;

import model.common.GameObjectType;
import model.common.Point2D;
import model.common.Vector2D;
import model.gameobject.GameObject;
import model.gameobject.dinamicobject.AbstractDinamicObject;
import model.gameobject.dinamicobject.Coin;
import model.gameobject.dinamicobject.DinamicObject;
import model.gameobject.dinamicobject.bullet.BulletFactory;
import model.gameobject.dinamicobject.bullet.BulletFactoryImpl;
import model.room.Room;

public abstract class AbstractEnemy extends AbstractDinamicObject implements Enemy {

    private long lastShootTime = System.currentTimeMillis();
    private double life;
    private final BulletFactory bulletFactory;

    public AbstractEnemy(final int speed, final Point2D position, final Vector2D direction, final GameObjectType gameObjectType) {
        super(speed, position, direction, gameObjectType);
        this.bulletFactory = new BulletFactoryImpl();
    }

    /**
     * 
     */
    @Override
    public double getLife() {
        return this.life;
    }

    /**
     * @param life set the enemy life
     */
    public void setLife(final double life) {
        this.life = life;
        if (this.life <= 0) {
            this.getRoom().addDinamicObject(new Coin(0, this.getPosition(), new Vector2D(0, 0), GameObjectType.COIN));
            this.getRoom().deleteGameObject(this);
        }
    }

    /**
     * @return the factory of bullet
     */
    protected BulletFactory getBulletFactory() {
        return this.bulletFactory;
    }

    @Override
    public void takesDamage(final int damage) {
        //TODO
    }

    /**
     * 
     */
    @Override
    public void collideWith(final GameObject obj2) {
        switch (obj2.getGameObjectType().getCollisionType()) {
        case OBSTACLE:
            /*this.setPosition(new Point2D(this.getPosition().getX() - (this.getDirection().getX() * 1),
                    this.getPosition().getY()  - (this.getDirection().getY() * 1)));*/
            this.setPosition(this.getLastPosition());
            this.changeRoutine();
            break;
        case ENTITY:
            /*this.setPosition(new Point2D(this.getPosition().getX() - (this.getDirection().getX() * 1),
                    this.getPosition().getY()  - (this.getDirection().getY() * 1)));*/
            //this.setPosition(this.getLastPosition());
            final AbstractDinamicObject dinamicObject = (AbstractDinamicObject) obj2;
            dinamicObject.setPosition(dinamicObject.getLastPosition());
            this.changeRoutine();
            this.setLife(0);
            break;
        default:
            break;
        }
    }

    /**
     * @param shootFrequency the frequency of shoot
     * @return .
     */
    protected boolean canShoot(final long shootFrequency) {
        final long currentTime = System.currentTimeMillis();
        if (currentTime - this.lastShootTime > shootFrequency) {
            this.lastShootTime = currentTime;
            return true;
        }
        return false;
    }

    protected abstract void changeRoutine();

    @Override
    public abstract void shoot();

}
