/*
 * Name:        Lesson 11: Writing Classes (Enemy.java)
 * Description: The Enemy class from Lesson 10, rewritten from scratch with
 *              private attributes, getters, and setters. Used by
 *              WritingClassesDemo.java.
 * Created by:  Mr Kowalczewski
 * Last edited: 2026-10-06
 */
public class Enemy {

    // 1. attributes - always private
    private String name;
    private int health;
    private int damage;

    // 2. constructor
    public Enemy(String name, int health, int damage) {
        this.name = name;
        this.health = health;
        this.damage = damage;
    }
   
    // 3. Methods - getters for each attribute
    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public int getDamage() {
        return damage;
    }

    // do you always want a setter?  Programmer's choice!
    public void setName(String name) {
        this.name = name;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    // rather than setDamage(), we can have a method that
    // calculates the damage for us
    // this is NOT a setter, because it doesn't set an attribute directly
    public void takeDamage(int amount) {
        health = health - amount;
        if (health < 0) {
            health = 0;
        }
    }

    public int attack() {
        return damage;
    }

    public boolean isDefeated() {
        return health == 0;
    }

}
