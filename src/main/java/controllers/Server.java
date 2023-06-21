package controllers;

import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.StageStyle;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

public class Server implements Initializable {
    @FXML
    private JFXTextField txtmessage;
    @FXML
    private JFXTextArea txtArea;
    private static Socket socket;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        new Thread(()->{
            ServerSocket serverSocket= null;
            try {
                serverSocket= new ServerSocket(3002);
                socket=serverSocket.accept();
            }catch (IOException e){
                e.printStackTrace();
            }

            new Thread(()->{
                while (!txtmessage.equals("end-chat")){
                    try {
                        DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
                        String reply = dataInputStream.readUTF();
                        Platform.runLater(()->{
                            txtArea.setText(txtArea.getText()+"\n"+reply);
                        });
                    }catch (IOException e){
                        e.printStackTrace();
                        break;
                    }
                }
            }).start();
        }).start();

    }

    public void txtMessageOnAction(ActionEvent actionEvent) throws IOException {
        btnSendOnAction(actionEvent);
    }

    public void btnSendOnAction(ActionEvent actionEvent) throws IOException {

       String message= "Server : "+txtmessage.getText();
       DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
       dataOutputStream.writeUTF(message);

       txtArea.setText(txtArea.getText()+ "\n"+"me : "+txtmessage.getText());
       txtmessage.setText(null);

    }


}
