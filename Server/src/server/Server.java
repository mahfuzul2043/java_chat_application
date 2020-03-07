package server;

import java.io.*;
import java.util.*;
import java.net.*;

public class Server {
    public static ArrayList<HandleUsers> userList = new ArrayList<HandleUsers>();
    private static int usersCounter = 0;

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(1000);
        Socket socket;

        while (usersCounter < 2) {
            socket = serverSocket.accept();

            DataInputStream input = new DataInputStream(socket.getInputStream());
            DataOutputStream output = new DataOutputStream(socket.getOutputStream());
            HandleUsers newUser = new HandleUsers(socket, "user " + usersCounter, input, output);

            Thread thread = new Thread(newUser);

            System.out.println("new user added");
            userList.add(newUser);
            thread.start();
            usersCounter++;
        }
    }
}

class HandleUsers implements Runnable {
    private Scanner messageInput = new Scanner(System.in);
    private String userName;
    private final DataInputStream input;
    private final DataOutputStream output;
    private Socket socket;
    private boolean status;

    // constructor 
    public HandleUsers(Socket socket, String userName, DataInputStream input, DataOutputStream output) {
        this.status = true;
        this.userName = userName;
        this.socket = socket;
        this.input = input;
        this.output = output;
    }

    @Override
    public void run() {

        String message;
        while (true) {
            try {
                message = input.readUTF();
                String sendTo = (this.userName.equals("user 0") ? "user 1" : "user 0");
                System.out.println(message + " - " + this.userName);

                if (message.equals("logout")) {
                    this.status = false;
                    this.socket.close();
                    break;
                }

                for (int i = 0; i < Server.userList.size(); i++) {
                    if (Server.userList.get(i).status && Server.userList.get(i).userName.equals(sendTo)) {
                        Server.userList.get(i).output.writeUTF(message);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {

            this.input.close();
            this.output.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
