package org.example;

import org.example.model.Action;

public class Startup {
    private ActionIStrategy strategy;

    public static void main(String[] args) {

    }
    public void setStrategy(String action) {
        switch (action){
            case "A":
                this.strategy = new add();
                break;
            case "B":
                this.strategy = new Restore();
                break;
//            case "C":
//                this.strategy = new divide();
//                break;
//            case "D":
//                this.strategy = new multyply();
//                break;
//            case "E":
//                this.strategy = new multyply();
//                break;
//            case "F":
//                this.strategy = new multyply();
//                break;
//            case "G":
//                this.strategy = new multyply();
//                break;
//            case "H":
//                this.strategy = new multyply();
//                break;
//            case "I":
//                this.strategy = new multyply();
//                break;
//            case "J":
//                this.strategy = new multyply();
//                break;
//            case "K":
//                this.strategy = new multyply();
//                break;
        }
    }
}