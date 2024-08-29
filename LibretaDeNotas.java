import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class LibretaDeNotas {
    private HashMap<String, ArrayList<Double>> calificaciones;
    private final double notaAprobatoria = 60.0;

    public LibretaDeNotas() {
        this.calificaciones = new HashMap<>();
    }
    public void ingresarCalificaciones() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingresar cantidad de alummnos:");
        int cantidadAlumnos = scanner.nextInt();
        validarNumeroPositivo(cantidadAlumnos);

        System.out.print("Ingresar cantidad de notas por alumno: ");
        int cantidadNotas = scanner.nextInt();
        validarNumeroPositivo(cantidadNotas);

        for (int i = 0; i < cantidadAlumnos; i++) {
            System.out.print("Ingrese el nombre del alumno " + (i + 1) + ": ");
            String nombre = scanner.next();
            ArrayList<Double> notas = new ArrayList<>();

            for (int j = 0; j < cantidadNotas; j++) {
                System.out.print("Ingrese la nota " + (j + 1) + " de " + nombre + ": ");
                double nota = scanner.nextDouble();
                validarNota(nota);
                notas.add(nota);
            }
            calificaciones.put(nombre, notas);
        }
    }
    private void validarNumeroPositivo(int numero) {
        if (numero <= 0) {
            throw new IllegalArgumentException("El número debe ser positivo.");
        }
    }
    private void validarNota(double nota) {
        if (nota < 0 || nota > 100) {
            throw new IllegalArgumentException("La nota debe estar entre 0 y 100.");
        }
    }

    public void mostrarPromedioPorEstudiante() {
        for (Map.Entry<String, ArrayList<Double>> entrada : calificaciones.entrySet()) {
            String nombre = entrada.getKey();
            ArrayList<Double> notas = entrada.getValue();
            double promedio = calcularPromedio(notas);
            System.out.println("El promedio de " + nombre + " es: " + promedio);
        }
    }

    public void mostrarEstadoAprobacion() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el nombre del estudiante: ");
        String nombre = scanner.next();

        if (calificaciones.containsKey(nombre)) {
            System.out.print("Ingrese la nota a evaluar: ");
            double nota = scanner.nextDouble();
            validarNota(nota);

            if (nota >= notaAprobatoria) {
                System.out.println("La nota es aprobatoria.");
            } else {
                System.out.println("La nota es reprobatoria.");
            }
        } else {
            System.out.println("el estudiante no existe.");
        }
    }

    public void mostrarNotaSobreDebajoPromedioCurso() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("ingrese el nombre del estudiante: ");
        String nombre = scanner.next();

        if (calificaciones.containsKey(nombre)) {
            System.out.print("ingrese la nota a evaluar: ");
            double nota = scanner.nextDouble();
            validarNota(nota);

            double promedioCurso = calcularPromedioGeneral();
            if (nota >= promedioCurso) {
                System.out.println("la nota está por sobre el promedio del curso.");
            } else {
                System.out.println("la nota está por debajo del promedio del curso.");
            }
        } else {
            System.out.println("El estudiante no existe.");
        }
    }

    private double calcularPromedio(ArrayList<Double> notas) {
        double suma = 0;
        for (double nota : notas) {
            suma += nota;
        }
        return suma / notas.size();
    }

    private double calcularPromedioGeneral() {
        double sumaTotal = 0;
        int cantidadNotas = 0;
        for (ArrayList<Double> notas : calificaciones.values()) {
            for (double nota : notas) {
                sumaTotal += nota;
                cantidadNotas++;
            }
        }
        return sumaTotal / cantidadNotas;
    }

    public void mostrarMenu() {
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\nMenú:");
            System.out.println("1. promedio de notas de alumno");
            System.out.println("2. nota final (pasa o repite): ");
            System.out.println("3. media de la nota: ");
            System.out.println("0. Salir");
            System.out.print("sseleccione una opción: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    mostrarPromedioPorEstudiante();
                    break;
                case 2:
                    mostrarEstadoAprobacion();
                    break;
                case 3:
                    mostrarNotaSobreDebajoPromedioCurso();
                    break;
                case 0:
                    System.out.println("saliendo...");
                    break;
                default:
                    System.out.println("opción no válida.");
            }
        } while (opcion != 0);
    }
    public static void main(String[] args) {
        LibretaDeNotas libreta = new LibretaDeNotas();
        libreta.ingresarCalificaciones();
        libreta.mostrarMenu();
    }
}