//package Spheron;



import spheron.Udpclient;

public class Spheron{

  static {
        System.loadLibrary("Spheron");
  }

//Native functions

public native void test();
public native void mconnect();
public native void row();
public native void rolltopos(int x, int y, int c_x, int c_y);
  
public static void main(String[] args) {
	
	Udpclient cl = new Udpclient(); 
    cl.setColor('b');
    cl.run();
	

	
      Spheron sp =new Spheron();
      sp.mconnect();
      sp.test();
      sp.row();
      
      
      while(true) {
    	    int x = (int)cl.getX();
    		int y=  (int)cl.getY();
          if(x != 200 && y != 200) {
      sp.rolltopos(200, 200,x,y);
          }
      //sp.rolltopos(200, 200,300,400);
   }
  }
}
