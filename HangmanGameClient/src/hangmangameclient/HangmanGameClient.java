package hangmangameclient;

import hangmangameserver.ServiceInterface;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.concurrent.TimeUnit;


public class HangmanGameClient {
    
    private static Play play = new Play();
        
    public static void main(String[] args) throws InterruptedException {
       
        Registry reg;
        Remote proxy;
        ServiceInterface s;
        
        System.out.println("Connecting to the server..");
        
        try {
            reg = LocateRegistry.getRegistry("Localhost", 1234);
            proxy = reg.lookup("Hangman Game");
  
            System.out.println("Connection successful!");
            s = (ServiceInterface) proxy;
            
            TimeUnit.SECONDS.sleep(2);
            play.run(s);
            
        } catch (RemoteException | NotBoundException ex) {
            ex.printStackTrace();
        }
        
        
    }
}
