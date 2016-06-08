package server;

import creation.*;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.HashMap;

public class PortThread extends Thread {

    private ServerSocket serverSocket;
    private int port;
    private GameBuilder gameBuilder;
    private Game game;

    private HashMap<ClientThread, Integer> clientThreads = new HashMap<>();

    public PortThread(int port, GameBuilder gameBuilder) {
        this.port = port;
        this.gameBuilder = gameBuilder;
    }

    @Override
    public void run() {
        game = gameBuilder.build();
        createServerSocket();
        System.out.println(game.getName() + " is ready and waiting clients in port " + serverSocket.getLocalPort() + ".");

        while (!this.isInterrupted()) {
            try {
                Socket socket = serverSocket.accept();
                if (clientThreads.size() < game.getNumberOfPlayers()) {
                    System.out.println("Port " + serverSocket.getLocalPort() + " got a client: " + socket.getRemoteSocketAddress());
                    ClientThread clientThread = new ClientThread(socket, this);
                    addNewClientThread(clientThread);
                    clientThread.start();
                } else {
                    socket.close();
                    System.out.println("Port " + serverSocket.getLocalPort() + " rejected a client: limit of players reached.");
                }
            } catch (SocketException e) {
                System.out.println("Server socket " + serverSocket.getLocalPort() + " with game " + game.getName() + " has closed.");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void addNewClientThread(ClientThread clientThread) {
        int currentNumber = 0;
        boolean inserted = false;
        while (!inserted) {
            if (clientThreads.values().contains(currentNumber)) {
                currentNumber++;
            } else {
                clientThreads.put(clientThread, currentNumber);
                inserted = true;
            }
        }
    }

    private void createServerSocket() {
        try {
            this.serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            System.out.println("Unable to create new server socket!");
        }
    }

    public void newPlayerJoinedEvent(ClientThread clientThread) {
        clientThread.sendToClient("Welcome to game " + game.getName() + "! You are Player " + clientThreads.get(clientThread) + ".");
        notifyOtherClients("Player " + clientThreads.get(clientThread) + " joined!", clientThread);
    }

    private String getBasicAnswer(ClientThread clientThread, String cmd) {
        if (cmd.matches("^(?i)/help$")) {
            return game.getHelp();
        } else {
            return game.receiveCommands(clientThreads.get(clientThread) + ":" + cmd);
        }
    }

    public void playerSendCommandEvent(ClientThread clientThread, String cmd) {
        notifyOtherClients("Player " + clientThreads.get(clientThread) + " send this: " + cmd, clientThread);

        String answer = getBasicAnswer(clientThread, cmd);

        if (answer.equals(GameBuilderImp.winText)) {
            notifyOtherClients("Player " + clientThreads.get(clientThread) + " won! Game reset.", clientThread);
            resetGame();
            answer = answer + " Game reset.";
        }

        if (answer.equals(GameBuilderImp.loseText)) {
            notifyOtherClients("Player " + clientThreads.get(clientThread) + " lost the game!", clientThread);
            answer = answer + " Respawn.";
        }

        clientThread.sendToClient(answer);
    }

    public void playerLeftGameEvent(ClientThread clientThread) {
        notifyOtherClients("Player " + clientThreads.get(clientThread) + " escaped.", clientThread);

        clientThreads.remove(clientThread);
        if (clientThreads.size() == 0) {
            System.out.println("Last player abandoned " + serverSocket.getLocalPort() + ".");
            resetGame();
        }

        clientThread.interrupt();
    }

    public void resetGame() {
        game = gameBuilder.build();
        System.out.println(serverSocket.getLocalPort() + ": " + game.getName() + " reset.");
    }

    public void notifyOtherClients(String msg, ClientThread informer) {
        for (ClientThread clientThread : clientThreads.keySet()) {
            if (clientThread != informer) {
                clientThread.sendToClient(msg);
            }
        }
    }

    public void interrupt() {
        super.interrupt();

        clientThreads.keySet().forEach(ClientThread::interrupt);

        try {
            this.serverSocket.close();
        } catch (IOException e) {
            System.out.println("Unable to close server socket!");
        }
    }
}
