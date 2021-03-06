package mvc;

import java.util.List;

import model.common.Point2D;
import model.gameobject.GameObject;
import model.room.RoomManager;
import model.shop.Shop;

public interface Model {
    Point2D getGameObjectPosition(int id);

    GameObject getGameObject(int id);

    List<GameObject> getActualGameObjects();

    void updateGameWorld(double elapsed);

    RoomManager getRoomManager();

    boolean isGameOver();

    Shop getShop();

    boolean isWon();
}
