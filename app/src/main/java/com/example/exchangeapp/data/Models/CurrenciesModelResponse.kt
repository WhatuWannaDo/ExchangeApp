package com.example.exchangeapp.data.Models

data class CurrenciesModelResponse(
    val base: String,
    val date: String,
    val rates: Rates,
    val success: Boolean,
    val timestamp: Int
)
data class Rates(
    val AED: Double,
    val AFN: Double,
    val ALL: Double,
    val AMD: Double,
    val ANG: Double,
    val AOA: Double,
    val ARS: Double,
    val AUD: Double,
    val AWG: Double,
    val AZN: Double,
    val BAM: Double,
    val BBD: Double,
    val BDT: Double,
    val BGN: Double,
    val BHD: Double,
    val BIF: Double,
    val BMD: Double,
    val BND: Double,
    val BOB: Double,
    val BRL: Double,
    val BSD: Double,
    val BTC: Double,
    val BTN: Double,
    val BWP: Double,
    val BYN: Double,
    val BYR: Double,
    val BZD: Double,
    val CAD: Double,
    val CDF: Double,
    val CHF: Double,
    val CLF: Double,
    val CLP: Double,
    val CNY: Double,
    val COP: Double,
    val CRC: Double,
    val CUC: Double,
    val CUP: Double,
    val CVE: Double,
    val CZK: Double,
    val DJF: Double,
    val DKK: Double,
    val DOP: Double,
    val DZD: Double,
    val EGP: Double,
    val ERN: Double,
    val ETB: Double,
    val EUR: Double,
    val FJD: Double,
    val FKP: Double,
    val GBP: Double,
    val GEL: Double,
    val GGP: Double,
    val GHS: Double,
    val GIP: Double,
    val GMD: Double,
    val GNF: Double,
    val GTQ: Double,
    val GYD: Double,
    val HKD: Double,
    val HNL: Double,
    val HRK: Double,
    val HTG: Double,
    val HUF: Double,
    val IDR: Double,
    val ILS: Double,
    val IMP: Double,
    val INR: Double,
    val IQD: Double,
    val IRR: Double,
    val ISK: Double,
    val JEP: Double,
    val JMD: Double,
    val JOD: Double,
    val JPY: Double,
    val KES: Double,
    val KGS: Double,
    val KHR: Double,
    val KMF: Double,
    val KPW: Double,
    val KRW: Double,
    val KWD: Double,
    val KYD: Double,
    val KZT: Double,
    val LAK: Double,
    val LBP: Double,
    val LKR: Double,
    val LRD: Double,
    val LSL: Double,
    val LTL: Double,
    val LVL: Double,
    val LYD: Double,
    val MAD: Double,
    val MDL: Double,
    val MGA: Double,
    val MKD: Double,
    val MMK: Double,
    val MNT: Double,
    val MOP: Double,
    val MRO: Double,
    val MUR: Double,
    val MVR: Double,
    val MWK: Double,
    val MXN: Double,
    val MYR: Double,
    val MZN: Double,
    val NAD: Double,
    val NGN: Double,
    val NIO: Double,
    val NOK: Double,
    val NPR: Double,
    val NZD: Double,
    val OMR: Double,
    val PAB: Double,
    val PEN: Double,
    val PGK: Double,
    val PHP: Double,
    val PKR: Double,
    val PLN: Double,
    val PYG: Double,
    val QAR: Double,
    val RON: Double,
    val RSD: Double,
    val RUB: Double,
    val RWF: Double,
    val SAR: Double,
    val SBD: Double,
    val USD: Double,
){
    fun getCurrenciesAsList() : List<Any> = listOf(
        AED,
        AFN,
        ALL,
        AMD,
        ANG,
        AOA,
        ARS,
        AUD,
        AWG,
        AZN,
        BAM,
        BBD,
        BDT,
        BGN,
        BHD,
        BIF,
        BMD,
        BND,
        BOB,
        BRL,
        BSD,
        BTC,
        BTN,
        BWP,
        BYN,
        BYR,
        BZD,
        CAD,
        CDF,
        CHF,
        CLF,
        CLP,
        CNY,
        COP,
        CRC,
        CUC,
        CUP,
        CVE,
        CZK,
        DJF,
        DKK,
        DOP,
        DZD,
        EGP,
        ERN,
        ETB,
        EUR,
        FJD,
        FKP,
        GBP,
        GEL,
        GGP,
        GHS,
        GIP,
        GMD,
        GNF,
        GTQ,
        GYD,
        HKD,
        HNL,
        HRK,
        HTG,
        HUF,
        IDR,
        ILS,
        IMP,
        INR,
        IQD,
        IRR,
        ISK,
        JEP,
        JMD,
        JOD,
        JPY,
        KES,
        KGS,
        KHR,
        KMF,
        KPW,
        KRW,
        KWD,
        KYD,
        KZT,
        LAK,
        LBP,
        LKR,
        LRD,
        LSL,
        LTL,
        LVL,
        LYD,
        MAD,
        MDL,
        MGA,
        MKD,
        MMK,
        MNT,
        MOP,
        MRO,
        MUR,
        MVR,
        MWK,
        MXN,
        MYR,
        MZN,
        NAD,
        NGN,
        NIO,
        NOK,
        NPR,
        NZD,
        OMR,
        PAB,
        PEN,
        PGK,
        PHP,
        PKR,
        PLN,
        PYG,
        QAR,
        RON,
        RSD,
        RUB,
        RWF,
        SAR,
        SBD,
        USD,
    )
}
/*
cannot use more vals, seems like kotlin data class can contain only 127 elements as vals
    val SCR: Double, // changed 1
    val SDG: Double,
    val SEK: Double,
    val SGD: Double,
    val SHP: Double,
    val SLL: Double,
    val SOS: Double,
    val SRD: Double,
    val STD: Double,
    val SVC: Double,
    val SYP: Double,
    val SZL: Double,
    val THB: Double,
    val TJS: Double,
    val TMT: Double,
    val TND: Double,
    val TOP: Double,
    val TRY: Double,
    val TTD: Double,
    val TWD: Double,
    val TZS: Double,
    val UAH: Double,
    val UGX: Double,
    val USD: Double, // changed 1
    val UYU: Double,
    val UZS: Double,
    val VEF: Double,
    val VND: Double,
    val VUV: Double,
    val WST: Double,
    val XAF: Double,
    val XAG: Double,
    val XAU: Double,
    val XCD: Double,
    val XDR: Double,
    val XOF: Double,
    val XPF: Double,
    val YER: Double,
    val ZAR: Double,
    val ZMK: Double,
    val ZMW: Double,
    val ZWL: Double*/

