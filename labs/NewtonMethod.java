package labs;
import static java.lang.Math.*;

public class NewtonMethod {
    // Функция, корень которой ищем
    public static double function(double x) {
        return exp(-0.5 * x * x) - pow(x, 3) + 0.2;
    }


    // Производная функции
    public static double derivative(double x) {
        return -x * exp(-0.5 * x * x) - 3 * x * x;
    }

    // Метод Ньютона
    public static double newton(double x0, double tol, int maxIterations) {
        double x = x0;

        for (int i = 0; i < maxIterations; i++) {
            double f_x = function(x);
            double df_x = derivative(x);

            if (Math.abs(df_x) < 1e-10) { // проверка деления на 0
                throw new ArithmeticException("Производная слишком близка к нулю, метод не сходится.");
            }

            double x_new = x - f_x / df_x; //  формула метода Ньютона

            if (Math.abs(x_new - x) < tol) { // Проверка точности
                System.out.println("\nЧисло итераций: " + i);
                return x_new;
            }

            x = x_new;
        }

        return x; // Возвращаем приближенное значение корня, если достигнуто максимальное число итераций
    }

}

