package model;

import java.util.Objects;

public class Question {
    private final Long id;
    private final Long categoryId;
    private final Long subCategoryId;

    public Question(Long id) {
        this(id, null, null);
    }

    public Question(Long id, Long categoryId) {
        this(id, categoryId, null);
    }

    public Question(Long id, Long categoryId, Long subCategoryId) {
        this.id = id;
        this.categoryId = categoryId;
        this.subCategoryId = subCategoryId;
    }

    public Long getId() {
        return id;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public Long getSubCategoryId() {
        return subCategoryId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Question question = (Question) o;
        return Objects.equals(id, question.id)
                && Objects.equals(categoryId, question.categoryId)
                && Objects.equals(subCategoryId, question.subCategoryId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, categoryId, subCategoryId);
    }

    @Override
    public String toString() {
        return "Question{"
                + "id=" + id
                + ", categoryId=" + categoryId
                + ", subCategoryId=" + subCategoryId
                + '}';
    }

    public static Question parseFromString(String value) {
        Objects.requireNonNull(value);
        if (value.equals("*")) {
            return new Question(null);
        }
        String[] strings = value.split("\\.");
        if (strings.length > 3) {
            throw new RuntimeException("Invalid data " + value);
        }
        if (strings.length == 3) {
            return new Question(Long.parseLong(strings[0]),
                    Long.parseLong(strings[1]), Long.parseLong(strings[2]));
        }
        return strings.length == 2 ? new Question(Long.parseLong(strings[0]),
                Long.parseLong(strings[1])) : new Question(Long.parseLong(strings[0]));
    }

    public Boolean matches(Question question) {
        return matchesQuestionTypeId(question.getId())
                && matchesCategoryId(question.getCategoryId())
                && matchesSubCategoryId(question.getSubCategoryId());
    }

    private Boolean matchesQuestionTypeId(Long id) {
        return this.id == null || id == null || this.id == id;
    }

    private Boolean matchesCategoryId(Long categoryId) {
        return this.categoryId == null || categoryId == null || this.categoryId == categoryId;
    }

    private Boolean matchesSubCategoryId(Long subCategoryId) {
        return this.subCategoryId == null || subCategoryId == null
                || this.subCategoryId == subCategoryId;
    }
}
