import java.io.File;
import java.util.ArrayList;
import java.util.Formatter;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.*;

public class App {
    public static void main(String[] args) {
        int f=1, i=2;
        while(++i<5)
           f*=i;
        System.out.println(f);
    }
}
//     System.out.println(div(1,0));
    // }
    // static int div(int a, int b) throws ArithmeticException {
    //         if(b == 0) {
    //           throw new ArithmeticException("Division by Zero");
    //         } else {
    //           return a / b;
    //         }
// try {
        //     int a[ ] = new int[2];
        //     System.out.println(a[5]);
        //   } catch (Exception e) {
        //     System.out.println("An error occurred");
        //   }
// public class App {
//     public static void main(String[] args) {
//         Machine m = new Machine(){
//             @Override public void start(){
//                 System.out.println("woo");
//             }
//         };
//         m.start();
//     }
// }
/**
 * App
 */
//    Rank a = Rank.SOLDIER;
    //     switch(a) {
    //         case SOLDIER:
    //           System.out.println("Soldier says hi!");
    //           break;
    //         case SERGEANT:
    //           System.out.println("Sergeant says Hello!");
    //         break;
    //         case CAPTAIN:
    //           System.out.println("Captain says Welcome!");
    //           break;
    //     }; 
// public static void main(String[] args) {
    //     ArrayList<String> colours = new ArrayList<String>();
    //     colours.add("Red");
    //     colours.add("Bule");
    //     colours.add("Green");
    //     colours.add("Orange");
    //     colours.remove("Green");
    
    //     System.out.println(colours.contains("Green"));
    //     System.out.println(colours.contains("Orange"));
    //     System.out.println(colours.get(0));
    //     System.out.println(colours.size());
 // LinkedList<String> c = new LinkedList<String>();
        // c.add("Red");
        // c.add("Blue");
        // c.add("Green");
        // c.add("Orange");
        // c.remove("Green");
        // for(String s: c){
        //     System.out.println(s);
        // }
// ArrayList<String> animals = new ArrayList<String>();
//        animals.add("tiger");
//        animals.add("cat");
//        animals.add("dog");
//        animals.add("snake");

//         Collections.sort(animals);
//         System.out.println(animals);
// animals.add("cat");
//        animals.add("dog");
//        animals.add("rabbit");

//        Iterator<String> it = animals.iterator();
//        while (it.hasNext()) {
//             String value = it.next();
//             System.out.println(value);
//        }
// try {
//             File x = new File("C://Users//NRK//Desktop//n" + 
//                       "otes.txt/");
//             Scanner sc = new Scanner(x);
//             while (sc.hasNext()) {
//                 System.out.println(sc.next());
//             }
//         } catch (FileNotFoundException e) {
//             System.out.println("Error");
//         }         