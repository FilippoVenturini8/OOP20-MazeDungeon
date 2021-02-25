package model.gameobject.dinamicobject.character;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

import jaco.mp3.player.MP3Player;
import model.common.GameObjectType;
import model.common.Point2D;
import model.common.Vector2D;
import model.gameobject.GameObject;
import model.gameobject.dinamicobject.AbstractDinamicObject;
import model.gameobject.dinamicobject.bullet.*;
import model.room.Room;
import model.shop.Item;
import model.shop.Items;


public class CharacterImpl extends AbstractDinamicObject implements Character {

    /**
     * CONSTANTS.
     */
    private static final double MAX_LIFE = 20.0;
    private static final int INITIAL_SPEED = 200;
    private static final long INITIAL_SHOOT_SPEED = 3;
    private static final long INITIAL_BULLET_DELAY = 200;
    private static final int INITIAL_MONEY = 0;

    /*
     * VARIABLES.
     */
    private double life;
    private int damage;
    private long bulletSpeed;
    private int money;
    
    /**
     * 
     */
    private Set<Items> items;
    private final BulletFactory bulletFactory;
    private long lastShootTime; 
    private boolean shoot;
    private Vector2D shootDirection;

 
    public CharacterImpl(final Point2D position, final Vector2D direction, final GameObjectType gameObjectType) {
        super(INITIAL_SPEED, position, direction, gameObjectType);
        this.life = MAX_LIFE;
        this.money = INITIAL_MONEY;
        this.bulletSpeed = INITIAL_SHOOT_SPEED;
        this.items = new HashSet<>();
        this.bulletFactory = new BulletFactoryImpl();
        this.shoot = false;
        this.lastShootTime = System.currentTimeMillis();
    }


    /**
     * 
     */
    @Override
    public void updateState(final double elapsed) { 
        this.move(elapsed);
        if (this.shoot) {
            this.shoot();
        }
    }

    /**
     * 
     */
    @Override
    public void takesDamage(final double damage) {
        this.life = this.life - damage;
        System.out.println(this.getID() + ") " + this.getGameObjectType() + " Life: " + this.getLife());
        if (this.life < 0) {
            System.out.println("IL PERSONAGGIO PRINCIPALE E' MORTO");
        }
    }
    /**
     * @return the life
     */
    @Override
    public double getLife() {
        return this.life;
    }

    /**
     * set the current life
     */
    private void setLife(final double life) {
        this.life = this.life + life;
    }

    @Override
    public int getMoney() {
        return money;
    }

    @Override
    public void setMoney(int money) {
        this.money = money;
    }

    /**
     * @return the max life
     */
    @Override
    public double getMaxLife() {
        return MAX_LIFE;
    }
    /**
     * @return the items' set
     */
    @Override
    public Set<Items> getItems() {
        return this.items;
    }
    /**
     * 
     */
    @Override
    public void addItem(final Items item) {
        this.items.add(item);
        switch (item) {
            case ARTHEMIDEBOW:
            case HERMESBOOTS:
            case ZEUSBOLT:
            case HEALTH:
                /**TODO*/
          //  this.setSkills(item.getItem().get);
            default:
                break;
        }
    }
    
    private void setSkills() {
        // TODO Auto-generated method stub

    }


    /*METHODS FOR MOVEMENT*/

   


    /**
     * move up the character.
     */
    @Override
    public void moveUp() {
        this.setDirection(new Vector2D(this.getDirection().getX() , -1));
    }

    /**
     * move down the character..
     */
    @Override
    public void moveDown() {
        this.setDirection(new Vector2D(this.getDirection().getX() , 1));
    }

    /**
     * move right the character.
     */
    @Override
    public void moveRight() {
        this.setDirection(new Vector2D(1, this.getDirection().getY()));
    }

    /**
     * move left the character.
     */
    @Override
    public void moveLeft() {
        this.setDirection(new Vector2D(-1, this.getDirection().getY()));
    }

    /**
     * stops the character when moving vertically.
     */
    @Override
    public void stopVertical() {
        this.setDirection(new Vector2D(this.getDirection().getX() , 0));

    }
    /**
     * stops the character when moving horizontally.
     */
    @Override
    public void stopHorizontal() {
        this.setDirection(new Vector2D(0 , this.getDirection().getY()));
    }

    /**
     * TODO
     */
    @Override
    public void collideWith(final GameObject obj2) {
       /*final int footHeight = 15;
        final Point2D footColliderUL = new Point2D(this.getBoundingBox().getULCorner().getX(), this.getBoundingBox().getBRCorner().getY() - footHeight);
        final BoundingBox footCollider = new BoundingBox(footColliderUL, this.getBoundingBox().getWidth(), footHeight);
        if (footCollider.intersectWith(obj2.getBoundingBox())) {
            if (obj2.getGameObjectType().getCollisionType() == CollisionType.INTERACTIVE_ELEMENT && obj2.getGameObjectType() != GameObjectType.CHARACTER_BULLET ) { //COLLISIONE CON MONETE E BULLET
                switch (obj2.getGameObjectType()) {
                    case COIN:
                        System.out.println("COLLECT A COIN");
                        break;
                    default:
                        break;

                }
                this.getRoom().deleteGameObject(obj2);
            }
            this.setDirection(new Vector2D(0, 0));
            this.setPosition(this.getLastPosition());
        }*/

       switch (obj2.getGameObjectType().getCollisionType()) {
            case OBSTACLE:
                this.setPosition(this.getLastPosition());
                break;
            case ENTITY:
                /*this.setPosition(new Point2D(this.getPosition().getX() - (this.getDirection().getX() * 1),
                        this.getPosition().getY()  - (this.getDirection().getY() * 1)));*/
                //this.setPosition(this.getLastPosition());
                final AbstractDinamicObject dinamicObject = (AbstractDinamicObject) obj2;
                dinamicObject.setPosition(dinamicObject.getLastPosition());
                break;
            case INTERACTIVE_ELEMENT:
                if (obj2.getGameObjectType().equals(GameObjectType.COIN)) {
                    System.out.println("COLLECT A COIN");
                    this.money++;
                    System.out.println("CURRENT MONEY BADGE: " + this.money);
                    this.getRoom().deleteGameObject(obj2);
                }
                break;
            default:
                break;
        }
        /*this.setPosition(this.getLastPosition());*/
    }


    /*METHODS FOR SHOOTING*/
    /**
     * shoot a bullet.
     */
    @Override
    public void shoot() {
        Bullet bullet = bulletFactory.createCharacterBullet(
                new Point2D(getPosition().getX() + this.getBoundingBox().getWidth() / 2, getPosition().getY() + this.getBoundingBox().getHeight() / 2),
                this.shootDirection.mul(this.bulletSpeed)); 
        getRoom().addDinamicObject(bullet);
        /*final MP3Player mp3Player = new MP3Player(new File("resources/sounds/characterhoot.mp3"));
        mp3Player.play();*/
        this.shoot = false;
    }

    /**
     * 
     * @return if the character can shoot
     */
    private boolean canShoot() {
        final long currentTime = System.currentTimeMillis();
        if (currentTime - this.lastShootTime > this.INITIAL_BULLET_DELAY) {
            this.lastShootTime = currentTime;
            return true;
        }
        return false;
    }
    /*
     * set a shoot and his shoot direction.
     */
    @Override
    public void setShoot(final boolean shoot, final Vector2D shootDirection) {
        if (this.canShoot()) {
            this.shoot = shoot;
            this.shootDirection = shootDirection;
        }
    }
}

