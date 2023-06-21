package controllers;

import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javax.xml.crypto.Data;
import java.io.*;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

public class Client implements Initializable {
    @FXML
    private JFXTextField txtmessage;
    @FXML
    private JFXTextArea txtArea;
    private Socket socket;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try{
            socket = new Socket("localhost", 3002);
        }catch (IOException e){
            e.printStackTrace();
        }
    new Thread(()->{
        while (!txtmessage.equals("end-chat")) {
            try {
                DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
                String reply = dataInputStream.readUTF();
                Platform.runLater(() -> {
                    txtArea.setText(txtArea.getText() + "\n" + reply);
                });
            }catch (IOException e){
                e.printStackTrace();
                break;
            }
        }
    }).start();

    }
    public void txtMessageOnAction(ActionEvent actionEvent) throws IOException {
        btnSendOnAction(actionEvent);
    }

    public void btnSendOnAction(ActionEvent actionEvent) throws IOException {
        String message= "Client : "+txtmessage.getText();
        DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
        dataOutputStream.writeUTF(message);

        txtArea.setText(txtArea.getText()+"\n"+"me : "+txtmessage.getText());
        txtmessage.setText(null);
    }


}
