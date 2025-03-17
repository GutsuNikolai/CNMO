package labs;
import static java.lang.Math.*;

public class SecantMethod {
    // Функция, корень которой ищем
    public static double function(double x) {
        return exp(-0.5 * x * x) - pow(x, 3) + 0.2;
    }

    // Метод хорд (секущих)
    public static double secant(double x0, double x1, double tol, int maxIterations) {
        double x2;
        for (int i = 0; i < maxIterations; i++) {
            double f_x0 = function(x0);
            double f_x1 = function(x1);

            if (Math.abs(f_x1 - f_x0) < 1e-10) { // Защита от деления на 0
                throw new ArithmeticException("Деление на ноль в вычислениях.");
            }

            x2 = x1 - f_x1 * (x1 - x0) / (f_x1 - f_x0); // Формула метода хорд

            if (Math.abs(x2 - x1) < tol) { // Проверка точности
                System.out.println("\nЧисло итераций: " + i);
                return x2;
            }

            x0 = x1;
            x1 = x2;
        }

        return x1; // Приближенное значение корня, если превыено количество итераций
    }

}
