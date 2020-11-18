package com.example.ISA2020.common;

import java.io.Serializable;
import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class TimeProvider implements Serializable {

    private static final long serialVersionUID = 60760671214376643L;

    public Date now() {
        return new Date();
    }

}
