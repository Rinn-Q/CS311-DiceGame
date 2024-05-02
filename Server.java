import java.io.*;
import java.net.*;
import java.util.*;

import Backend.menu;

public class Server {
    private static final int PORT = 4000;
    private static final int MAX_CLIENTS = 2;
    private static final List<ClientHandler> clients = new ArrayList<>();
    private static int connectedClients = 0;
    private static menu sharedMenu;
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Server started");
            sharedMenu = new menu();
            while (true) {
                Socket clientSocket = serverSocket.accept();
                if (connectedClients < MAX_CLIENTS) {
                    System.out.println("New client connected: " + clientSocket);
                    connectedClients++;
                    ClientHandler clientHandler = new ClientHandler(clientSocket, sharedMenu);
                    clients.add(clientHandler);
                    clientHandler.start();
                } else {
                    System.out.println("Connection limit reached. Rejecting client: " + clientSocket);
                    clientSocket.close();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static class ClientHandler extends Thread {
        private final Socket clientSocket;
        private final ObjectInputStream in;
        private final ObjectOutputStream out;

        public ClientHandler(Socket socket,menu mainMenu) throws IOException {
            this.clientSocket = socket;
            in = new ObjectInputStream(clientSocket.getInputStream());
            out = new ObjectOutputStream(clientSocket.getOutputStream());
            out.writeObject(mainMenu);
            mainMenu.game.printState();
            System.out.println("this is the start tester");
            out.flush();
        }
        @Override
        public void run() {
            try {
                while (true) {
                    menu receivedMenu = (menu) in.readObject();
                    Server.updateSharedMenu(receivedMenu);
                    Server.broadcastMenuUpdate(); // Broadcast the updated menu to all clients
                    receivedMenu.game.printState();
                }
            } catch (IOException | ClassNotFoundException | CloneNotSupportedException e) {
                System.out.println("Client disconnected: " + clientSocket);
            } finally {
                try {
                    clientSocket.close();
                    synchronized (Server.clients) {
                        Server.clients.remove(this);
                    }
                    Server.connectedClients--;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public static synchronized void updateSharedMenu(menu updatedMenu) throws CloneNotSupportedException {
        sharedMenu = (menu) updatedMenu.clone(); // Assuming menu has a copy method to update its state
    }

    public static void broadcastMenuUpdate() {
        for (Object clientOutObj : clients) {
            if (clientOutObj instanceof ObjectOutputStream) {
                try (ObjectOutputStream clientOut = (ObjectOutputStream) clientOutObj) {
                    try {
                        clientOut.writeObject(sharedMenu);
                        clientOut.flush();
                        System.out.println("this is the flushing tester");
                        sharedMenu.game.printState();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
