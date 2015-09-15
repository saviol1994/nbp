package pl.parser.nbp;


public class Currency {

    private String kod_waluty;
    private String kurs_kupna;
    private String kurs_sprzedazy;

    public Currency(String kod_waluty, String kurs_kupna, String kurs_sprzedazy) {
        this.kod_waluty = kod_waluty;
        this.kurs_kupna = kurs_kupna;
        this.kurs_sprzedazy = kurs_sprzedazy;
    }

    @Override
    public String toString() {

        return kod_waluty + " kupna : " + kurs_kupna + " sprzedazy: " + kurs_sprzedazy;
    }

    public float getKurs_kupna() {
        String kurs_kupnaFixed = kurs_kupna.replace(",", ".");
        return Float.parseFloat(kurs_kupnaFixed);
    }

    public float getKurs_sprzedazy() {
        String kurs_sprzedazyFixed = kurs_sprzedazy.replace(",", ".");
        return Float.parseFloat(kurs_sprzedazyFixed);
    }

}