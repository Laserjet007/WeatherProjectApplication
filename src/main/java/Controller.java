import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Controller {  // контроллер должен знать как подключиться к модели
   private WeatherModel weatherModel = new AccuweatherModel();
   private Map<Integer, Period> variants = new HashMap<>();

   public Controller() {         //в конструкторе инициализация вариантов
      variants.put(1, Period.NOW);       // для ввода пользователем чифр 1 или 5 для обозачения периода в один день и пять дней
      variants.put(5, Period.FIVE_DAYS);
   }

   public void getWeather(String userInput, String selectedCity) throws IOException {//метод передающий запрос от клиента (интерфейса) к модели
      Integer userIntegerInput = Integer.parseInt(userInput);//команда , которую запросил пользователь

      switch (variants.get(userIntegerInput)) {     //свич по вариантам
         case NOW:
            weatherModel.getWeather(selectedCity, Period.NOW);
            break;

         case FIVE_DAYS:
            throw new IOException("Этот метод еще не реализован");
      }
   }
}
