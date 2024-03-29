package test;

import static org.junit.Assert.assertEquals;

import model.common.BoundingBox;
import model.common.GameObjectType;
import model.common.Point2D;
import model.common.Vector2D;
import model.common.VectorDirection;
import model.gameobject.GameObject;
import model.gameobject.dynamicobject.bullet.Bullet;
import model.gameobject.dynamicobject.bullet.BulletFactoryImpl;
import model.gameobject.dynamicobject.bullet.BulletImpl;
import model.gameobject.dynamicobject.maincharacter.MainCharacter;
import model.gameobject.dynamicobject.maincharacter.MainCharacterImpl;
import model.room.Room;
import model.room.RoomBuilderImpl;
import model.room.RoomManagerImpl;
import model.shop.Item;
import model.shop.ItemBuilder;
import model.shop.Items;
import model.shop.Shop;
import model.shop.ShopImpl;

public class TestItem {
    private Item item;
    private MainCharacter c;
    private Room room;
    private int beforeMoney;
    private int beforeSpeed;
    private double beforeHealth;
    private static final double MORE_HEALTH = 20;
    private static final int MORE_DAMAGE = 5;
    private static final int MORE_SPEED = 40;
    private static final int MORE_BULLETSPEED = 40;
    private static final int PRICE_ARTHEMIDEBOW = 4;
    private static final int PRICE_HERMESBOOTS = 3;
    private static final int PRICE_HEALTH = 2;
    private static final int PRICE_ORACLEAMULET = 7;
    private Bullet defaultBullet;

    @org.junit.Before
    public void newCharacter() {
        final int timeSleep = 400;
        c = new MainCharacterImpl(new Point2D(100, 100), GameObjectType.MAINCHARACTER);
        room = new RoomBuilderImpl(new RoomManagerImpl()).build();
        room.addDynamicObject(c);
        c.setMoney(10);
        c.setLife(c.getLife() - c.getLife() / 2);
        this.beforeMoney = c.getMoney();
        this.defaultBullet = new BulletFactoryImpl().createMainCharacterBullet(new Point2D(0, 0), new Vector2D(0, 0), 0, 0);
        this.beforeSpeed = c.getSpeed();
        this.beforeHealth = c.getLife();
        try {
            Thread.sleep(timeSleep);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        c.setBoundingBox(new BoundingBox(new Point2D(0, 0), 0, 0));
        c.setShoot(true, VectorDirection.UP);
    }
    @org.junit.Test
    public void testArthemideBow() {
        item = new ItemBuilder.Builder(Items.ARTHEMIDEBOW, PRICE_ARTHEMIDEBOW).addDamage(MORE_DAMAGE).build();
        final Shop shop = new ShopImpl(c);
        shop.checkItem(Items.ARTHEMIDEBOW);
        c.update(1000);
        final Bullet bulletActual = searchBullet();
        assertEquals(c.getMoney(), this.beforeMoney - item.getCost());
        assertEquals(bulletActual.getDamage(), this.defaultBullet.getDamage() + item.getDamage());
        assertEquals(c.getSpeed(), this.beforeSpeed + item.getSpeed());
        assertEquals((int) c.getLife(), (int) (this.beforeHealth + item.getHealth()));
    }

    @org.junit.Test
    public void testHermesBoots() {
        item = new ItemBuilder.Builder(Items.HERMESBOOTS, PRICE_HERMESBOOTS).addSpeed(MORE_SPEED).build();
        final Shop shop = new ShopImpl(c);
        shop.checkItem(Items.HERMESBOOTS);
        c.update(1000);
        final Bullet bulletActual = searchBullet();
        assertEquals(c.getMoney(), this.beforeMoney - item.getCost());
        assertEquals(bulletActual.getDamage(), this.defaultBullet.getDamage() + item.getDamage());
        assertEquals(c.getSpeed(), this.beforeSpeed + item.getSpeed());
        assertEquals((int) c.getLife(), (int) (this.beforeHealth + item.getHealth()));
    }
    @org.junit.Test
    public void testHealth() {
        item = new ItemBuilder.Builder(Items.HEALTH, PRICE_HEALTH).addHelath(MORE_HEALTH).build();
        final Shop shop = new ShopImpl(c);
        shop.checkItem(Items.HEALTH);
        c.update(1000);
        final Bullet bulletActual = searchBullet();
        assertEquals(c.getMoney(), this.beforeMoney - item.getCost());
        assertEquals(bulletActual.getDamage(), this.defaultBullet.getDamage() + item.getDamage());
        assertEquals(c.getSpeed(), this.beforeSpeed + item.getSpeed());
        assertEquals((int) c.getLife(), (int) (this.beforeHealth + item.getHealth()));
    }
    @org.junit.Test
    public void testOracleAmulet() {
        item = new ItemBuilder.Builder(Items.ORACLEAMULET, PRICE_ORACLEAMULET).addDamage(MORE_DAMAGE).addSpeed(MORE_SPEED).addBulletSpeed(MORE_BULLETSPEED).build();
        final Shop shop = new ShopImpl(c);
        shop.checkItem(Items.ORACLEAMULET);
        c.update(1000);
        final Bullet bulletActual = searchBullet();
        assertEquals(c.getMoney(), this.beforeMoney - item.getCost());
        assertEquals(bulletActual.getDamage(), this.defaultBullet.getDamage() + item.getDamage());
        assertEquals(c.getSpeed(), this.beforeSpeed + item.getSpeed());
        assertEquals((int) c.getLife(), (int) (this.beforeHealth + item.getHealth()));
    }

    private Bullet searchBullet() {
        for (final GameObject go : room.getCurrentGameObjects()) {
            if (go.getClass().equals(BulletImpl.class)) {
                return (Bullet) go;
            }
        }
        return null;
    }

}
