package br.com.mpetech.converter;

import java.math.BigDecimal;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 *
 * @author Miguel Castro/Everton Coutinho
 */
@Component
public class StringToBigDecimal implements Converter<String, BigDecimal>{

    @Override
    public BigDecimal convert(String input) {
        
        input = input.trim();
        if (input.length() > 0) {
            input = input.replace(".", "").replace(",", ".");
            BigDecimal big = new BigDecimal(input);
            return big;
        }
        return null;
    }
    
}
