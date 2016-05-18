package codility.primeandcomposite;

/**
 * https://codility.com/programmers/task/min_perimeter_rectangle/
 */
public class MinPerimeterRectangle {

    public static void main(String[] args) {
        System.out.println(solution(30)); // --> 22
        System.out.println(solution(25)); // --> 20
        System.out.println(solution(1)); // --> 4
        System.out.println(solution(2)); // --> 6
        System.out.println(solution(3)); // --> 8
        System.out.println(solution(17)); // --> 36
    }

    public static int solution(int N) {
        int sqrt = (int) Math.sqrt(N);
        for (int i = sqrt; i > 1; i--) {
            if (N % i == 0) {
                return 2 * (i + N / i);
            }
        }

        return 2 * (1 + N);
    }
}