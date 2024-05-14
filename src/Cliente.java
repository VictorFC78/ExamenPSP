import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {
    private static final int PUERTO=6000;
    private static final String HOST="localhost";
    private DataInputStream flujoEntrada;
    private DataOutputStream flujoSalida;
    private Socket cliente;
    public Cliente(){
        try {
            cliente=new Socket(HOST,PUERTO);
            flujoEntrada=new DataInputStream(cliente.getInputStream());
            flujoSalida=new DataOutputStream(cliente.getOutputStream());
            //leemos la primera linea que envia el servidor
            String datosServidor=flujoEntrada.readUTF();
            System.out.println(datosServidor);
            String numero;
            do{
                numero=solicitarNumero();
                flujoSalida.writeUTF(numero);
                datosServidor=flujoEntrada.readUTF();
                System.out.println(datosServidor);
            }while(!datosServidor.contains("ENHORABUENA"));
            cliente.close();
        } catch (IOException e) {
            System.out.println("Se ha producido un error al intentar conexion con servidor");
        }
    }
    private String solicitarNumero(){
        Scanner sc=new Scanner(System.in);
        return sc.nextLine();
    }

    public static void main(String[] args) {
        Cliente cliente=new Cliente();
    }
}
