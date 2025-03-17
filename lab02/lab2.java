package lab02;

import java.util.Arrays;

public class lab2 {
    public static void main(String[] args) {
        double[][] A = {
                {10, 2, -1},
                {-3, -10, 2},
                {5, 0, 1}
        };
        double[] B = {6, -6, 10};
        double[] X = {0, 0, 0}; // Начальное приближение

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
