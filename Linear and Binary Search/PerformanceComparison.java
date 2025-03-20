public class PerformanceComparison {
    public static void main(String[] args) {
        long startTime, endTime;

        StringBuilder sb = new StringBuilder();
        startTime = System.nanoTime();
        for (int i = 0; i < 10000; i++) {
            sb.append("a");
        }
        endTime = System.nanoTime();
        System.out.println("StringBuilder Time: " + (endTime - startTime) + " ns");

        StringBuffer sbf = new StringBuffer();
        startTime = System.nanoTime();
        for (int i = 0; i < 10000; i++) {
            sbf.append("a");
        }
        endTime = System.nanoTime();
        System.out.println("StringBuffer Time: " + (endTime - startTime) + " ns");
    }
}
