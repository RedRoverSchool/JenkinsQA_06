package school.redrover;

public class Exceptions {
    public static void main(String[] args) {
        System.out.println(devide(15,0));
    }

        private static int devide(int devided, int devisor) {
        try{
            return devided / devisor;
        } catch (ArithmeticException exception){
            System.out.println("Something goes wrong");
            exception.printStackTrace();
            return 0;
        } finally {
            System.out.println("Everything can do here after error happened");
        }

        }

    }
