package com.company.jmixpm.datatype;

import io.jmix.core.metamodel.annotation.DatatypeDef;
import io.jmix.core.metamodel.datatype.impl.StringDatatype;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.util.Locale;

@DatatypeDef(id = "string", javaClass = String.class, defaultForClass = true, value = "ext_StringDatatype")
public class ExtStringDatatype extends StringDatatype {

    private static final Logger log = LoggerFactory.getLogger(ExtStringDatatype.class);

    @Override
    public String format(Object value) {
//        log.info("ExtStringDatatype#format");

        return super.format(value);
    }

    @Override
    public String format(Object value, Locale locale) {
//        log.info("ExtStringDatatype#format");
        return super.format(value, locale);
    }

    @Override
    public String parse(String value) {
//        log.info("ExtStringDatatype#parse");
        return super.parse(value);
    }

    @Override
    public String parse(String value, Locale locale) throws ParseException {
//        log.info("ExtStringDatatype#parse");
        return super.parse(value, locale);
    }
}
