package find_your_house.config;

import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.support.DefaultConversionService;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CustomConversionService extends DefaultConversionService {
    public CustomConversionService() {
        super();
        addConverter(new StringToDateConverter());
    }
    public static class StringToDateConverter implements Converter<String, Date> {
        private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        @Override
        public Date convert(String source) {
            try {
                return dateFormat.parse(source);
            } catch (Exception e) {
                return null;
            }
        }
    }


}
