package ufpel.trabfinalpoo.helperClasses;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

public class Messenger {
    private static Messenger instance;
    public Queue<Object> queue;

    private Messenger() {
        queue = new LinkedList<Object>();
    }

    public static Messenger getInstance() {
        if(instance == null)
            instance = new Messenger();

        return instance;
    }


}
