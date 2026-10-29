package s03reto02poo;

import java.util.Scanner;

public class S03Reto02CreditosPOO {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        LectorConsola lector = new LectorConsola(scanner);
        EvaluadorCredito evaluador = new EvaluadorCredito();
        EstadisticasCreditos estadisticas = new EstadisticasCreditos();

        long opcion;
        do {
            System.out.println();
            System.out.println("=== Simulador de aprobación de créditos ===");
            System.out.println("1 = Nueva solicitud");
            System.out.println("0 = Finalizar");
            // Cualquier opción distinta de 0 o 1 se vuelve a pedir.
            opcion = lector.leerEnRango("Seleccione una opción", 0, 1);

            if (opcion == 1) {
                SolicitudCredito solicitud = lector.leerSolicitud();
                ResultadoEvaluacion resultado = evaluador.evaluar(solicitud);
                System.out.println(resultado.generarReporte());
                estadisticas.registrar(resultado.getClasificacion());
            }
        } while (opcion != 0);

        System.out.println();
        System.out.println(estadisticas.generarResumen());
        scanner.close();
    }
}
