package com.taraskina;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Random;


public class ServerThread  extends Thread{
    private BufferedReader reader;
    private PrintStream printStream;
    private java.net.InetAddress address;

    public ServerThread(Socket socket){
        try{
        reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        printStream = new PrintStream(socket.getOutputStream());
        }catch (IOException e){
            e.printStackTrace();
        }
        address = socket.getInetAddress();

    }
    @Override
    public void run() {
        Random random = new Random();
       
        try {
            while ((reader.readLine()) != null) {
                  int randomNumber = random.nextInt(10);
                  printStream.println(Util.QUOTES[randomNumber] );
                  System.out.println(Util.QUOTES[randomNumber]);
                  }


        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            disconnect();
            System.out.println(address.getHostName() + " disconnected at "+ java.time.LocalDateTime.now());

        }
    }
    private void disconnect(){
        if (printStream != null)
            printStream.close();
        try{
            if (reader != null){
                reader.close();
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
