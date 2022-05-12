package vp.seminarska.airplanemanagmentapp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Service;
import vp.seminarska.airplanemanagmentapp.web.GeocodingController;

import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class GeocodingService {
    @Autowired
    private GeocodingController geocodingController;

    public List<String> LatLngParse(String message) throws JSONException, SQLException, URISyntaxException {
        message = geocodingController.geocodeCity(message);
        JSONArray array = new JSONArray(message);
        int index = 0;
        for(int i = 0; i<array.length();i++)
        {
            if (array.getJSONObject(i).getString("lat") != null)
                index = i;
        }
        JSONObject object = array.getJSONObject(index);
        List<String> list= new ArrayList<>();
        list.add(object.getString("lat"));
        list.add(object.getString("lon"));
        list.add(object.getString("country"));
        list.add(object.getString("state"));
        return list;
    }
}
