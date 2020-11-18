package edu.eci.arep.mypaas;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class PortQueue {

    private Queue<String> ports = new LinkedList<>();
    public PortQueue() {
        for (int i= 8080; i<= 8099 ; i++){
            ports.add(Integer.toString(i));
        }
    }

    public Queue<String> getPorts() {
        return ports;
    }

    public void setPorts(Queue<String> ports) {
        this.ports = ports;
    }

}
