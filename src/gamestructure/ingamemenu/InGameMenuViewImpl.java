package gamestructure.ingamemenu;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.border.LineBorder;

import gamestructure.ingamemenu.utilities.ImageLoader;
import gamestructure.ingamemenu.utilities.Images;
import gamestructure.ingamemenu.utilities.MenuShopComponents;
import model.shop.Items;
import viewutilities.Pair;
import viewutilities.ResizableRectangle;
import viewutilities.WindowUtilities;
/**
 * This class implement all the needed features for realize the GUI of the InGameMenu.
 * 
 * It provide to place all components of the shop and InGameMenu in the right way,
 * and make it resizable for the portability of the application, using different monitor.
 *
 */
public class InGameMenuViewImpl implements InGameMenuView  {
    private static final int SIZE_IMAGE_ITEM = 100;
    private final WindowUtilities windowUtilities = new WindowUtilities();

    private static final String FONT_ALGERIAN = "Algerian";
    private String priceArthemideBow;
    private String priceHermesBoots;
    private String priceZeusBolth;
    private String priceHealth;
    private String priceOracleAmulet;
    private final JFrame frame = new JFrame();

    private final ImageLoader  imageIconSources = new ImageLoader();
    private final JLayeredPane inGameMenuPanel = new JLayeredPane();
    private final JLayeredPane shopPanel = new JLayeredPane();
    private static final Color COLOR_BACKGROUND = new Color(11, 23, 30, 255);
    private boolean startMenu = true;
    private boolean startShop = true;

    private final InGameMenuController controller;
    private static final int WIDTHBTN = 300; 
    private static final int HEIGHTBTN = 90;
    private static final int POS_Y_BTN_ITEM = 180;
    private final ResizableRectangle rrBtnResume = new ResizableRectangle(WindowUtilities.NATIVE_WIDTH / 4, 340, WIDTHBTN, HEIGHTBTN);
    private final ResizableRectangle rrBtnExit = new ResizableRectangle(WindowUtilities.NATIVE_WIDTH / 4, 523, WIDTHBTN, HEIGHTBTN);
    private final ResizableRectangle rrBtnShop = new ResizableRectangle(WindowUtilities.NATIVE_WIDTH / 4, 433, WIDTHBTN, HEIGHTBTN);
    private final ResizableRectangle rrLblBackGroundMenu =  new ResizableRectangle(0, 0, 1280, 800);

    private final Map<MenuShopComponents, Pair<JComponent, ResizableRectangle>> componentMapMenu = new HashMap<>() { /**
         * 
         */
        private static final long serialVersionUID = 609112033710638154L;

    {
        put(MenuShopComponents.BTN_RESUME, new Pair<>(new JButton(), rrBtnResume));
        put(MenuShopComponents.BTN_EXIT, new Pair<>(new JButton(), rrBtnExit));
        put(MenuShopComponents.BTN_SHOP, new Pair<>(new JButton(), rrBtnShop));
        put(MenuShopComponents.LBL_BACKGROUND_MENU, new Pair<>(new JLabel(), rrLblBackGroundMenu));
    }};
    private static final int WIDTH_LABEL = 25;
    private static final int HEIGHT_LABEL = 25;
    private static final int POS_Y_LBL_PRICE = 310;

    private final ResizableRectangle rrBtnArthemideBow = new ResizableRectangle(45, POS_Y_BTN_ITEM, SIZE_IMAGE_ITEM, SIZE_IMAGE_ITEM);
    private final ResizableRectangle rrBtnHermesBoots = new ResizableRectangle(208, POS_Y_BTN_ITEM, SIZE_IMAGE_ITEM, SIZE_IMAGE_ITEM);
    private final ResizableRectangle rrBtnZeusBolt = new ResizableRectangle(350, POS_Y_BTN_ITEM, SIZE_IMAGE_ITEM, SIZE_IMAGE_ITEM);
    private final ResizableRectangle rrBtnHealth = new ResizableRectangle(510, POS_Y_BTN_ITEM, SIZE_IMAGE_ITEM, SIZE_IMAGE_ITEM);
    private final ResizableRectangle rrBtnOracleAmulet = new ResizableRectangle(680, POS_Y_BTN_ITEM, SIZE_IMAGE_ITEM, SIZE_IMAGE_ITEM);
    private final ResizableRectangle rrBtnBack = new ResizableRectangle(WindowUtilities.NATIVE_WIDTH / 4, 710, WIDTHBTN, HEIGHTBTN);
    private final ResizableRectangle rrLblBackGroundShop =  new ResizableRectangle(0, 0, 1280, 800);
    private final ResizableRectangle rrLblPriceAB =  new ResizableRectangle(90, POS_Y_LBL_PRICE, WIDTH_LABEL, HEIGHT_LABEL);
    private final ResizableRectangle rrLblPriceH = new ResizableRectangle(560, POS_Y_LBL_PRICE, WIDTH_LABEL, HEIGHT_LABEL);
    private final ResizableRectangle rrLblPriceHB = new ResizableRectangle(250, POS_Y_LBL_PRICE, WIDTH_LABEL, HEIGHT_LABEL);
    private final ResizableRectangle rrLblPriceOA =  new ResizableRectangle(710, POS_Y_LBL_PRICE, WIDTH_LABEL, HEIGHT_LABEL);
    private final ResizableRectangle rrLblPriceZB = new ResizableRectangle(390, POS_Y_LBL_PRICE, WIDTH_LABEL, HEIGHT_LABEL);
    private final ResizableRectangle rrLblMsg = new ResizableRectangle(150, 580, 420, 50);

    private final Map<MenuShopComponents, Pair<JComponent, ResizableRectangle>> componentMapShop = new HashMap<>() { /**
         * 
         */
        private static final long serialVersionUID = -8153964955947288127L;

    {
        put(MenuShopComponents.BTN_ARTHEMIDE_BOW, new Pair<>(new JButton(), rrBtnArthemideBow));
        put(MenuShopComponents.BTN_HERMES_BOOTS, new Pair<>(new JButton(), rrBtnHermesBoots));
        put(MenuShopComponents.BTN_ZEUS_BOLT, new Pair<>(new JButton(), rrBtnZeusBolt));
        put(MenuShopComponents.BTN_HEALTH, new Pair<>(new JButton(), rrBtnHealth));
        put(MenuShopComponents.BTN_ORACLE_AMULET, new Pair<>(new JButton(), rrBtnOracleAmulet));
        put(MenuShopComponents.BTN_BACK, new Pair<>(new JButton(), rrBtnBack));
        put(MenuShopComponents.LBL_BACKGROUND_SHOP, new Pair<>(new JLabel(), rrLblBackGroundShop));
        put(MenuShopComponents.LBL_PRICE_AB, new Pair<>(new JLabel(), rrLblPriceAB));
        put(MenuShopComponents.LBL_PRICE_H, new Pair<>(new JLabel(), rrLblPriceH));
        put(MenuShopComponents.LBL_PRICE_HB, new Pair<>(new JLabel(), rrLblPriceHB));
        put(MenuShopComponents.LBL_PRICE_OA, new Pair<>(new JLabel(), rrLblPriceOA));
        put(MenuShopComponents.LBL_PRICE_ZB, new Pair<>(new JLabel(), rrLblPriceZB));
        put(MenuShopComponents.LBL_MSG, new Pair<>(new JLabel(), rrLblMsg));
    }};

    /**
     * Instantiate a new InGameMenuView initializing it.
     * @param controller
     */
    public InGameMenuViewImpl(final InGameMenuController controller) {
        this.controller = controller;
        this.frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.frame.setResizable(false);
        this.frame.setTitle("MazeDungeon");
    }
    /**
     * {@inheritDoc}
     */
    public void showShop() {
        this.frame.remove(inGameMenuPanel);

        if (startShop) {
            this.componentMapShop.entrySet().stream().forEach(e -> {
                e.getValue().getY().mul(windowUtilities.getScreenRatio());
                e.getValue().getX().setBounds(e.getValue().getY());
                this.configureShopComponent(e);
                if (e.getValue().getX() instanceof JButton) {
                    this.configureButton((JButton) e.getValue().getX());
                    this.shopPanel.add(e.getValue().getX(), JLayeredPane.PALETTE_LAYER);
                } else if (e.getKey().equals(MenuShopComponents.LBL_BACKGROUND_SHOP)) {
                    this.shopPanel.add(e.getValue().getX(), JLayeredPane.DEFAULT_LAYER);
                } else {
                    this.shopPanel.add(e.getValue().getX(), JLayeredPane.PALETTE_LAYER);
                }
            });
            startShop = false;
        }
        this.frame.setContentPane(shopPanel);
        this.show();
    }
    /**
     * {@inheritDoc}
     */
    public void showInGameMenu() {
        this.frame.remove(shopPanel);
        this.frame.setContentPane(inGameMenuPanel);
        if (startMenu) {
            this.componentMapMenu.entrySet().stream().forEach(e -> {
                e.getValue().getY().mul(windowUtilities.getScreenRatio());
                e.getValue().getX().setBounds(e.getValue().getY());
                this.configureMenuComponent(e);
                if (e.getValue().getX() instanceof JButton) {
                    this.configureButton((JButton) e.getValue().getX());
                    this.inGameMenuPanel.add(e.getValue().getX(), JLayeredPane.PALETTE_LAYER);
                } else {
                    this.inGameMenuPanel.add(e.getValue().getX(), JLayeredPane.DEFAULT_LAYER);
                }

            });
            startMenu = false;
        }
        this.show();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void show() {
        this.frame.getContentPane().setPreferredSize(new Dimension((int) (WindowUtilities.NATIVE_WIDTH * WindowUtilities.WIDTH_RATIO * windowUtilities.getScreenRatio()),
                (int) (WindowUtilities.NATIVE_HEIGHT * WindowUtilities.HEIGHT_RATIO * windowUtilities.getScreenRatio())));
        this.frame.pack();
        this.frame.setLocation(windowUtilities.getScreen().width / 2 - this.frame.getSize().width / 2,
                windowUtilities.getScreen().height / 2 - this.frame.getSize().height / 2);
        this.frame.setVisible(true);
        nobodyBtnSelect();
        this.frame.setBackground(COLOR_BACKGROUND);
    }
    private void nobodyBtnSelect() {
        this.componentMapMenu.entrySet().stream().forEach(e -> {
            if (e.getValue().getX() instanceof JButton) {
                e.getValue().getX().setBackground(COLOR_BACKGROUND);
            }
        });
        this.componentMapShop.entrySet().stream().forEach(e -> {
            if (e.getValue().getX() instanceof JButton) {
                e.getValue().getX().setBackground(COLOR_BACKGROUND);
            }
        });
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void hide() {
        this.frame.setVisible(false);
    }

    private void configureButton(final JButton btn) {
        btn.setBackground(COLOR_BACKGROUND);
        btn.setBorder(new LineBorder(COLOR_BACKGROUND));
        btn.setFocusPainted(false);
        btn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(final MouseEvent e) {
                btn.setBackground(new Color(Color.TRANSLUCENT));
            }
            @Override
            public void mouseExited(final MouseEvent e) {
                btn.setBackground(COLOR_BACKGROUND);
            }
        });
    }
    /**
     * {@inheritDoc}
     *
     */
    public void returnMessage(final String messageOutput) {
        ((JLabel) componentMapShop.get(MenuShopComponents.LBL_MSG).getX()).setText(messageOutput);
        ((JLabel) componentMapShop.get(MenuShopComponents.LBL_MSG).getX()).setVisible(true);
    }
    /**
     * {@inheritDoc}
     */
    public void removeMessage() {
        ((JLabel) componentMapShop.get(MenuShopComponents.LBL_MSG).getX()).setText("");
    }
    /**
     * {@inheritDoc}
     *
     */
    public void setPriceItem(final Map<Items, Integer> map) {
        priceArthemideBow = map.get(Items.ARTHEMIDEBOW).toString();
        priceHermesBoots = map.get(Items.HERMESBOOTS).toString();
        priceZeusBolth = map.get(Items.ZEUSBOLT).toString();
        priceHealth = map.get(Items.HEALTH).toString();
        priceOracleAmulet = map.get(Items.ORACLEAMULET).toString();
    }
    private void configureMenuComponent(final Entry<MenuShopComponents, Pair<JComponent, ResizableRectangle>> entry) {
        switch (entry.getKey()) {
        case BTN_EXIT:
            final JButton btnExitMenu = (JButton) entry.getValue().getX();
            btnExitMenu.addActionListener(e -> this.controller.exit());
            btnExitMenu.setIcon(windowUtilities.resizeImage(imageIconSources.getImage(Images.BTNEXIT), entry.getValue().getY()));
            break;

        case BTN_RESUME:
            final JButton btnResume = (JButton) entry.getValue().getX();
            btnResume.addActionListener(e -> this.controller.resume());
            btnResume.setIcon(windowUtilities.resizeImage(imageIconSources.getImage(Images.BTNRESUME), entry.getValue().getY()));
            break;

        case BTN_SHOP:
            final JButton btnShop = (JButton) entry.getValue().getX();
            btnShop.addActionListener(e -> this.controller.openShop());
            btnShop.setIcon(windowUtilities.resizeImage(imageIconSources.getImage(Images.BTNSHOP), entry.getValue().getY()));
            break;

        case LBL_BACKGROUND_MENU:
            final JLabel lblBackGround = (JLabel) entry.getValue().getX();
            lblBackGround.setIcon(windowUtilities.resizeImage(imageIconSources.getImage(Images.BACKGROUNDMENU), entry.getValue().getY()));
            break;
        default:
            break;
        }
    }
    private void configureShopComponent(final Entry<MenuShopComponents, Pair<JComponent, ResizableRectangle>> entry) {
        final int sizeFont = 20;
        switch (entry.getKey()) {
        case BTN_ARTHEMIDE_BOW:
            final JButton btnArthemideBow = (JButton) entry.getValue().getX();
            btnArthemideBow.addActionListener(e -> this.controller.buyItem(Items.ARTHEMIDEBOW));
            btnArthemideBow.setIcon(windowUtilities.resizeImage(imageIconSources.getImage(Images.BTNARTHEMIDEBOW), entry.getValue().getY()));
            break;

        case BTN_BACK:
            final JButton btnReturn = (JButton) entry.getValue().getX();
            btnReturn.addActionListener(e -> this.controller.openInGameMenu());
            btnReturn.setIcon(windowUtilities.resizeImage(imageIconSources.getImage(Images.BTNRETURNMENU), entry.getValue().getY()));
            break;

        case BTN_HEALTH:
            final JButton btnHealth = (JButton) entry.getValue().getX();
            btnHealth.addActionListener(e -> this.controller.buyItem(Items.HEALTH));
            btnHealth.setIcon(windowUtilities.resizeImage(imageIconSources.getImage(Images.BTNHEALTH), entry.getValue().getY()));
            break;

        case BTN_HERMES_BOOTS:
            final JButton btnHermesBoots = (JButton) entry.getValue().getX();
            btnHermesBoots.addActionListener(e -> this.controller.buyItem(Items.HERMESBOOTS));
            btnHermesBoots.setIcon(windowUtilities.resizeImage(imageIconSources.getImage(Images.BTNHERMESBOOTS), entry.getValue().getY()));
            break;

        case BTN_ORACLE_AMULET:
            final JButton btnOracleAmulet = (JButton) entry.getValue().getX();
            btnOracleAmulet.addActionListener(e -> this.controller.buyItem(Items.ORACLEAMULET));
            btnOracleAmulet.setIcon(windowUtilities.resizeImage(imageIconSources.getImage(Images.BTNORACLEAMULET), entry.getValue().getY()));
            break;

        case BTN_ZEUS_BOLT:
            final JButton btnZeusBolt = (JButton) entry.getValue().getX();
            btnZeusBolt.addActionListener(e -> this.controller.buyItem(Items.ZEUSBOLT));
            btnZeusBolt.setIcon(windowUtilities.resizeImage(imageIconSources.getImage(Images.BTNZEUSBOLT), entry.getValue().getY()));
            break;

        case LBL_BACKGROUND_SHOP:
            final JLabel lblBackGroundShop;
            lblBackGroundShop = (JLabel) entry.getValue().getX();
            lblBackGroundShop.setIcon(windowUtilities.resizeImage(imageIconSources.getImage(Images.BACKGROUNDSHOP), entry.getValue().getY()));
            break;

        case LBL_PRICE_AB:
            final JLabel lblPriceArthemideBow = (JLabel) entry.getValue().getX();
            lblPriceArthemideBow.setFont(new Font(FONT_ALGERIAN, Font.ITALIC, (int) (sizeFont * windowUtilities.getScreenRatio())));
            lblPriceArthemideBow.setText(priceArthemideBow);
            break;

        case LBL_PRICE_H:
            final JLabel lblPriceHealth = (JLabel) entry.getValue().getX();
            lblPriceHealth.setFont(new Font(FONT_ALGERIAN, Font.ITALIC, (int) (sizeFont * windowUtilities.getScreenRatio())));
            lblPriceHealth.setText(priceHealth);
            break;

        case LBL_PRICE_HB:
            final JLabel lblPriceHermesBoots = (JLabel) entry.getValue().getX();
            lblPriceHermesBoots.setFont(new Font(FONT_ALGERIAN, Font.ITALIC, (int) (sizeFont * windowUtilities.getScreenRatio())));
            lblPriceHermesBoots.setText(priceHermesBoots);
            break;

        case LBL_PRICE_OA:
            final JLabel lblPriceOracleAmulet = (JLabel) entry.getValue().getX();
            lblPriceOracleAmulet.setFont(new Font(FONT_ALGERIAN, Font.ITALIC, (int) (sizeFont * windowUtilities.getScreenRatio())));
            lblPriceOracleAmulet.setText(priceOracleAmulet);
            break;

        case LBL_PRICE_ZB:
            final JLabel lblPriceZeusBolt = (JLabel) entry.getValue().getX();
            lblPriceZeusBolt.setFont(new Font(FONT_ALGERIAN, Font.ITALIC, (int) (sizeFont * windowUtilities.getScreenRatio())));
            lblPriceZeusBolt.setText(priceZeusBolth);
            break;
        case LBL_MSG:
            final JLabel lblMessage = (JLabel) entry.getValue().getX();
            lblMessage.setFont(new Font(FONT_ALGERIAN, Font.ITALIC, (int) (sizeFont * windowUtilities.getScreenRatio())));
            lblMessage.setText("");
            break;
        default:
            break;
        }
    }
}
