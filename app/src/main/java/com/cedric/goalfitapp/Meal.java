package com.cedric.goalfitapp;

public class Meal {
    int calories;
    int sugar;
    int fat;

    public Meal(){
        // Empty constructor
    }
    public Meal(int calories, int sugar, int fat) {
        this.calories = calories;
        this.sugar = sugar;
        this.fat = fat;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public int getSugar() {
        return sugar;
    }

    public void setSugar(int sugar) {
        this.sugar = sugar;
    }

    public int getFat() {
        return fat;
    }

    public void setFat(int fat) {
        this.fat = fat;
    }
}
