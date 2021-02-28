package model.shop;

public final class ItemBuilder implements Item {

    private final Items name;

    private int cost;

    private final int damage;

    private final int speed;

    private final int bulletSpeed;

    private final double health;

    private ItemBuilder(final Items name, final int cost, final int damage, final int speed, final int bulletSpeed, final double health) {
        super();
        this.name = name;
        this.setCost(cost);
        this.damage = damage;
        this.speed = speed;
        this.bulletSpeed = bulletSpeed;
        this.health = health;
    }

    @Override
    public int getCost() {
        return cost;
    }

    @Override
    public void setCost(final int cost) {
        this.cost = cost;
    }
    @Override
    public Items getName() {
        return this.name;
    }
    @Override
    public double getHealth() {
        return health;
    }
    @Override
    public int getSpeed() {
        return this.speed;
    }
    @Override
    public int getBulletSpeed() {
        return this.bulletSpeed;
    }
    @Override
    public int getDamage() {
        return this.damage;
    }

    public static class Builder {

        private final Items name;
        private final int cost;
        private final int speed;
        private int damage;
        private int bulletSpeed;
        private double health;

        public Builder(final Items name, final int cost) {
            this.name = name;
            this.cost = cost;
            this.damage = 0;
            this.speed = 0;
            this.bulletSpeed = 0;
            this.health = 0;
        }

        /**
         * @param health
         * @return this
         */
        public Builder addHelath(final double health) {
            this.health = health;
            return this;
        }
        /**
         * 
         * @param damage
         * @return .
         */
        public Builder addDamage(final int damage) {
            this.damage = damage;
            return this;
        }
        /**
         * 
         * @param speed
         * @return .
         */
        public Builder addSpeed(final int speed) {
            this.damage = speed;
            return this;
        }
        /**
         * 
         * @param speedAttack
         * @return .
         */
        public Builder addBulletSpeed(final int bulletSpeed) {
            this.bulletSpeed = bulletSpeed;
            return this;
        }
        /**
         * @return Item
         */
        public Item build() {
            return new ItemBuilder(this.name, this.cost, this.damage, this.speed, this.bulletSpeed, this.health);
        }
    }

}
