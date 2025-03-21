
import java.util.Arrays;

public class Lab02 {
    public static void main(String[] args) {
        double[][] A = {
                {5.452, 0.401, 0.758, 0.123},
                {0.785, 2.654, 0.687, 0.203},
                {0.402, 0.244, 4.456, 0.552},
                {0.210, 0.514, 0.206, 4.568}
        };
        double[] B = {0.886, 0.356, 0.342, 0.452};
        double[] X = {0, 0, 0, 0}; // Начальное приближение

        int maxIterations = 100;
        double tolerance = 1e-6;

        System.out.println("Метод Якоби:");
        jacobi(A, B, X, maxIterations, tolerance);
        System.out.println("\nМетод Гаусса-Зейделя:");
        gaussSeidel(A, B, X, maxIterations, tolerance);
    }

    public static void jacobi(double[][] A, double[] B, double[] X, int maxIterations, double tolerance) {
        int n = A.length;
        double[] newX = new double[n];

        for (int iter = 0; iter < maxIterations; iter++) {
            System.arraycopy(X, 0, newX, 0, n);

            // Реализаия метода Якоби
            for (int i = 0; i < n; i++) {
                double sum = B[i];
                for (int j = 0; j < n; j++) {
                    if (i != j) {
                        sum -= A[i][j] * X[j];
                    }
                }
                newX[i] = sum / A[i][i];
            }
            
             System.out.println("Итерация " + (iter + 1) + ": " + Arrays.toString(newX));
            // Проверка на сходимость
            if (hasConverged(X, newX, tolerance)) {
                System.out.println("Решение найдено за " + (iter + 1) + " итераций: " + Arrays.toString(newX));
                return;
            }

            System.arraycopy(newX, 0, X, 0, n);
        }

        System.out.println("Метод не сошелся за " + maxIterations + " итераций.");
    }
    public static void gaussSeidel(double[][] A, double[] B, double[] X, int maxIterations, double tolerance) {
        int n = A.length;

        for (int iter = 0; iter < maxIterations; iter++) {
            double[] oldX = X.clone(); // Копируем старые значения для проверки сходимости

            for (int i = 0; i < n; i++) {
                double sum = B[i];

                for (int j = 0; j < n; j++) {
                    if (i != j) {
                        sum -= A[i][j] * X[j]; // Используем уже обновленные значения X[j]
                    }
                }

                X[i] = sum / A[i][i]; // Вычисляем новое значение X[i]
            }
            System.out.println("Итерация " + (iter + 1) + ": " + Arrays.toString(X));
            // Проверка на сходимость
            if (hasConverged(oldX, X, tolerance)) {
                System.out.println("Решение найдено за " + (iter + 1) + " итераций: " + Arrays.toString(X));
                return;
            }
        }

        System.out.println("Метод не сошелся за " + maxIterations + " итераций.");
    }


    // Проверка на сходимость. Проверяются значения на текущей и предыдущей итерации
    public static boolean hasConverged(double[] X, double[] newX, double tolerance) {
        for (int i = 0; i < X.length; i++) {
            if (Math.abs(newX[i] - X[i]) > tolerance) {
                return false;
            }
        }
        return true;
    }
}
