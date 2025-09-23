package br.com.sarti.JavaSpring.exeception;

import java.util.Date;

public record ExeceptionResponse(Date timestamp, String message, String details) {
}
