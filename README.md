# java_chat_application
 A chat application developed using Java and JavaFX that leverages socket technology

```
For this project, we designed a GUI interface using JavaFX to simulate a chat application. The way the application works is that a server will be run in which 2 clients at 
most will be able to connect to. The server is the intermediary entity that will be responsible for the exchange of messages between the 2 clients. The client will be an 
entity which will be able to send messages to and receive messages from the server after it connects to it. This functionality is made possible by leveraging java server 
sockets. The server itself will not be made into a GUI interface, rather its sole responsibility will be to handle the socket/networking logic and it will also output the 
messages that are being sent by the clients into the console. The client GUI was developed using SceneBuilder – it only contains 3 elements which are: listview, textfield, 
and a button. The listview will be the output window where the user will be able to see all the messages from itself and the 2nd client, the textfield is where the client 
will be able to input its message, and the button is the submit button which a user can click to send a message, or the user can press enter. Behind the scenes, the 
client will maintain a thread and data input stream for receiving messages from the server, and it will also maintain a data output stream in which it will send the 
message to the server. The server connection will be maintained by a socket.
```