package weather.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Current {
    private Integer last_updated_epoch;
    private String last_updated;
    private Integer temp_c;
    private Double temp_f;
    private Integer is_day;
    private Condition condition;
    private Double wind_mph;
    private Double wind_kph;
    private Integer wind_degree;
    private String wind_dir;
    private Integer pressure_mb;
    private Double pressure_in;
    private Double precip_mm;
    private Double precip_in;
    private Integer humidity;
    private Integer cloud;
    private Double feelslike_c;
    private Double feelslike_f;
    private Integer vis_km;
    private Integer vis_miles;
    private Integer uv;
    private Integer gust_mph;
    private Double gust_kph;
}
