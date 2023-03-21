/*
, se deben definir los tipos de datos datoPolinomio y Polinomio, donde el primero almacena el valor y el término de dicho valor –es decir si es xn, xn-1,…, x0– y el segundo es la estructura de datos polinomio que está compuesto de dos campos el grado del polinomio y un puntero que apunta al termino mayor del polinomio,


Por su parte en el bloque de la interfaz se desarrollan inicialmente los eventos necesarios para ad- ministrar el polinomio, es decir, para poder cargar un elemento, modificarlo y obtener su valor.


Luego se definen las funciones específicas para realizar operaciones con polinomios, como sumar, multiplicar y mostrar su contenido.


Ahora quedará a cargo del alumno completar la funcionalidad del TDA polinomio, dado que solo se desarrollaron algunas funciones, agregándole la capacidad de eliminar términos, y de determinar si en un polinomio existe un término, para evitar tener que llamar a la función “obtener_valor” y luego consultar si el resultado es distinto de cero para determinar si el polinomio tiene ese término o no. Esta última debe ser una función booleana
 */
import java.util.Scanner;
public class Matematicas {
    public class datoPolinomio {
        int coeficiente;
        int exponente;

        public datoPolinomio(int coeficiente, int exponente) {
            this.coeficiente = coeficiente;
            this.exponente = exponente;
        }

        public int getCoeficiente() {
            return coeficiente;
        }

        public void setCoeficiente(int coeficiente) {
            this.coeficiente = coeficiente;
        }

        public int getExponente() {
            return exponente;
        }

        public void setExponente(int exponente) {
            this.exponente = exponente;
        }
    }

    public class Polinomio {
        int grado;
        datoPolinomio[] polinomio;

        public Polinomio(int grado) {
            this.grado = grado;
            polinomio = new datoPolinomio[grado];
        }

        public int getGrado() {
            return grado;
        }

        public void setGrado(int grado) {
            this.grado = grado;
        }

        public datoPolinomio[] getPolinomio() {
            return polinomio;
        }

        public void setPolinomio(datoPolinomio[] polinomio) {
            this.polinomio = polinomio;
        }
    }

    public class Interfaz {
        Matematicas matematicas = new Matematicas();
        datoPolinomio datoPolinomio = matematicas.new datoPolinomio(0, 0);
        Polinomio polinomio = matematicas.new Polinomio(0);

        Scanner sc = new Scanner(System.in);

        public void cargarPolinomio(Polinomio polinomio) {
            for (int i = 0; i < polinomio.getGrado(); i++) {
                System.out.println("Ingrese el coeficiente del termino " + (i + 1));
                int coeficiente = sc.nextInt();
                System.out.println("Ingrese el exponente del termino " + (i + 1));
                int exponente = sc.nextInt();
                polinomio.getPolinomio()[i] = new datoPolinomio(coeficiente, exponente);
            }
        }

        public void mostrarPolinomio(Polinomio polinomio) {
            for (int i = 0; i < polinomio.getGrado(); i++) {
                System.out.print(polinomio.getPolinomio()[i].getCoeficiente() + "x^" + polinomio.getPolinomio()[i].getExponente() + " + ");
            }
            System.out.println();
        }

        public void modificarPolinomio(Polinomio polinomio) {
            System.out.println("Ingrese el termino que desea modificar");
            int termino = sc.nextInt();
            System.out.println("Ingrese el nuevo coeficiente");
            int coeficiente = sc.nextInt();
            System.out.println("Ingrese el nuevo exponente");
            int exponente = sc.nextInt();
            polinomio.getPolinomio()[termino - 1].setCoeficiente(coeficiente);
            polinomio.getPolinomio()[termino - 1].setExponente(exponente);
        }

        public int obtenerValor(Polinomio polinomio, int x) {
            int valor = 0;
            for (int i = 0; i < polinomio.getGrado(); i++) {
                valor += polinomio.getPolinomio()[i].getCoeficiente() * Math.pow(x, polinomio.getPolinomio()[i].getExponente());
            }
            return valor;
        }

        public Polinomio sumarPolinomios(Polinomio polinomio1, Polinomio polinomio2) {
            Polinomio polinomio3 = new Polinomio(polinomio1.getGrado());
            for (int i = 0; i < polinomio1.getGrado(); i++) {
                polinomio3.getPolinomio()[i] = new datoPolinomio(polinomio1.getPolinomio()[i].getCoeficiente() + polinomio2.getPolinomio()[i].getCoeficiente(), polinomio1.getPolinomio()[i].getExponente());
            }
            return polinomio3;
        }

        public Polinomio multiplicarPolinomios(Polinomio polinomio1, Polinomio polinomio2) {
            Polinomio polinomio3 = new Polinomio(polinomio1.getGrado() * polinomio2.getGrado());
            for (int i = 0; i < polinomio1.getGrado(); i++) {
                for (int j = 0; j < polinomio2.getGrado(); j++) {
                    polinomio3.getPolinomio()[i + j] = new datoPolinomio(polinomio1.getPolinomio()[i].getCoeficiente() * polinomio2.getPolinomio()[j].getCoeficiente(), polinomio1.getPolinomio()[i].getExponente() + polinomio2.getPolinomio()[j].getExponente());
                }
            }
            return polinomio3;
        }

        public void menu() {
            System.out.println("Ingrese el grado del polinomio");
            int grado = sc.nextInt();
            Polinomio polinomio1 = new Polinomio(grado);
            Polinomio polinomio2 = new Polinomio(grado);
            cargarPolinomio(polinomio1);
            cargarPolinomio(polinomio2);
            System.out.println("Polinomio 1");
            mostrarPolinomio(polinomio1);
            System.out.println("Polinomio 2");
            mostrarPolinomio(polinomio2);
            System.out.println("Ingrese el valor de x");
            int x = sc.nextInt();
            System.out.println("El valor del polinomio 1 en x es " + obtenerValor(polinomio1, x));
            System.out.println("El valor del polinomio 2 en x es " + obtenerValor(polinomio2, x));
            Polinomio polinomio3 = sumarPolinomios(polinomio1, polinomio2);
            System.out.println("La suma de los polinomios es");
            mostrarPolinomio(polinomio3);
            Polinomio polinomio4 = multiplicarPolinomios(polinomio1, polinomio2);
            System.out.println("La multiplicacion de los polinomios es");
            mostrarPolinomio(polinomio4);
            System.out.println("Ingrese el termino que desea modificar");
            int termino = sc.nextInt();
            modificarPolinomio(polinomio1);
            System.out.println("Polinomio 1");
            mostrarPolinomio(polinomio1);

        }

    }

    public static void main(String[] args) {
        Matematicas.Interfaz interfaz = new Matematicas().new Interfaz();
        interfaz.menu();
    }
}
