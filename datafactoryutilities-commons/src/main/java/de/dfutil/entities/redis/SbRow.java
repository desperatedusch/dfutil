package de.dfutil.entities.redis;

import de.dfutil.entities.format.RowType;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

@RedisHash
public class SbRow {


    private final static RowType rowType = RowType.SB;
    @jakarta.persistence.Id
    private String id;
    private String strDatum;
    @Indexed
    private String strAlOrt;
    private String strSchluessel;
    private String strNamenSchl;
    private String strBundLfdnr;
    private String strHnrVon;
    private String strHnrBis;
    private String strStatus;
    @Indexed
    private String strName46;
    @Indexed
    private String strName22;
    private String strReserve;
    private String strHnrTyp;
    private String strPlz;
    private String strCode;
    private String strOtlSchl;
    private String strAlorgB;
    private String strKgs;
    @Indexed
    private String strAlortNeu;
    private String strNamenSchlNeu;
    private String strBundLfdnrNNeu;
    private String strHnrvonNeu;
    private String strHnrbisNeu;

}
