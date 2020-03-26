package cn.itcast.day11.demo06;

public class DemoMain {

    public static void main(String[] args) {
        // 创建一个英雄角色
        Hero hero = new Hero();
        // 为英雄起一个名字，并且设置年龄
        hero.setName("盖伦");
        hero.setAge(20);

        // 创建一个武器对象
        Weapon weapon = new Weapon("AK-47");
        // 为英雄配备武器
        hero.setWeapon(weapon);

        // 年龄为20的盖伦用多兰剑攻击敌方。
        hero.attack();
    }

}
