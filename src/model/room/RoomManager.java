package model.room;

import model.common.Direction;
import model.common.IdIterator;
import model.gameobject.dynamicobject.character.Character;

public interface RoomManager {
    Room getCurrentRoom();

    void update(double elapsed);

    IdIterator getIdIterator();

    void changeRoom(Direction direction);

    Character getCharacter();

    int getVisitedRooms();

    int getTotalRooms();
}
