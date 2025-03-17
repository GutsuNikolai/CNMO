package labs;
import static java.lang.Math.*;

public class SimpleIterationMethod {


    public static double f(double x) {
        return exp(-0.5 * x * x) - pow(x, 3) + 0.2;
    }


    public static double g(double x) {
        return sqrt(-2 * log(f(x) - 0.2));
    }

    // Метод простой итерации
    public static double simpleIteration(double x0, double tolerance, int maxIterations) {
        double x = x0;
        int iteration = 0;

        while (iteration < maxIterations) {
            double newX = g(x); // Вычисляем новое значение
            if (abs(newX - x) < tolerance) {  // Проверка на точность
                System.out.println("\nЧисло итераций: " + iteration);
                return newX;  // Если разница между итерациями меньше погрешности, выходим
            }
            x = newX;  // Обновляем значение x для следующей итерации
            iteration++;
        }

        throw new ArithmeticException("Превышено максимальное количество итераций без сходимости.");
    }

}
