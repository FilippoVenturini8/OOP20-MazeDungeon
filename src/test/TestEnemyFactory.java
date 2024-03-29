package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import model.common.BoundingBox;
import model.common.Point2D;
import model.gameobject.dynamicobject.enemy.Enemy;
import model.gameobject.dynamicobject.enemy.EnemyFactory;
import model.gameobject.dynamicobject.enemy.EnemyFactoryImpl;
import model.room.Room;
import model.room.RoomBuilderImpl;
import model.room.RoomManagerImpl;

/**
 * This test provide to verify the correct working of the factory and of the all types of enemy.
 *
 */
public class TestEnemyFactory {
    private static final int SOUL_LIFE = 50;
    private static final int SPROUT_LIFE = 50;
    private static final int SKELETON_LIFE = 75;

    private static final int SOUL_SPEED = 90;
    private static final int SPROUT_SPEED = 50;
    private static final int SKELETON_SPEED = 90;
    private EnemyFactory ef;

    @org.junit.Before
    public void initFactory() {
        ef = new EnemyFactoryImpl();
    }

    /**
     * This test verify the correct creation and working of the Sprout.
     */
    @org.junit.Test
    public void testSprout() {
        final Enemy sprout = ef.createSprout(new Point2D(100, 100));
        assertEquals(sprout.getPosition(), new Point2D(100, 100));
        assertEquals((int) sprout.getLife(), (int) SPROUT_LIFE);
        assertEquals(sprout.getSpeed(), SPROUT_SPEED);
        final Room room = new RoomBuilderImpl(new RoomManagerImpl()).build();
        sprout.setBoundingBox(new BoundingBox(new Point2D(100, 100), 100, 100));
        room.addDynamicObject(sprout);
        final int previousGameObjects = room.getCurrentGameObjects().size();
        sprout.tryToShoot();
        assertEquals(previousGameObjects + 1, room.getCurrentGameObjects().size());
        sprout.takesDamage(10);
        assertEquals((int) SPROUT_LIFE - 10, (int) sprout.getLife());
        sprout.takesDamage(SPROUT_LIFE);
        assertFalse(room.getCurrentGameObjects().contains(sprout));
    }

    /**
     * This test verify the correct creation and working of the Soul.
     */
    @org.junit.Test
    public void testSoul() {
        final Enemy soul = ef.createSoul(new Point2D(100, 100));
        assertEquals(soul.getPosition(), new Point2D(100, 100));
        assertEquals((int) soul.getLife(), (int) SOUL_LIFE);
        assertEquals(soul.getSpeed(), SOUL_SPEED);
        final Room room = new RoomBuilderImpl(new RoomManagerImpl()).build();
        soul.setBoundingBox(new BoundingBox(new Point2D(100, 100), 100, 100));
        room.addDynamicObject(soul);
        final int previousGameObjects = room.getCurrentGameObjects().size();
        soul.tryToShoot();
        assertEquals(previousGameObjects + 1, room.getCurrentGameObjects().size());
        soul.takesDamage(10);
        assertEquals((int) SOUL_LIFE - 10, (int) soul.getLife());
        soul.takesDamage(SOUL_LIFE);
        assertFalse(room.getCurrentGameObjects().contains(soul));
    }

    /**
     * This test verify the correct creation and working of the Skeleton.
     */
    @org.junit.Test
    public void testSkeleton() {
        final Enemy skeleton = ef.createSkeletonSeeker(new Point2D(100, 100));
        assertEquals(skeleton.getPosition(), new Point2D(100, 100));
        assertEquals((int) skeleton.getLife(), (int) SKELETON_LIFE);
        assertEquals(skeleton.getSpeed(), SKELETON_SPEED);
        final Room room = new RoomBuilderImpl(new RoomManagerImpl()).build();
        skeleton.setBoundingBox(new BoundingBox(new Point2D(100, 100), 100, 100));
        room.addDynamicObject(skeleton);
        final int previousGameObjects = room.getCurrentGameObjects().size();
        skeleton.tryToShoot();
        assertEquals(previousGameObjects + 4, room.getCurrentGameObjects().size());
        skeleton.takesDamage(10);
        assertEquals((int) SKELETON_LIFE - 10, (int) skeleton.getLife());
        skeleton.takesDamage(SKELETON_LIFE);
        assertFalse(room.getCurrentGameObjects().contains(skeleton));
    }

}
