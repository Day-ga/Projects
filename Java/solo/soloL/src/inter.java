// /**
//  * inter
//  */
// public interface inter {
//     public void eat();
//     public void makeSound();
    
// }
    
// enum Colour{
//     RED,BLUE,GREEN;
// }
// class Loader extends Thread {
//     public void run() {
//       System.out.println("Hello");
//     }
//   }

class B implements Runnable {
    public void run() {
      System.out.println("B");	
    }
 }
 class A extends Thread {
    public void run() {
     System.out.println("A");
     Thread t = new Thread(new B());
     t.start();
    }
 public static void main(String[ ] args) {
    A object = new A();
    object.start();
    }
 }
  
//   class MyClass {
//     public static void main(String[ ] args) {
//       Loader obj = new Loader();
//       obj.start();
//     }
//   }