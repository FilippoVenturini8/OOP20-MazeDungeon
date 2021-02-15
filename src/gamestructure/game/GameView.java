package gamestructure.game;

import mvc.View;

public interface GameView extends View {
    void render();

    void setController(GameController controller);
}