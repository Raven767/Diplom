package com.example.myapplication;

import java.util.ArrayList;
import java.util.List;

public class State {
    private String log;
    private String plase;
    private String comm;

    public State(String log, String plase, String comm){
        this.log=log;
        this.plase=plase;
        this.comm=comm;
    }

    public String getLog() {
        return this.log;
    }

    public String getPLACE() {
        return this.plase;
    }

    public String getcom() {
        return this.comm;
    }


    public static ArrayList<State> createContactsList(int numContacts, String[] myInts, String[] mydate, String[] inform) {
        ArrayList<State> State = new ArrayList<State>();
        for (int i = 1; i <= numContacts; i++) {
            State.add(new State(myInts[i-1],mydate[i-1],inform[i-1]));
        }
        return State;
    }
}
