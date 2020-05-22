package com.soma.utilities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class CovidApiResult {

    private final BigInteger confirmedCovid19Cases;
    private final BigInteger deathsFromCovid19;
    private final BigInteger recoveredCasesFromCovid19;
    private final String lastReportedDate;
}
