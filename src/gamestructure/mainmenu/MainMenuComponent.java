package gamestructure.mainmenu;

/**
 * An enumeration used for classify all the Main Menu's components, 
 * with a name and the corresponding path of their images in the resource directory.
 *
 */
public enum MainMenuComponent {
    /**
     * The button that start a new game. 
     */
    BTN_NEW_GAME(PathResources.IMAGE_PATH + "NewGame.png"),

    /**
     * The button that show the credits.
     */
    BTN_CREDITS(PathResources.IMAGE_PATH + "CreditsButton.png"),

    /**
     * The button for exit the game.
     */
    BTN_EXIT(PathResources.IMAGE_PATH + "Exit.png"),

    /**
     * The label where the credits are showed.
     */
    LBL_CREDITS(PathResources.IMAGE_PATH + "lblCredits.png"),

    /**
     * The label that contains the background image.
     */
    LBL_BACKGROUND(PathResources.IMAGE_PATH + "MainMenuBackground.png");

    private final String path;

    MainMenuComponent(final String path) {
        this.path = path;
    }

    private static class PathResources {
        private static final String IMAGE_PATH = "/images/MainMenu/";
    }

    /**
     * @return the path matched to the name of the component.
     */
    public String getPath() {
        return this.path;
    }
}
