package com.taraskina;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class Client2 {

    public static void main(String[] args) {
        Scanner scan=new Scanner(System.in);
        try(
                Socket socket = new Socket(InetAddress.getLocalHost(), Util.PORT);
                PrintStream printStream = new PrintStream(socket.getOutputStream());
                BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            boolean finish = false;
            while (!finish){
                printStream.println("Цитата:   ");
                System.out.println(reader.readLine());
                System.out.println("Press n for end");
                String s = scan.nextLine();
                if (s.equals("n"))
                    finish = true;
            }

        } catch (IOException e){
            e.printStackTrace();
        }

    }

}
