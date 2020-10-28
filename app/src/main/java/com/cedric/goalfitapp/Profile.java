package com.cedric.goalfitapp;

public class Profile {
    private String firstName;
    private String lastName;
    private int age;
    private int Weight;
    private int height;
    private int WeightTarget;
    private int stepsTarget;

    public Profile()
    {
        // Empty Constructor
    }

    public Profile(String firstName, String lastName, int age, int weight, int height, int weightTarget, int stepsTarget) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        Weight = weight;
        this.height = height;
        WeightTarget = weightTarget;
        this.stepsTarget = stepsTarget;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getWeight() {
        return Weight;
    }

    public void setWeight(int weight) {
        Weight = weight;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeightTarget() {
        return WeightTarget;
    }

    public void setWeightTarget(int weightTarget) {
        WeightTarget = weightTarget;
    }

    public int getStepsTarget() {
        return stepsTarget;
    }

    public void setStepsTarget(int stepsTarget) {
        this.stepsTarget = stepsTarget;
    }

    public String ToString()
    {
        return "FirstName : "+firstName+"\n"+"LastName :"+"\n"+lastName+"\n"+"Age :"+age+"\n"+"Weight :"+"\n"+Weight+"\n"+"Height :"+height+"\n"+"Target Weight :"+"\n"+WeightTarget+"\n"+"Target Steps :"+stepsTarget;
    }
}
