package model;

import java.util.Objects;

public class Service {
    private final Long id;
    private final Long variationId;

    public Service(Long id) {
        this(id, null);
    }

    public Service(Long id, Long variationId) {
        this.id = id;
        this.variationId = variationId;
    }

    public Long getId() {
        return id;
    }

    public Long getVariationId() {
        return variationId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Service service = (Service) o;
        return Objects.equals(id, service.id)
                && Objects.equals(variationId, service.variationId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, variationId);
    }

    @Override
    public String toString() {
        return "Service{"
                + "id=" + id
                + ", variationId=" + variationId
                + '}';
    }

    public static Service parseFromString(String value) {
        Objects.requireNonNull(value);
        if (value.equals("*")) {
            return new Service(null);
        }
        String[] strings = value.split("\\.");
        if (strings.length > 2) {
            throw new RuntimeException("Invalid data " + value);
        }
        return strings.length == 2 ? new Service(Long.parseLong(strings[0]),
                Long.parseLong(strings[1])) : new Service(Long.parseLong(strings[0]));
    }

    public Boolean matches(Service service) {
        return matchesServiceId(service.getId()) && matchesVariationId(service.getVariationId());
    }

    private Boolean matchesServiceId(Long id) {
        return this.id == null || id == null || this.id == id;
    }

    private Boolean matchesVariationId(Long variationId) {
        return this.variationId == null || variationId == null || this.variationId == variationId;
    }
}
