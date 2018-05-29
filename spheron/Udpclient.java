package spheron;

import java.io.*;
import java.net.*;
//import java.util.concurrent.Semaphore;


public class Udpclient extends Thread {

    DatagramSocket serverSocket = null;
    int port;
    byte[] receiveData;
    char color;
    short x;
    short y;
   // private final Semaphore mutex = new Semaphore(1, true);


    public Udpclient( ){
        try {
            serverSocket = new DatagramSocket(55000);
            receiveData = new byte[1028];
        }
        catch (IOException e){
            System.err.println("IOException " + e);
        }


    }
    public void setPort(int port){
        this.port = port;
    }
    public void run() {
        DatagramPacket incoming = new DatagramPacket(this.receiveData, this.receiveData.length);
        try {
            while (true) {
                this.serverSocket.receive(incoming);
                byte[] data = incoming.getData();
                String s = new String(data, 0, incoming.getLength());
                //System.out.print(s);
                parseMessage(s);

            }
        }
        catch (IOException e){
            System.err.println("IOException " + e);
        }
    }

    public void parseMessage(String msg){
        String youString = null;
        if ( !msg.isEmpty()) {
            switch (this.color) {
                case 'g':
                    youString = msg.substring(2, 11);
                    break;

                case 'b':
                    youString = msg.substring(14, 23);
                    break;

                case 'r':
                    youString = msg.substring(26, 35);
                    break;
            }
            //System.out.print(youString.substring(0, 4));
            if (!youString.substring(0, 4).equals("eeee")) {
                this.x = Short.valueOf(youString.substring(0, 4));
                this.y = Short.valueOf(youString.substring(5, 9));

            }
        }

    }
    public char getColor(){

        return this.color;
    }
    public void setColor(char color){
        this.color = color;
    }
    public short getX(){
        return this.x;
    }
    public short getY(){
        return this.y;
    }


}