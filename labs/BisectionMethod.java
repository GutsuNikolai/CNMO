package labs;
import static java.lang.Math.*;

public class BisectionMethod {
    // Функция, корень которой ищем
    public static double function(double x) {
        return exp(-0.5 * x * x) - pow(x, 3) + 0.2;
    }

    // Метод бисекции
    public static double bisection(double a, double b, double tol, int maxIterations) {
        if (function(a) * function(b) >= 0) {
            throw new IllegalArgumentException("На данном отрезке нет корня или их несколько.");
        }

        double c = a; // Средняя точка
        for (int i = 0; i < maxIterations; i++) {
            c = (a + b) / 2; // Делим отрезок пополам
            double f_c = function(c);

            // Проверяем точность
            if (Math.abs(f_c) < tol) {
                System.out.println("\nЧисло итераций: " + i); // Выводим число итераций
                return c;
            }

            // Определяем новый отрезок
            if (function(a) * f_c < 0) {
                b = c;
            } else {
                a = c;
            }
        }

        return c; // Возвращаем приближенное значение корня
    }

}

