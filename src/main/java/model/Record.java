package model;

import java.util.Arrays;
import java.util.Objects;

public class Record {
    private final RecordType recordType;
    private final Service service;
    private final Question question;
    private final ResponseType responseType;
    private final PeriodOfTime periodOfTime;
    private final Long time;

    public Record(RecordType recordType,
                  Service service,
                  Question question,
                  ResponseType responseType,
                  PeriodOfTime periodOfTime,
                  Long time) {
        this.recordType = recordType;
        this.service = service;
        this.question = question;
        this.responseType = responseType;
        this.periodOfTime = periodOfTime;
        this.time = time;
    }

    public RecordType getRecordType() {
        return recordType;
    }

    public Service getService() {
        return service;
    }

    public Question getQuestion() {
        return question;
    }

    public ResponseType getResponseType() {
        return responseType;
    }

    public PeriodOfTime getPeriodOfTime() {
        return periodOfTime;
    }

    public Long getTime() {
        return time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Record record = (Record) o;
        return recordType == record.recordType
                && Objects.equals(service, record.service)
                && Objects.equals(question, record.question)
                && responseType == record.responseType
                && Objects.equals(periodOfTime, record.periodOfTime)
                && Objects.equals(time, record.time);
    }

    @Override
    public int hashCode() {
        return Objects.hash(recordType, service, question, responseType, periodOfTime, time);
    }

    @Override
    public String toString() {
        return "Record{"
                + "recordType=" + recordType
                + ", service=" + service
                + ", question=" + question
                + ", responseType=" + responseType
                + ", periodOfTime=" + periodOfTime
                + ", time=" + time
                + '}';
    }

    public Boolean matches(Record record) {
        return this.getService().matches(record.getService())
                && this.getQuestion().matches(record.getQuestion())
                && this.getResponseType() == record.getResponseType()
                && this.getPeriodOfTime().matches(record.getPeriodOfTime().getDateFrom());
    }

    public enum RecordType {
        WAITING_TIME("C"),
        QUERY("D");

        private final String character;

        RecordType(String character) {
            this.character = character;
        }

        public String getCharacter() {
            return character;
        }

        public static RecordType getByName(String inputName) {
            return Arrays
                    .stream(Record.RecordType.values())
                    .filter(c -> c.getCharacter().equals(inputName))
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("Unknown character " + inputName));
        }
    }

    public enum ResponseType {
        FIRST_ANSWER("P"),
        NEXT_ANSWER("N");

        private final String character;

        ResponseType(String character) {
            this.character = character;
        }

        public String getCharacter() {
            return character;
        }

        public static ResponseType getByName(String inputName) {
            return Arrays
                    .stream(Record.ResponseType.values())
                    .filter(c -> c.getCharacter().equals(inputName))
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("Unknown character " + inputName));
        }
    }
}
