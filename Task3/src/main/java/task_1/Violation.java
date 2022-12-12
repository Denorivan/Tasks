package task_1;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Violation implements Comparable<Violation> {
    @EqualsAndHashCode.Include
    private String type;
    private BigDecimal total;

    public Violation setType(String type) {
        this.type = type;
        return this;
    }

    public Violation setTotal(BigDecimal total) {
        this.total = total;
        return this;
    }

    @Override
    public int compareTo(Violation o) {
        return total.compareTo(o.getTotal());
    }
}
