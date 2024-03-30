/**
 * 
 * Por la presente declaramos que todo el trabajo 
 * presentado en esta actividad es de elaboración propia. 
 * Elaborado por Jaime Barraza y Alejandro Gonzalez para el curso de Fundamentos de Programación
 */



package teatromoro;
//import java.util.Locale;
import java.util.Scanner;

public class TeatroMoro {


    public static void main(String[] args) {

      
    // defino colores    
    String black="\033[30m"; 
    String red="\033[31m"; 
    String green="\033[32m"; 
    String yellow="\033[33m"; 
    String blue="\033[34m"; 
    String purple="\033[35m"; 
    String cyan="\033[36m"; 
    String white="\033[37m";
    String reset="\u001B[0m";   
        
    // Definicion de variables 
     var encontrado = false; 
     boolean tarifaTrue = false;
     int indicadorTarifa = 0;  
     int indicadorEntrada = 0;
     int comprarEntrada = 0;
     int edad = 1;
     double precioFinal = 0;
     
     // Abstraccion de representacion de datos
     // Declaro una matriz 4x3 de tipo {entrada, tarifaEstudiante, tarifaGeneral}
     String[] tipoEntrada = {"A", "B", "C", "D"};
     
     int[] contadorEntrada = {0,0,0,0};
     
     // declaro matriz 4x5
     int[][] ubicacionAsiento = {
         {0,0,0,0,0}, // [0,0][0,1][0,2][0,3][0,4]
         {0,0,0,0,0}, // [1,0][1,1][1,2][1,3][1,4]
         {0,0,0,0,0}, // [2,0][2,1][2,2][2,3][2,4]
         {0,0,0,0,0}, // [3,0][3,1][3,2][3,3][3,4]
     };
     
         // Variables de input de usuario desde teclado
     Scanner teclado = new Scanner(System.in);

    // Despliegue menu principal
        System.out.println(red+"*******************************");
        System.out.println(red+"********* TEATRO MORO *********");              
        System.out.println(red+"*******************************"); 
        System.out.println(cyan+"------ TICKETERA VIRTUAL ------"+reset); 
        
        

        
        do{
            encontrado = false;
        System.out.println("\nPresiona 1 si quieres [Comprar Entrada]");
        System.out.println("Presiona 2 si quieres [Salir]");
              
        comprarEntrada = teclado.nextInt();
            
        if (comprarEntrada==1){    
        // Plano del teatro usando bucle for
        System.out.println("\n"+cyan+"**** PLANO DEL TEATRO ****"+reset);
        for(int i = 0; i<4; i++){
            System.out.print("Zona " + tipoEntrada[i] + " ");
            for (int j=0; j<5; j++){
                System.out.print("[");
                if(ubicacionAsiento[i][j]==1){
                    System.out.print(red+ubicacionAsiento[i][j]+reset);
                }else{
                    System.out.print(ubicacionAsiento[i][j]);
                }
                System.out.print("]");
            }
            System.out.println("");
        }
        System.out.println("");
        System.out.println("[0] Disponible");
        System.out.println(red+"[1] No disponible"+reset);
        System.out.println();
        
           
            System.out.println("Ingrese su edad: ");
            edad = teclado.nextInt();teclado.nextLine();
          while(edad<0){
            System.out.println("Ingrese su edad: ");
            edad = teclado.nextInt();teclado.nextLine();
          }
            
            while(encontrado==false){
            System.out.println("Ingrese la ubicacion deseada (A,B,C,D): ");
            String ubicacionDeseada = teclado.nextLine();
            
            ubicacionDeseada = ubicacionDeseada.toUpperCase();
            
            for (int i=0; i<4; i++){
                if (tipoEntrada[i].equalsIgnoreCase(ubicacionDeseada)){
                    encontrado = true;
                    indicadorEntrada = i;
                    break;
                    }
            }
            
            if(contadorEntrada[indicadorEntrada]==5){
                System.out.println(red+"*Entradas en Zona "+tipoEntrada[indicadorEntrada]+" completas, escoja otra ubicacion"+reset);
                encontrado = false;
            }
            
        
            double precioEntrada = calcularPrecioEntrada(ubicacionDeseada, edad);
            
            precioFinal += precioEntrada;
            
            if(encontrado==true){
            System.out.println("\n"+red+"Resumen compra:");
            System.out.println(green+"[Ubicacion]: " + reset + ubicacionDeseada);
            System.out.println(green+"[Precio Base]: " + reset + calcularPrecioEntrada(ubicacionDeseada, 20));
            if(edad<18){
                System.out.println(green+"[Descuento]: 10%");
            } else if(edad>=65){
                System.out.println(green+"[Descuento]: 15%");
            } else{
            System.out.println(green+"[Descuento]: 0%");
            }
            System.out.println(green+"[Precio final]: " + reset + precioEntrada);
            System.out.println(blue+"[Precio Total a pagar]: " + reset + precioFinal);
        
            
         // Usa el asiento y lo registra en el array
         for(int i = 0; i<4; i++){
            for (int j=0; j<5; j++){
                if(tipoEntrada[i].equals(ubicacionDeseada) && ubicacionAsiento[i][j]!=1){
                ubicacionAsiento[i][j] = 1;
                contadorEntrada[i]++;
                break;
                }
                }
                }
            }
            
            }// fin while
            
            
        } // fin if-1
        
        // Visualizacion resumen de la compra
        
        }while(comprarEntrada==1);    

        
        
        System.out.println("\n"+red+"Gracias por preferirnos!");
        teclado.close();
    }
    
   
    /*
    private static double descuentoEntrada(int precioBase, int edad){
        double descuento = 0;
        
        if(edad < 18){
            descuento = precioBase * 0.1;
        } else if (edad >= 65) {
            descuento = precioBase * 0.15;
        }
        
        return descuento;
    }
    */
    
    
    private static double calcularPrecioEntrada(String ubicacion, int edad){
        double precioBase = 0;
        switch(ubicacion){
            case "A" -> precioBase = 35000.0;
            case "B" -> precioBase = 30000.0;
            case "C" -> precioBase = 25000.0;
            case "D" -> precioBase = 22000.0;
            }
       
        double descuento = 0;
        
        if(edad < 18){
            descuento = precioBase * 0.1;
        } else if (edad >= 65) {
            descuento = precioBase * 0.15;
        }
        
        
        return (precioBase - descuento);
    }
    
    
    
    
}
