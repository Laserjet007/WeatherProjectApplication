public class AccuweatherModel implements WeatherModel {   //наследую WeatherModel
        //для того что бы получить погоду подключаюсь к серверу Weather (чтобы не передавать запросы строчками - делаю переменные константы к которов в дальнейшем буду обращаться)
    //http://dataservice.accuweather.com/forecasts/v1/daily/1day/  -адрес от куда беру погоду

        private static final String PROTOKOL = "HTTPS";
        private static final String BASE_HOST = "dataservice.accuweather.com";
        private static final String FORECASTS = "forecasts";
        private static final String VERSION = "v1";
        private static final String DAILY = "daily";
        private static final String ONE_DAY = "1day";
        private static final String API_KEY = "QNdYlQNp9YPUd0PDuj5POfyfuUR9xwFS";
        private static final String API_KAY_QUERY_PARAM = "apikey";
        private static final String LOCATIONS = "locations";
        private static final String CITIES = "cities";
        private static final String AUTOCOMPLETE = "autocomplete";

    public void getWeather(String selectedCity, Period period) {// главный реализуемый метод котой будет получать погоду
    }

}
