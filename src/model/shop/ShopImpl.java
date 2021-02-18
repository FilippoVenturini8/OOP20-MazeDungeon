package model.shop;

import java.util.HashSet;
import java.util.Set;

public class ShopImpl {
    private int actualMoney;
    private int actualLife;
    private static int MAX_LIFE=7;
    private Set<Items> purchasedItems = new HashSet<>();

    private String messageOuput="";
    
    private String msgBought = "You bought this item!";
    private String msgNoMoney = "You don't have enough coins!";
    public String getMessageOuput() {
        return messageOuput;
    }
    /**
     * @return Item with features of ArthemideBow
     */

    public boolean buyItem(final Items i) {
        if(purchasedItems.contains(i)) {
            messageOuput="You already have this item";
            return false;
        }
        switch (i) {
            case ARTHEMIDEBOW:
                if (this.getArthemideBow().getCost() <= actualMoney) {
                    actualMoney -= this.getArthemideBow().getCost();
                    purchasedItems.add(i);
                    messageOuput = msgBought;
                    return true;
                }
                break;
            case HERMESBOOTS:
                if (this.getHermesBoots().getCost() <= actualMoney) {
                    actualMoney -= this.getHermesBoots().getCost();
                    purchasedItems.add(i);
                    messageOuput = msgBought;
                    return true;
                }
                break;
            case ZEUSBOLT:
                if (this.getZeusBolt().getCost() <= actualMoney) {
                    actualMoney -= this.getZeusBolt().getCost();
                    purchasedItems.add(i);
                    messageOuput = msgBought;
                    return true;
                }
                break;
            case HEALTH:
                if (this.getHealth().getCost() <= actualMoney) {
                    if (this.actualLife + this.getHealth().getHealth().get() > MAX_LIFE) {
                        messageOuput = "You have too much life!";
                        return false;
                    }
                    actualMoney -= this.getHealth().getCost();
                    messageOuput = msgBought;
                    return true;
                }
                break;
             default:
                 messageOuput = msgNoMoney;
                 return false;

        }
        return false;
    }
    public Item getArthemideBow() {
        return new Item.Builder("ArthemideBow", 2).addDemage(5).build();
    }

    /**
     * @return Item with features of HermesBoots
     */
    public Item getHermesBoots() {
        return new Item.Builder("HermesBoots", 1).addSpeed(3).build();
    }

    /**
     * @return Item with features of ZeusBolt
     */
    public Item getZeusBolt() {
        return new Item.Builder("ZeusBolt", 1).addSpeedAttack(4).build();
    }

    /**
     * @return Item with features more Health
     */
    public Item getHealth() {
        return new Item.Builder("Health", 1).addHelath(2).build();
    }


    
}