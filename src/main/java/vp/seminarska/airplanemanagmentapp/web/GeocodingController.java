package vp.seminarska.airplanemanagmentapp.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.thymeleaf.util.StringUtils;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import javax.websocket.server.PathParam;
import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

@RestController
@CrossOrigin
public class GeocodingController {

    @Autowired
    DataSource dataSource;
    static String API_KEY;

    public GeocodingController() throws SQLException {
    }

    @PostConstruct
    private void getKey() throws SQLException {
        Statement statement = dataSource.getConnection().createStatement();
        String query = "select t.* from env_var t where t.name='geocoding'";
        ResultSet rset = statement.executeQuery(query);
        String key = null;

        while(rset.next()) {
            String name = rset.getString("name");
            key = rset.getString("key");
        }

        API_KEY = key;
    }
    @Autowired
    private RestTemplate restTemplate;

    private static final String url = "http://api.openweathermap.org/geo/1.0/direct?q=city_name&limit=5&appid=";

    //TODO: Parse response
    @GetMapping(value = "/api/{city}", produces = MediaType.APPLICATION_JSON_VALUE )
    @ResponseBody
    public String geocodeCity(@PathVariable("city") String city) throws URISyntaxException, SQLException {
        getKey();

        URI parsed_url = new URI(StringUtils.concat(url,API_KEY).replace("city_name",city));


        return restTemplate.getForObject(parsed_url, String.class);
    }
}
