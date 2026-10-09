/*
 * Name:        Lesson 11: Writing Classes (WritingClassesDemo.java)
 * Description: Uses the Enemy class written in Enemy.java: the constructor,
 *              getters, setters, and other methods, and what private
 *              attributes stop you from doing.
 * Created by:  Mr Kowalczewski
 * Last edited: 2026-10-06
 */

// The Enemy class is in a separate file, Enemy.java, in the same folder.
// This time we write it ourselves, so open it and follow along.

public class WritingClassesDemo {

    public static void main(String[] args) {

        // the constructor runs once for each new object
        Enemy goblin = new Enemy("Goblin", 30, 4);
        Enemy dragon = new Enemy("Dragon", 500, 60);

        // attributes are private now, so read them with getters
        System.out.println("goblin: " + goblin.getName() + ", health " + goblin.getHealth()
                + ", damage " + goblin.getDamage());
        System.out.println("dragon: " + dragon.getName() + ", health " + dragon.getHealth()
                + ", damage " + dragon.getDamage());

        // this no longer compiles: health has private access in Enemy
        // goblin.health = -9999;

        // setters change an attribute from outside the class
        goblin.setName("Goblin King");
        goblin.setDamage(goblin.getDamage() * 2);
        System.out.println("after setters: " + goblin.getName() + ", damage " + goblin.getDamage());

        // there is no setHealth, so health only changes through takeDamage
        goblin.takeDamage(25);
        System.out.println("after takeDamage(25): health " + goblin.getHealth()
                + ", defeated? " + goblin.isDefeated());

        goblin.takeDamage(25);
        System.out.println("after takeDamage(25): health " + goblin.getHealth()
                + ", defeated? " + goblin.isDefeated());

        // each object has its own copy of every attribute
        System.out.println("dragon health is still " + dragon.getHealth());

        // methods can use each other's results
        dragon.takeDamage(goblin.attack());
        System.out.println("goblin hits dragon for " + goblin.attack()
                + ", dragon health " + dragon.getHealth());
    }

}
