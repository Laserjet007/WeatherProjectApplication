import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

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
        private static final String AUTOCOMPLETE = "autocomplete";//      найти похожие города

        private static final OkHttpClient okkHttpClient = new OkHttpClient();//    в виде констант обьявил http клиент обжектмапер
        private static final ObjectMapper objectMapper = new ObjectMapper();

        @Override
        public void getWeather(String selectedCity,Period period) throws IOException {//     главный реализуемый метод котой будет получать погоду
            String weatherResponse = null;
            switch (period) {//        период может быть двух вариантов поэтому делаю свич (оператор ветвления) который будет проверять период
                case NOW:   //реализую
                    HttpUrl httpUrl = new HttpUrl.Builder()      //собираю запрос
                            .scheme(PROTOKOL)
                            .host(BASE_HOST)
                            .addPathSegment(FORECASTS)
                            .addPathSegment(VERSION)
                            .addPathSegment(DAILY)
                            .addPathSegment(ONE_DAY)
                            .addPathSegment(detectCityKey(selectedCity))     //делаю вызов метода который будет определять айди города
                            .addQueryParameter(API_KAY_QUERY_PARAM, API_KEY)
                            .build();

                    Request request = new Request.Builder()
                            .url(httpUrl)
                            .build();

                    Response oneDayForecastResponse = okkHttpClient.newCall(request).execute();    // ответ
                    weatherResponse = oneDayForecastResponse.body().string();
                    System.out.println(weatherResponse);

                break;

                case FIVE_DAYS:

                    break;


            }

        }
    private String detectCityKey(String selectedCity) throws IOException {   //api запрос
        //http://dataservice.accuweather.com/lacations/v1/cities/autocomplete/
        HttpUrl httpUrl = new HttpUrl.Builder()      //собираю запрос
                .scheme(PROTOKOL)
                .host(BASE_HOST)
                .addPathSegment(LOCATIONS)
                .addPathSegment(VERSION)
                .addPathSegment(CITIES)
                .addPathSegment(AUTOCOMPLETE)
                .addQueryParameter(API_KAY_QUERY_PARAM, API_KEY)
                .addQueryParameter("q", selectedCity)
                .build();

        Request request = new Request.Builder()
                .url(httpUrl)
                .get()
                .addHeader("accept", "application/json")
                .build();

        Response response = okkHttpClient.newCall(request).execute();
        String responseCity = response.body().string();  // responseCity - большая джейсонка из маина

         String cityKey = objectMapper.readTree(responseCity).get(0).at("/Key").asText(); // из обжектмапера вызываю метод readTree который пройдется по методам. get(0) - взять первый элемент массиваю.at("/Key") - пкредать кей и сказать что это текст

        return cityKey;
    }

}

