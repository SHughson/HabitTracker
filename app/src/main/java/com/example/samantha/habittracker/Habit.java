package com.example.samantha.habittracker;

public class Habit {

    private String name;
    private int count;

    public Habit(String name, int count)
    {
        this.name = name;
        this.count = count;
        //if name is not in database - add it?
    }

    public String getName()
    {
        return this.name;
    }

    public int getTotalCount()
    {
        return this.count;
    }

    public int getDailyCount()
    {
        int dailycount = 0;
        //query database using current date
        return dailycount;
    }

    public void add()
    {
        this.count++;
        //update count in database
        //DynamoDbClient.update();
    }

    public void deleteLast()
    {
        this.count--;
        //update count in database
    }
}
