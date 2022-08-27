package model;

import static util.DateUtil.parseDate;

import java.util.Date;
import java.util.Objects;

public class PeriodOfTime {
    private static final String DATE_SEPARATOR = "-";
    private final Date dateFrom;
    private final Date dateTo;

    public PeriodOfTime(Date dateFrom) {
        this(dateFrom, null);
    }

    public PeriodOfTime(Date dateFrom, Date dateTo) {
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
    }

    public Date getDateFrom() {
        return dateFrom;
    }

    public Date getDateTo() {
        return dateTo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PeriodOfTime that = (PeriodOfTime) o;
        return Objects.equals(dateFrom, that.dateFrom)
                && Objects.equals(dateTo, that.dateTo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dateFrom, dateTo);
    }

    @Override
    public String toString() {
        return "PeriodOfTime{"
                + "dateFrom=" + dateFrom
                + ", dateTo=" + dateTo
                + '}';
    }

    public static PeriodOfTime parseFromString(String value) {
        Objects.requireNonNull(value);
        String[] strings = value.split(DATE_SEPARATOR);
        if (strings.length > 2) {
            throw new RuntimeException("Invalid date range value " + value);
        }
        return strings.length == 1 ? new PeriodOfTime(parseDate(strings[0]))
                : new PeriodOfTime(parseDate(strings[0]),
                parseDate(strings[1]));
    }

    public Boolean matches(Date date) {
        return !(date.before(dateFrom) || date.after(dateTo));
    }
}
