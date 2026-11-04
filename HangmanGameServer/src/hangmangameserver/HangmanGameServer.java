package hangmangameserver;

import java.rmi.AlreadyBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.concurrent.TimeUnit;

public class HangmanGameServer extends Game {

    
    public static void main(String[] args) {
        
        Registry reg;
        HangmanGameServer service;
        Remote proxy;
        
        System.out.println("Starting Server..");
        
        try {
            
            service = new HangmanGameServer();
            
            proxy  = UnicastRemoteObject.exportObject(service, 0);
            
            reg = LocateRegistry.createRegistry(1234);
            reg.bind("Hangman Game", proxy);
            
            TimeUnit.SECONDS.sleep(1);
            System.out.println("Server is Online!");
            
        } catch (RemoteException | AlreadyBoundException | InterruptedException ex) {
            ex.printStackTrace();
        }
    }  
}
