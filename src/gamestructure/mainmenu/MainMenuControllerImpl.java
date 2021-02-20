package gamestructure.mainmenu;

import gamestructure.game.GameController;
import gamestructure.game.GameControllerImpl;
import gamestructure.game.GameView;
import gamestructure.game.GameViewImpl;
import model.ModelImpl;
import mvc.Model;

public class MainMenuControllerImpl implements MainMenuController {

    private final MainMenuView view;

    public MainMenuControllerImpl(final MainMenuView view) {
        this.view = view;
    }
    /**
     * 
     */
    @Override
    public void newGame() {
        final GameView view = new GameViewImpl();
        final Model model = new ModelImpl();
        final GameController controller = new GameControllerImpl(view, model);
        //view.setController(controller);
        controller.setup();
        controller.mainLoop();
        this.view.hide();
    }

}