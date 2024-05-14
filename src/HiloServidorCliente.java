import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Random;

public class HiloServidorCliente extends Thread{
    private Socket cliente;
    public HiloServidorCliente(Socket cliente)  {
        this.cliente=cliente;
    }

    @Override
    public void run() {
       int aleatorio=numAleatorio();
        try {
            DataInputStream flujoCliente=new DataInputStream(cliente.getInputStream());
            DataOutputStream flujoServidor=new DataOutputStream(cliente.getOutputStream());
            //enviamos al cliente que tiene hacer
            flujoServidor.writeUTF("Debe adivinar un numero entero entre el 0 y el 100:");
            //leemos el fjujo del cliente y respondemos hasta que acierte
            String respuestaCliente;
            int aleatorioCliente=-1;
            do{
                respuestaCliente=flujoCliente.readUTF();
                try{
                    aleatorioCliente=Integer.parseInt(respuestaCliente);
                    if(aleatorioCliente>aleatorio){
                        flujoServidor.writeUTF("El numero buscado es menor, inserte numero de nuevo:");
                    }else if(aleatorioCliente<aleatorio){
                        flujoServidor.writeUTF("El numero buscado es mayor, inserte numero de nuevo:");
                    }else{
                        flujoServidor.writeUTF("ENHORABUENA HAS ACERTADO \n el numero buscado era "+aleatorio);
                    }
                }catch(NumberFormatException e){
                    flujoServidor.writeUTF("Solo se permiten numeros enteros del 0 al 100,inserte numero de nuevo:");
                }
            }while(aleatorio!=aleatorioCliente);
        } catch (IOException e) {
            System.out.println("Error al abrir conexion entre cliente y servidor");
        }
    }
    private int numAleatorio(){
        Random rd=new Random();
        return rd.nextInt(100)+1;
    }
}