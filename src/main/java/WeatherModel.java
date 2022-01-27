import java.io.IOException;

public interface WeatherModel {  // что бы наследники реализовывали, добавляю метод гет ведер, готорый будет получать погоду с того или иного сервиса
    void getWeather(String selectedCity, Period period) throws IOException;// метод будет получать и адресовывать на интерфес ( с двумя параметрами : город и период
}
