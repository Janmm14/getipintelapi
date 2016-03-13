package me.minotopia.getipintelapi;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode
public class LookupResult {
    private String status; //"success" or "error"
    private int result;
    private String queryIP;
    private String queryflags;
    private String queryFormat;
    private String contact;

    private String message;
}
