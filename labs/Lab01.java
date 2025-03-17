package labs;

public class Lab01 {
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
}
