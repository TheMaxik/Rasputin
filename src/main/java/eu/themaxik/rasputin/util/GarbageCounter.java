package eu.themaxik.rasputin.util;

public class GarbageCounter {

    private static int i;
    private static int limit;
    public GarbageCounter(int limit){
        this.limit = limit;
        this.i = 0;
    }

    public static void increment(){
        i++;
        checkLimit();
    }

    private static void checkLimit(){
        if(i > limit){
            System.out.println("Taking trash out...");
            System.gc();
            System.out.println("Done!");
            i = 0;

        }
    }

}
