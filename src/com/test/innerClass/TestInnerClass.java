package com.test.innerClass;

/**
 * Created by zhouj on 2017/2/5.
 */
public class TestInnerClass{
    public static void main(String args[]){
        Goods good = new Goods();

        Contents content = good.dest();
        System.out.println(content.value());

        Destination destination = good.cont("BeiJing");
        System.out.println(destination.readLabel());
    }
}
interface Contents{
    int value();
}
interface Destination{
    String readLabel();
}
class Goods{
    private class Content implements Contents{
        private int i = 11;
        public int value(){
            return i;
        }
    }
    protected class GDestination implements Destination{
        private String label;
        private GDestination(String whereTo){
            label = whereTo;
        }
        public String readLabel(){
            return label;
        }
    }
    public Content dest(){
        return new Content();
    }
    public GDestination cont(String s){
        return new GDestination(s);
    }
}
