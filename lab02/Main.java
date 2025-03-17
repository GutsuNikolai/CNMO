import static java.lang.Math.*;

public class Main {
    public static void main(String[] args) {
        double a = 0.5; // Начало
        double b = 1.5; // Конец
        double tolerance = 0.000001; // Точность
        int maxIterations = 100; // Максимальное число итераций

        BisectionMethod bisectionMethod = new BisectionMethod();
        SecantMethod secantMethod = new SecantMethod();
        NewtonMethod newtonMethod = new NewtonMethod();
        SimpleIterationMethod simpleIterationMethod = new SimpleIterationMethod();

        double root = bisectionMethod.bisection(a, b, tolerance, maxIterations);
        System.out.println("Приближенный корень: " + root);

        root = secantMethod.secant(a, b, tolerance, maxIterations);
        System.out.println("Приближенный корень: " + root);

        root = newtonMethod.newton(a, tolerance, maxIterations);
        System.out.println("Приближенный корень: " + root);

        root = simpleIterationMethod.simpleIteration(a, tolerance, maxIterations);
        System.out.println("Приближенный корень: " + root);
    }
    public static class SimpleIterationMethod {


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
                System.out.println("Итерация " + (iteration + 1) + ": x = " + newX);
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


    public static class BisectionMethod {
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

                System.out.println("Итерация " + (i + 1) + ": c = " + c);

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

    public static class NewtonMethod {
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
                System.out.println("Итерация " + (i + 1) + ": x = " + x_new);

                if (Math.abs(x_new - x) < tol) { // Проверка точности
                    System.out.println("\nЧисло итераций: " + i);
                    return x_new;
                }

                x = x_new;
            }

            return x; // Возвращаем приближенное значение корня, если достигнуто максимальное число итераций
        }
    }
    public static class SecantMethod {
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
                System.out.println("Итерация " + (i + 1) + ": x2 = " + x2);
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

}
