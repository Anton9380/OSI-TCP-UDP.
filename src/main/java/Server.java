package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private static char lastChar;

    public static void main(String[] args) {
        try (ServerSocket server = new ServerSocket(org.example.ServerConfig.PORT);
        ) {
            System.out.println("Сервер запущен");
            String lastCity = "???";

            try (Socket client = server.accept();
                 PrintWriter writer = new PrintWriter(client.getOutputStream(), true);
                 BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream()))) {

                System.out.println("Подключен клиент" + client.getPort());
                while (true) {
                    final String name = reader.readLine();

                    writer.println("Называет город: " + name + ", последний город: " + lastCity);

                    String input = reader.readLine();
                    char charIn = input.charAt(0);


                    if (lastCity.equals("???") && !lastCity.equals(input)) {
                        writer.println("OK");
                        lastCity = input;
                        lastChar = input.charAt(input.length() - 1);

                    } else {
                        if (lastChar == charIn) {
                            writer.println("OK");
                            lastCity = input;
                            lastChar = input.charAt(input.length() - 1);


                        } else {
                            writer.println("NOT OK");
                        }

                    }

                }
            }
        } catch (IOException e) {
            System.out.println("Не могу стартовать сервер");
            e.printStackTrace();
        }
    }
}