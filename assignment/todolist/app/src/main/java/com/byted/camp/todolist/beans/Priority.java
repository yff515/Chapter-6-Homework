package com.byted.camp.todolist.beans;

import android.graphics.Color;

public enum  Priority {
    Low(1,Color.WHITE),
    Medium(2,Color.GREEN),
    High(3,Color.YELLOW);

    public final int intValue;
    public final int color;

    Priority(int intValue,int color)
    {
        this.intValue = intValue;
        this.color = color;
    }

    public static Priority from(int intValue){
        for (Priority priority : Priority.values()){
            if(priority.intValue == intValue)
                return priority;
        }
        return Priority.Low;
    }

}
