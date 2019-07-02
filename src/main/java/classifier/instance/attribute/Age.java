package classifier.instance.attribute;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.math.RoundingMode;
import java.text.DecimalFormat;

@Getter
@EqualsAndHashCode
public class Age {
    private double denormalized;

    public Age() {
    }

    public Age(double age) {
        this.denormalized = age;
    }

    @JsonIgnore
    public double getNormalized() {
        double minAge = 0;
        double maxAge = 95;

        double amplitude = maxAge - minAge;
        double proportion = denormalized - minAge;
        double result = proportion / amplitude;

        DecimalFormat resultFormat = new DecimalFormat("##.##");
        resultFormat.setRoundingMode(RoundingMode.FLOOR);

        return Double.valueOf(resultFormat.format(result));
    }
}
