/*import java.io.IOException;
import java.util.Scanner;

public class UserInterfaceView {
    private Controller controller = new Controller();

    public  void ranInterface() {//метод принимающий на вход пользователя из систем ин
        Scanner scanner = new Scanner(System.in);

        while (true){   //бесконечный цикл
            System.out.println("Введите название города:");
            String city = scanner.nextLine();

            System.out.println("Введите 1 для получения погоды на сегодня; 5 - для прогноза на пять дней; " +
                    "Для выхода введите 0");

            String command = scanner.nextLine();// сохранение того что ввел пользователь

            if(command.equals("0")) break;

            try {
                controller.getWeather(command, city);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
*/