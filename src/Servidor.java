import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
    private static final int PUERTO=6000;
    public Servidor(){
        try {
            ServerSocket servidor=new ServerSocket(PUERTO);
            while(true){
                System.out.println("Esperando cliente....");
                Socket cliente=servidor.accept();
                new HiloServidorCliente(cliente).start();
                System.out.println("Cliente conectado");
            }

        } catch (IOException e) {
            System.out.println("Se ha producido un error al iniciar el servidor");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Servidor servidor=new Servidor();
    }
}
